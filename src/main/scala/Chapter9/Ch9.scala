package Chapter9

object Ch9 {
  def main(args: Array[String]): Unit = {
    println()
    for(x <- fruit)
      println(x)
    val listInt = 10 :: 4 :: 6 :: 2 :: 3 :: 9 :: 2 :: Nil
    println("Example list: " + listInt)
    println("Insertion sort: " + isort(listInt))
    println("Pattern match insertion sort: " + isort2(listInt))
    val drop = listInt drop 2
    val take = listInt take 3
    val split = listInt splitAt 4
    val apply = listInt.apply(3)
    println("xs drop 2: " + drop)
    println("xs take 3: " + take)
    println("xs splitAt 4: " + split)
    println("xs.apply(3): " + apply)
    val listInt2 = List(1 ,3, 6, 4, 7, 5, 9, 2)
    println("Second example list: " + listInt2)
    val zipList = listInt zip listInt2
    println("Zipping list 1 and 2: " + zipList)
    // using map to do something will all elements
    val map = listInt map (x => x * 3)
    println(map)
    println("Squares all elements in list using recursion: " + squareList2(listInt))
    println("Square all elements using map: " + squareList(listInt))
    // filtering list for positive integers
    val newList = List(-1,3,-4,5,-3,7,-8,8,-9)
    println("Positive ints in a list: " + posInts(newList))
    println(s"positive ints in list using filter${posInt2(newList)}")
    // forall and List.range to find prime numbers
    println(s"forall and list.range() to check is a number is prime: ${isPrime(11)}")
    // reduceleft
    println(listInt.reduceLeft((x,y)=> x * y))
    println((listInt foldLeft 2) {(x, y) => x * y})
    println()
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
  /*******************************************************************
   * 9.2 Definition of class List I: First Order Methods
   ******************************************************************/
  /*
   * xs.last returns the last element of a list and xs.init returns
   * all elements except the last element. Both of these needs to
   * traverse the list to get the result.
   *
   * (xs drop n): drops the first n elements of the list
   * (xs take n): returns the first n elements or whole list
   * if n is bigger than the list length
   * (xs splitAt n): splits the list by n and returns the two list
   * The xs.apply(n) method is analogous to drop(n).head, in other words,
   * drop n elements and return the succeeding element(not the tail
   * of taking away n elements).
   *
   * Zipping a list. xs zip ys yields List((x1,y1)...(xn,yn)). If the list
   * has different lengths then the longer one is truncated.
   *
   * Concatenating lists we can use ":::". xs ::: yx results in all
   * xs elements followed by ys elements.
   */
  /*******************************************************************
   * 9.3 Example: Merge Sort
   ******************************************************************/

  /*******************************************************************
   * 9.4 Definition of class List II: Higher-Order Methods
   ******************************************************************/
  /*
   * Map method xs map (A => B), we can use this to scale a list by a factor
   * like xs map (x => x * 3), this returns a list of the original list
   * elements multiplied by 3.
   */
  // method to square all elements in list
  def squareList2(xs: List[Int]): List[Int] = xs match {
    case List() => Nil
    case y :: ys => (y * y) :: squareList2(ys)
  }
  // method using map to square all elements
  def squareList(xs: List[Int]): List[Int] =
    xs map (x => x * x)

  /*
   * Filtering lists. A function that returns a list of positive integers
   */
  def posInts(xs: List[Int]): List[Int] = xs match{
    case Nil => Nil
    case x :: ys => if(x >= 0) x :: posInts(ys) else posInts(ys)
  }
  // using "filter" method
  def posInt2(xs: List[Int]): List[Int] = {
    xs filter(x => x >= 0)
  }
  // using "List.range(x,y)" and "forall" to find prime numbers from 2 to n excluding n
  def isPrime(n: Int) =
    List.range(2, n) forall(x => n % x != 0)
  /*
   * Folding and reducing lists. Another common operation is to combine the elements
   * with some operator such as sum(List(x1..xn))
   * 
   */

}
