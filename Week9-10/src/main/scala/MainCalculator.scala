object MainCalculator {

  def calculate (equation: String): String ={

    var operatorsSymbols: String = ""
    var arr = Array[Double]()

    var perm: String = ""
    var input: String = equation

    for (i <- 0 to input.length() - 1) {
      if (input(i).toString == "=") {
        arr = arr :+ perm.toDouble
        perm = ""
      }
      else if (input(i).toString == "p" || input(i).toString == "-" || input(i).toString == "/" || input(i).toString == "*") {
        operatorsSymbols = operatorsSymbols + input(i).toString()
        arr = arr :+ perm.toDouble
        perm = ""
      }
      else {
        perm = perm + input(i).toString
      }
    }

    var res: Double = 0

    operatorsSymbols(0) match {
      case 'p' => res = arr(0) + arr(1)
      case '-' => res = arr(0) - arr(1)
      case '*' => res = arr(0) * arr(1)
      case '/' => res = arr(0) / arr(1)
    }
    for(i <- 1 to operatorsSymbols.length - 1){
      operatorsSymbols(i) match {
        case 'p' => res += arr(i+1)
        case '*' => res *= arr(i+1)
        case '-' => res -= arr(i+1)
        case '/' => res /= arr(i+1)
      }
    }
    res.toString()
  }
}
