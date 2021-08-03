package Chapter6
/*****************************************************************************
 * Chapter 6: Classes and Objects
 ***************************************************************************/
object Ch6{
  def main(args: Array[String]): Unit = {
    def apply(c: Int, d: Int) = new Rational(c, d)
    println(apply(10,2))
  }
}