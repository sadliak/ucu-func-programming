import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.xml.ScalaXmlSupport._
import akka.http.scaladsl.model.Uri.Query
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers._
import akka.http.scaladsl.unmarshalling.Unmarshal

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.concurrent.Future
import scala.xml.NodeSeq

object WikiIntegrationTask {

  def retrieveLatestChanges(): Future[Seq[WikiChange]] = {
    implicit val actorSystem = ActorSystem(Behaviors.empty, "http")
    implicit val executionContext = actorSystem.executionContext

    val request = HttpRequest(
      method = HttpMethods.GET,
      uri = Uri("https://www.mediawiki.org/w/api.php").withQuery(Query(("action", "feedrecentchanges"))),
      headers = Accept(MediaTypes.`application/xml`) :: Nil
    )

    Http()
      .singleRequest(request)
      .map(response => response.entity)
      .flatMap(entity => Unmarshal(entity).to[NodeSeq])
      .map(xml => (xml \ "channel" \ "item")
        .map(node => WikiChange(
          title = (node \ "title").text,
          link = Uri((node \ "link").text),
          guid = Uri((node \ "guid").text),
          description = (node \ "description").text,
          pubDate = LocalDateTime.parse((node \ "pubDate").text, DateTimeFormatter.RFC_1123_DATE_TIME),
        )))
  }

  case class WikiChange(title: String, link: Uri, guid: Uri, description: String, pubDate: LocalDateTime)
}