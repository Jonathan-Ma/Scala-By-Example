package Chapter5

object Ch5 {
  def main(args: Array[String]): Unit = {
    println(sumInt(1, 5))
    println(sumSquare(1, 5))
  }

  /*
     * Functions in scala is a "first-class value", they can be
     * passed in as parameters of another function or they can
     * evaluate to a result. Functions that take in another
     * function as a parameter are called higher-order functions.
     *
     */
  // function to add integers between a and b
  def sumInt(a: Int, b: Int): Int =
    if (a > b) 0 else a + sumInt(a + 1, b)

  // function to add the square of all integers between a and b
  def square(x: Int):Int = x * x
  def sumSquare(a: Int, b: Int): Int =
    if (a > b) 0 else square(a) + sumSquare(square(a + 1), b)
}
