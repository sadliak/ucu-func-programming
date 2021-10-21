object FlattingTask {

  def flattingStrings(strings: Seq[String]): Seq[Char] =
    strings.flatMap(_.toCharArray.toList)
}