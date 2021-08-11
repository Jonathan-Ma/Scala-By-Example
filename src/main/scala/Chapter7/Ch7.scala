package Chapter7

//import scala.sys.error

/*********************************************************************
 * Chapter 7 Case Classes and Pattern Matching
 *********************************************************************/
object Ch7{
  def main(args: Array[String]): Unit = {
    //val n = eval(new Sum(new Number(1),new Number(2)))
    //println(n)
//    val total = Sum(Number(2),Number(3))
//    total.print()
//    println(s" = ${total.eval.toString}")
    Sum(Number(2), Number(3)).print()
  }
  /*
   * Say we want to write an interpreter for arithmetic expressions.
   * Just a simple one for + operations and numbers.
   * We can represent such expressions with a class hierarchy rooted
   * at abstract class Expr and two sub classes Number and Sum.
   * Then the expression 1 + (3 + 7) would be represented as:
   * new Sum(new Number(1), new Sum(new Number(3), new Number(7))).
   *
   */
  /*
   * // commented out to demonstrate a decomposed version

  abstract class Expr {
    def isNumber: Boolean
    def isSum: Boolean
    def numValue: Int
    def leftOp: Expr
    def rightOp: Expr
  }
  class Number(n: Int) extends Expr {
    def isNumber: Boolean = true
    def isSum: Boolean = false
    def numValue: Int = n
    def leftOp: Expr = error("Number.leftOp")
    def rightOp: Expr = error("Number.rightOp")
  }
  class Sum(e1: Expr, e2: Expr) extends Expr {
    def isNumber: Boolean = false
    def isSum: Boolean = true
    def numValue: Int = error("Sum.numValue")
    def leftOp: Expr = e1
    def rightOp: Expr = e2
  }
  def eval(e: Expr): Int = {
    if (e.isNumber) e.numValue
    else if (e.isSum) eval(e.leftOp) + eval(e.rightOp)
    else error("unrecognized expression kind")
  }
  def print(e: Expr) {
    if (e.isNumber) Console.print(e.numValue)
    else if (e.isSum) {
      Console.print("(")
      print(e.leftOp)
      Console.print("+")
      print(e.rightOp)
      Console.print(")")
    } else error("unrecognized expression kind")
  }
  */

  /*
  * The above program would be tedious if we need to add another method
  * such as prod for Product. We can reuse existing, unmodified code
  * through inheritance. We can do this by making the "high-level"
  * eval into a method of each expression class instead of implementing
  * it as a function outside of the expression class hierarchy.
  *
  * The idea is that object oriented decomposition is preferable for
  * constructing systems that should be extensible for new types
  * of data.
  *
  * Just like the eval external function, the print function can
  * also be decomposed and implemented into each class node.
  * When a system is extended with new operations, all classes must
  * be modified.
  *
  */

  abstract class Expr{
    def eval: Int
    def print(): Unit
  }
  /* -----------------------------------------------------
   * Adding a case prefix has 4 effects
   *
   * 1. A constructor function is added implicitly
   * i.e. def Number(n: Int) = new Number(n)
   *             --------------------
   * 2. Case class and case objects implicitly comes
   *  with methods toString, equals, and hashcode.
   *
   * toString converts Sum(Number(2), Number(3)).print()
   * exactly like the structure while normal class returns
   * the class name and a number
   *
   * Equals method allows us to compare two identical
   * case objects, Sum(Number(2), Number(3)) == Sum(Number(2), Number(3))
   * is true while normal classes treats each object unique
   * when called by separate constructors
   *            --------------------
   * 3. Case classes implicitly come with nullary accessor
   * methods which retrieve the constructor arguments.
   *
   * For instance, for a value s of type Sum, s.e1 would access the left
   * operand of Sum.
   *            --------------------
   * 4. Case classes allow the constructions of patterns which refer
   * to the case class constructor.
   *
   */
  case class Number(n: Int) extends Expr{
    def eval: Int = n
    def print(): Unit = { Console.print(n) }
  }
  case class Sum(e1: Expr, e2: Expr){
    def eval: Int = e1.eval + e2.eval
    def print(): Unit = {
      Console.print("(")
      Console.print(e1.eval)
      Console.print("+")
      Console.print(e2.eval)
      Console.print(")")
    }
  }

  /*****************************************************************************
   * 7.1 Pattern Matching
   ****************************************************************************/
  /*
   * Pattern matching is generalization of C and Javas switch statements.
   * Instead of switch, scala uses match which is defined in scala's root
   * class Any, and therefore is available for all objects.
   */
  // pattern matching of eval
  // e is called the selector value
  def eval(e: Expr): Int = e match{
    // arguments are called pattern variables
    case Number(n: Int) => n
    case Sum(l, r) => eval(l) + eval(r)
  }
  /*
   * Each case associates a pattern with an expression. Patterns are
   * matched against the selector value e. The first pattern in this
   * example is Number(n), matches all values of the form Number(v),
   * v is arbitrary. The pattern variable n is bound to v. Similarly,
   * the pattern Sum(v1, v2) binds the pattern variable l, r to v1 and
   * v2 respectively.
   *
   * In general, patterns are built from:
   * 1. case class constructors e.g. Number, Sum whose arguments are patterns
   * 2. Pattern variables e.g. n, l, r
   * 3. The wildcard pattern "_"
   * 4. literals e.g. 1, true, "abc"
   * 5. constant identifiers, e.g. MAXINT , EmptySet .
   *              ---------------------
   * Pattern variables must start with lower case to be distinguished
   * from constant identifiers. Each variable also must be unique, Sum(x, x)
   * is illegal
   *              ---------------------
   * Pattern matching goes through the case list in order to find
   * the pattern. e match { case p 1 => e 1 ... case p n => e n }
   * matches in p1...pn in order.
   *              ---------------------
   * A constructor C(p1...pn) matches all values of type C(or subtype)
   * that have been constructed with C-arguments matching patterns (p1...pn)
   *
   * A variable pattern x matches with any value and binds the variable name
   * to that value
   *
   * Wildcard matches the value but does not bind the name
   *              ---------------------
   * 
   *
   */
}