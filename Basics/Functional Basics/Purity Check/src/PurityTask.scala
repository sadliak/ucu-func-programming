object PurityTask {

  case class Integer(var value: Int) {}

  def isPure(incrementFn: Integer => Integer): Boolean = {
    val input1 = Integer(1)
    val incr1_1 = incrementFn(input1)
    val incr1_2 = incrementFn(input1)

    val input2 = Integer(2)
    val incr2 = incrementFn(input2)

    incr1_1 match {
      case x if x == input1 => false
      case x if x == incr2 => false
      case x if x != incr1_2 => false
      case _ => true
    }
  }
}