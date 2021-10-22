object TakeWordsTask {
  val lineSeparatorString = "\n"

  def takeWords(seq: Seq[String]): Seq[String] = {
    @annotation.tailrec
    def takeWordsTailRec(seq: Seq[String], newSeq: Seq[String], separator: String, seenSeparator: Boolean): Seq[String] =
      (seq, seenSeparator) match {
        case (Nil, _) => newSeq
        case (_, true) => newSeq
        case (x :: xs, false) =>
          val word = x.takeWhile(ch => ch.toString != separator)

          takeWordsTailRec(
            xs,
            if (word.nonEmpty) newSeq :+ word else newSeq,
            separator,
            x != word
          )
      }

    takeWordsTailRec(seq, Seq(), lineSeparatorString, seenSeparator = false)
  }
}
