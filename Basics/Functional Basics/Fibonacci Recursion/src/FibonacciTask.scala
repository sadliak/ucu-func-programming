object FibonacciTask {
  def fibonacciImpl(): Int => Int = {
    def fib(num: Int): Int = {
      num match {
        case x if x < 2 => x
        case x => fib(x - 1) + fib(x - 2)
      }
    }

    fib
  }
}