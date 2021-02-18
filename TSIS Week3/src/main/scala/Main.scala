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
    //5
      def decompressRLElist(nums: Array[Int]): Array[Int] = {
        var k = 0
        var cnt = 0
        for (i <- 0 to nums.length-1 by 2){
          cnt = cnt + nums(i)
        }
        var newArr: Array[Int] = new Array[Int](cnt)
        for (i <- 0 to nums.length-1 by 2){
          for (j <- 0 until nums(i)){
            newArr(k) = nums(i+1)
            k = k + 1
          }
        }
        newArr
      }
    //6
      def sumZero(n: Int): Array[Int] = {
        var newArr: Array[Int] = new Array[Int](n)
        var k = n
        if(n%2 == 0){
          for(i <- 0 to (n-1)/2){
            newArr(i) = -k
            newArr(n-i-1) = k
            k = k - 1
          }
        }
        else{
          for(i <- 0 until (n-1)/2){
            newArr(i) = -k
            newArr(n-i-1) = k
            k = k - 1
          }
          newArr((n-1)/2) = 0
        }
        newArr
      }
    //7
      def kWeakestRows(mat: Array[Array[Int]], k: Int): Array[Int] = {
        var newArr: Array[Int] = new Array[Int](mat.length)
        var output: Array[Int] = new Array[Int](k)
        var sum = 0
        var cnt = 0
        var min = 101
        var perm = -1
        for(i <- 0 to mat.length-1){
          for( j <- 0 to mat(0).length-1){
            sum = sum + mat(i)(j)
          }
          newArr(i) = sum
          sum = 0
        }
        while(cnt != k){
          for(i <- 0 to newArr.length-1){
            if(newArr(i) < min){
              min = newArr(i)
              perm = i
            }
          }
          newArr(perm) = 101
          output(cnt) = perm
          perm = -1
          min = 101
          cnt = cnt + 1
        }
        output
      }
    //9
      def intersection(nums1: Array[Int], nums2: Array[Int]): Array[Int] = {
        (nums1 intersect nums2)
      }
}