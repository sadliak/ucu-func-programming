object FizzBuzzTask {
  def fizzBuzzIt(s: Seq[Int]): Seq[String] =
    s.map {
      case x if x % 15 == 0 => "FizzBuzz"
      case x if x % 3 == 0 => "Fizz"
      case x if x % 5 == 0 => "Buzz"
      case x => x.toString
    }
}