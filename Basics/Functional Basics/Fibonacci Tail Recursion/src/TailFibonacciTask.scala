import scala.annotation.tailrec

object TailFibonacciTask {
  def fibonacciImpl2(): Int => Int = {
    @tailrec
    def fibTail(depth: Int, num1: Int, num2: Int): Int = {
      depth match {
        case 0 => num1
        case 1 => num2
        case x => fibTail(x - 1, num2, num1 + num2)
      }
    }

    def fib(depth: Int): Int = {
      fibTail(depth, 0, 1)
    }

    fib
  }
}
