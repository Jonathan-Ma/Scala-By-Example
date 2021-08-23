package Chapter9

object Ch9 {
  def main(args: Array[String]): Unit = {
    for(x <- fruit)
      println(x)
    val listInt = 10 :: 4 :: 6 :: 2 :: 3 :: 9 :: 2 :: Nil
    println("Insertion sort: " + isort(listInt))
    println("Pattern match insertion sort: " + isort2(listInt))
  }
  /*******************************************************************
   * Chapter 9 Lists
   ******************************************************************/
  /*
   * A list is represented as:
   */
  val intList: List[Int] = List(1, 2, 3)
  val stringList: List[String] = List("a", "b", "fruit")
  val empty = List()
  /*
   * Lists are similar to arrays but with three key differences:
   * 1. Lists are immutable, elements can't be changed by assignment
   * 2. Lists have a recursive structure, arrays are flat
   * 3. Lists support a richer set of operations
   */

  /*******************************************************************
   * 9.1 Using Lists
   ******************************************************************/
  /*
   * List constructors. All lists are built from two more fundamental
   * constructors, Nil and '::'(Cons operator).
   * We can use the Cons infix operator to write the lists above:
   */
  val fruit: List[String] = "Apple" :: "Pineapple" :: "Guava" :: "Tomato" :: Nil
  // Notice that if Nil is omitted this won't work
  /*
   * Basic operations on Lists.
   * 1. isEmpty: returns true if list is empty
   * 2. Tail: returns ALL elements after the first element
   * 3. Head: returns first element
   */
  // Exercise 9.1.1
  def isort(xs: List[Int]): List[Int] =
    if (xs.isEmpty) Nil
    else insert(xs.head, isort(xs.tail))
  def insert(a: Int, b: List[Int]): List[Int] =
    if (b.isEmpty || a <= b.head) a :: b
    else b.head :: insert(a, b.tail)
  /*
   * List patterns. The cons operator :: is a case class, therefore we can
   * decompose it by pattern matching, isort can be written as follow:
   */
  def isort2(list: List[Int]): List[Int] = list match{
    case List() => List()
    case x :: xs => insert2(x, isort2(xs))
  }
  def insert2(x: Int, xs: List[Int]): List[Int] = xs match{
    case List() => List(x)
    case y :: ys => if(x <= y) x :: xs else y :: insert2(x, ys)
  }
}
