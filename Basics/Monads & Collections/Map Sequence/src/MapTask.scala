import scala.util.Try

object MapTask {
  def mapping(input: Seq[String]): Seq[Int] =
    input
      .map(el => Try(el.toInt))
      .filter(el => el.isSuccess)
      .map(el => el.get)
}