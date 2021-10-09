object PurityTask {

  case class Integer(var value: Int) {}

  def isPure(incrementFn: Integer => Integer): Boolean = ???
}