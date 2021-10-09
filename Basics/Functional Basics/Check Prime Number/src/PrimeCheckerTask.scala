import scala.annotation.tailrec

object PrimeCheckerTask {

  def isPrime(n: Long): Boolean = {
    @tailrec
    def iter(i: Long, limit: Long, num: Long): Boolean = {
      i match {
        case x if x == limit => true
        case x if num % x == 0 => false
        case x => iter(x + 1, limit, num)
      }
    }

    iter(2, n / 2 + 1, n)
  }
}