object Main extends App{
  //1
    def maxProduct(nums: Array[Int]): Int = {
      var newArr = nums.sorted
      (newArr(newArr.length-1)-1) * (newArr(newArr.length-2)-1)
    }
  //2
    def average(salary: Array[Int]): Double = {
      (salary.sum - salary.min - salary.max) / (salary.length - 2).toDouble
    }
  //3
    def dayOfTheWeek(day: Int, month: Int, year: Int): String = {

    }
}