object GroupWordsTask {
  def groupIt(wordsSeq: Seq[String]): Map[Char, Int] =
    wordsSeq
      .map(word => (word.head, word))
      .groupBy(_._1)
      .mapValues(_.map(value => value._2.count(ch => ch == value._1)).sum)
}