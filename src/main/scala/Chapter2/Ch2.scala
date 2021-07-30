import scala.language.postfixOps

object Ch2{
  def main(args: Array[String]): Unit = {
    val array = Array(23,34,63,21,26,6,7,47,1,74,37,37,23,33,46,43,22)
    // need a new array because sort2 function returns a
    // new array while argument array is unchanged
    val ar = sort2(array)
    for(a <- ar)
      print(a + " ")
  }
  // Quicksort implementation imperative like
  def sort(xs: Array[Int]) {
    def swap(i: Int, j: Int) {
      val t = xs(i); xs(i) = xs(j); xs(j) = t
    }
    def sort1(l: Int, r: Int) {
      val pivot = xs((l + r) / 2)
      var i = l; var j = r
      while (i <= j) {
        while (xs(i) < pivot) i += 1
        while (xs(j) > pivot) j -= 1
        if (i <= j) {
          swap(i, j)
          i += 1
          j -= 1
        }
      }
      if (l < j) sort1(l, j)
      if (j < r) sort1(i, r)
    }
    sort1(0, xs.length - 1)
  }
   // scala style quicksort, aka functional style
  def sort2(xs: Array[Int]): Array[Int] = {
    // if array is size 1 or smaller return the array
    if (xs.length <= 1) xs
    else {
      //find the pivot
      val pivot = xs(xs.length / 2)
      // concatenating sorted arrays using filter to get array of ints >pivot <pivot ==pivot
      Array.concat(
        sort2(xs filter (pivot >)),
        xs filter (pivot ==),
        sort2(xs filter (pivot <)))
      /*
       * The three filter functions are higher order functions because
       * they take in another function "pivot >", "pivot > " is a PARTIALLY
       * APPLIED function. Equivalent way of writing it is "x => pivot > x".
       * The function in filter is also an anonymous function because it has no name
       *
       */
    }
  }
}