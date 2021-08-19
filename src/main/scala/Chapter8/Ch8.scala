package Chapter8

import scala.annotation.tailrec
import scala.sys.error

object Ch8 {
  def main(args: Array[String]): Unit = {
    val x = new EmptyStack[Int]
    val y = x.push(1).push(2)
    val z = x.push(1).push(2)
    println(y.pop.top)
    println(isPrefix(z,y))
     // 8.1
    val set = new EmptySet[Num].incl(Num(2.0)).incl(Num(3.0))
    println(set.contains(Num(2.0)))
    //8.5 tuples
    val xy = divmod2(3,8)
    println("Quotient: " + xy._1 + ", remainder: " + xy._2)
    divmod2(3, 8) match {
      case Tuple2(n, d) =>
        println("Quotient: " + n + ", remainder: " + d)
    }
  }


  // Scala classes can have type parameters
  abstract class Stack[A] {
    def push(x: A): Stack[A] = new NonEmptyStack[A](x, this)
    def isEmpty: Boolean
    def top: A
    def pop: Stack[A]
  }
  class EmptyStack[A] extends Stack[A] {
    def isEmpty = true
    def top = error("EmptyStack.top")
    def pop = error("EmptyStack.pop")
  }
  class NonEmptyStack[A](elem: A, rest: Stack[A]) extends Stack[A] {
    def isEmpty = false
    def top = elem
    def pop = rest
  }
  /*
   * The 'A' in above program is a type parameter of class stack
   * and its subclasses. They are arbitrary names and are enclosed
   * in brackets instead of parentheses to distinguish from value
   * parameters.
   *
   * It is also possible to parameterize methods with types.
   * An example is a generic method that determines if one stack
   * is a prefix of another:
   */
  @tailrec
  def isPrefix[A](a: Stack[A], b: Stack[A]) :Boolean = {
    a.isEmpty || a.top == b.top && isPrefix(a.pop, b.pop)
  }
  /*
   * The method parameters are called polymorphic which is
   * greek for "have many forms".
   *              -------------------
   * Local type inferences: when we create an object with generic
   * parameters such as: isPrefix[int] = (x, y) we can omit
   * the [int] because scala inferences the type by the expected
   * result or the functions value parameters. Polymorphic
   * functions can omit the type.
   */
  /*******************************************************************
   * 8.1 Type parameter bounds
   ******************************************************************/
  /*
   * Previously we had IntSets which we could generalize as so:
   * abstract class Set[A] {
   *  def incl(x: A): Set[A]
   *  def contains(x: A): Boolean
   * }
   *
   * But the problem is we can't compare elements with &&, it was
   * ok for type Int because it had && compare method. But arbitrary
   * types is problematic.
   * For example:
   * def contains(x: Int): Boolean =
   *   if (x < elem) left contains x
   *         ^ < not a member of type A.
   * We can solve this by restricting the types allowed for A
   * that can use the method &&.
   * In scala standard class library there is a trait Ordered[A].
   * It represents values which are comparable (via &&) to type A.
   */

  trait Set[A <: Ordered[A]]{
    def incl(x: A): Set[A]
    def contains(x: A): Boolean
  }
  class EmptySet[A <: Ordered[A]] extends Set[A]{
    def incl(x: A) = new NonEmptySet(x, new EmptySet[A], new EmptySet[A])
    def contains(x: A): Boolean = false
  }
  class NonEmptySet[A <: Ordered[A]](elem: A, left: Set[A], right: Set[A]) extends Set[A] {
    def contains(x: A): Boolean =
      if (x < elem) left contains x
      else if (x > elem) right contains x
      else true
    def incl(x: A): Set[A] =
      if (x < elem) new NonEmptySet[A](elem, left incl x, right)
      else if (x > elem) new NonEmptySet[A](elem, left, right incl x)
      else this
  }
    case class Num(x: Double) extends Ordered[Num]{
      def compare(that: Num) =
        if (this.x < that.x) -1
        else if (this.x > that.x) 1
        else 0
    }
  /*
   * Type bounds require forethought, we can also use view bounds "<%",
   * which are weaker than plain bounds. View bounds only specifies that
   * [A <% T] type A must be convertible to bound type T using implicit conversion.
   */
  /*******************************************************************
   * 8.2 Variance Annotations
   ******************************************************************/
  /*
   * The property co-variant is defined as: if type T is a subtype of S, then
   * Stack[T] is a subtype of Stack[S]. But Scala generic types have default
   * non-variant subtyping. This means that elements of different types will never
   * have a subtyping relation. But we can change that by using prefix + in
   * front of a generic type like so:
   * class Stack[+A]. There is also prefix - that means contravariant,
   * which means if type T is a subtype of S then S is subtype of T.
   */
  // Example of covariance
  class Animal
  class Dog extends Animal
  class myList[+T]
  /* The +T indicates subtyping, since dog is a subtype of animal
   * then we can replace a list of animal with list of dog.*/
  val animals: myList[Animal] = new myList[Dog]
  /*******************************************************************
   * 8.3 Lower Bounds
   ******************************************************************/
  /*
   * There is upper bound which is annotated as: A <: T, this means
   * A has to be a subtype of T.
   * Suppose we have the following:
   */
    trait Ingredient{
      def isDelicious: Boolean
    }
    case object Chili extends Ingredient {
      def isDelicious = true
    }
    case object Tomato extends Ingredient {
      def isDelicious = true
    }
    // A filterDelicious function that filters a list of ingredients and returns delicious ones
    def filterDelicious(ingredients: List[Ingredient]): List[Ingredient] = ingredients.filter(_.isDelicious)
    val result: List[Ingredient] = filterDelicious(List(Chili, Chili))
    // Or we can do List[A <: Ingredient] and yield same result
    def filterDelicious1[A <: Ingredient](ingredients: List[A]): List[A] = ingredients.filter(_.isDelicious)
    // this is because A is a subtype of Ingredient so we can still use .isDelicious

    /*
     * For a lower bound variance List[A >: Ingredient], suppose we have
     * a Food trait and Ingredient extends Food like so:
     * trait Food
     * trait Ingredient extends Food{
     *  def isDelicious: Boolean
     * }
     * case object Chili extends Ingredient {
     *  def isDelicious = true
     * }
     * case object Tomato extends Ingredient {
     *  def isDelicious = true
     * }
     * Then with filterDelicious we can do:
     * def filterDelicious1[A >: Ingredient](ingredients: List[A]): List[A]
     * and A can be Food
   * Lower bound is the same concept indicated as A >: T which means
   * A is lower bounded by T, so A can be T or a supertype of T.
   *
   */

  /*******************************************************************
   * 8.5 Tuples
   ******************************************************************/
  /*
   * We can use tuples when we need to return multiple results, for example
   * a divmod function that returns the integer quotient and remainder of the
   * two integers.
   * We could use a class to hold the two results as so:
   */
  case class TwoInts(first: Int, second: Int)
  def divmod(x: Int, y: Int):TwoInts = new TwoInts(x/y, x%y)
  /*
   * But the problem is that having to create a class everytime to hold
   * values is tedious, thus we use tuples
   */
  def divmod2(x: Int, y:Int) = new Tuple2[Int, Int](x / y, x % y )
  // How to access the elements
  // val xy = divmod2(3,8)
  // println("Quotient: " + xy._1 + ", remainder: " + xy._2)
  /*
   * Tuple2 is defined in scala as
   * case class Tuple2[A, B](._1: A, ._2: B)
   * therefore we have to use new prefix to create a Tuple, there are also
   * Tuple1, Tuple3...etc
   */
   //Because its a case class, we can access Tuple elements by matching
  //   divmod2(x, y) match {
  //    case Tuple2(n, d) =>
  //      println("Quotient: " + n + ", remainder: " + d)
  //  }
  /*
   * "Tuple_" can be omitted, hence, we can write divmod like this:
   */
   def divmod3(x: Int, y: Int): (Int, Int) = (x / y, x % y)
  // Basically tuple becomes a type kind of
  // the matching for this is:
  /*
  divmod3 match {
    case (n, d) => println("Quotient: " + n + ", remainder: " + d)
  }
   */

  /*******************************************************************
   * 8.6 Functions
   ******************************************************************/
  /*
   * Functions are objects in Scala. For instance, a function from type String
   * to type Int is represented as an instance of the trait Function1[String, Int].
   */
  val plus1: (Int => Int) = (x: Int) => x + 1

  // Above function can be expanded to below
  val plus2: Function1[Int, Int] = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1
  }
  // to use function 1 plus1(3) is equivalent to second functions plus2.apply(3)

}
