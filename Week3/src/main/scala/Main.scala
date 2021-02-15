object Main extends App{
    //1
    def kidsWithCandies(candies: Array[Int], extraCandies: Int): Array[Boolean] = {
      var max = 0
      var output: Array[Boolean] = new Array[Boolean](candies.length)
      for (i <- 0 to candies.length-1){
        if (max < candies(i)){
          max = candies(i)
        }
      }
      for (i <- 0 to candies.length-1){
        if (candies(i)+extraCandies >= max){
          output(i) = true;
        }
        else{
          output(i) = false;
        }
      }
      output
    }
    //3
    def smallerNumbersThanCurrent(nums: Array[Int]): Array[Int] = {
      var cnt = 0
      var output: Array[Int] = new Array[Int](nums.length)
      for (i <- 0 to nums.length-1; j <- 0 to nums.length-1){
        if(nums(i) > nums(j)){
          cnt += 1
        }
        if(j == nums.length-1){
          output(i)= cnt
          cnt = 0
        }
      }
      output
    }
    //4
    def repeatedNTimes(A: Array[Int]): Int = {
      var cnt = 0
      var max = 0
      var pos = 0
      var quantity: Array[Int] = new Array[Int](A.length)
      for (i <- 0 to A.length-1; j <- 0 to A.length-1){
        if(A(i) == A(j)){
          cnt += 1
        }
        if(j== A.length-1){
          quantity(i) = cnt - 1
          cnt = 0
        }
      }
      for (i <- 0 to quantity.length-1){
        if(quantity(i) > max){
          max = quantity(i)
          pos = i
        }
      }
      A(pos)
    }


}