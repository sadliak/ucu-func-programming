import scala.annotation.tailrec

object FactorialTask {

  def factorialImpl(): Int => Long = {
    @tailrec
    def factTail(n: Int, result: Long): Long = {
      n match {
        case x if x <= 1 => result
        case x => factTail(x - 1, x * result)
      }
    }

    def factorial(n: Int): Long = {
      factTail(n, 1)
    }

    factorial
  }
}