package Chapter9

object Ch9 {
  def main(args: Array[String]): Unit = {
    for(x <- fruit)
      println(x)
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
}
