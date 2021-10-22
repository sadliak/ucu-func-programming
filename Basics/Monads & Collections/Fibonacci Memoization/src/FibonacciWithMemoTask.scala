import scala.collection.mutable

object FibonacciWithMemoTask {
  def memoFibonacci(): Int => Long = {
    val cache = mutable.HashMap.empty[Int, Long]
    def fib(n: Int): Long = n match {
      case 0 => 0
      case 1 => 1
      case x => cache.getOrElseUpdate(x, fib(x - 1) + fib(x - 2))
    }

    fib
  }
}