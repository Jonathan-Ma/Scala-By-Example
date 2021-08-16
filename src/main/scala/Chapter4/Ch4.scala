package Chapter4

object Ch4 {
  //program to find the square root of a number
  def main(args: Array[String]): Unit = {
    println(sqrt(1.0e20))
    println(factorial(5))
  }

  def sqrt(x: Double): Double = sqrtIter(1.0, x)

  def sqrtIter(guess: Double, x: Double): Double =
    if (isGoodEnough(guess, x)) guess
    else sqrtIter(improve(guess, x), x)

  def isGoodEnough(guess: Double, x: Double) =
    (guess * guess - x).abs < 0.001

  def improve(guess: Double, x: Double): Double = {
    (guess + x / guess) / 2
    /*
     * Scala uses block-structured scoping rules, a name defined
     * in some outer block does is visible in inner block, provided its not
     * redefined there
     * therefore x does not have to be passed around
     * so a simpler code would be:
     *
     * def sqrt(x: Double) = {
     *  def sqrtIter(guess: Double): Double =
     *    if (isGoodEnough(guess)) guess
     *    else sqrtIter(improve(guess))
     *  def improve(guess: Double,): Double = {
     *    (guess + x/guess) / 2
     *  def isGoodEnough(guess: Double) =
     *   (guess*guess - x).abs < 0.001
     *  sqrtIter(1.0)
     * }
     *
     */
  }

  // a tail recursion version of finding a numbers factorial
  def factorial(n: Int): Int = {
    def fact(x: Int, n: Int): Int = if (n <= 1) x else fact(x * n, n - 1)

    fact(1, n)
  }

  /*
   * Every definition must be followed by a semicolon, which seperates this
   * definition from subsequent definitions or result expressions. However,
   * semicolon is inserted implicitly at the end of each line unless the following
   * conditions is true:
   * 1. Either the line in question ends in a word such as a period or infix-operator
   * which would not be legal as the end of an expression.
   * 2. Or the next line starts with a word that cannot start a expression
   * 3. Or we are in parentheses or brackets
   */
}
