object ZipWordTask {
  def zipIt(prefixSeq: Seq[String], wordSeq: Seq[String], suffixSeq: Seq[String]): Seq[String] =
    (prefixSeq, wordSeq, suffixSeq).zipped.map((prefix, word, suffix) => s"$prefix$word$suffix")
}