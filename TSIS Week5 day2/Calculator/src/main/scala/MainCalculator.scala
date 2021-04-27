import scala.io.StdIn.readLine
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.LoggerOps
import akka.actor.typed.{ActorRef, ActorSystem, Behavior}
import org.slf4j.{Logger, LoggerFactory}

object MainCalculator extends App{

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
      else if (input(i).toString == "+" || input(i).toString == "-" || input(i).toString == "/" || input(i).toString == "*") {
        operatorsSymbols = operatorsSymbols + input(i).toString()
        arr = arr :+ perm.toDouble
        perm = ""
      }
      else {
        perm = perm + input(i).toString
      }
    }

    var res: Double = 0
    var temp:Int = 0
    val indexedSeq = for(i <- 0 to 10 if i % 2 == 0) yield i

    operatorsSymbols(0) match {
      case '+' => res = arr(0) + arr(1)
      case '-' => res = arr(0) - arr(1)
      case '*' => res = arr(0) * arr(1)
      case '/' => res = arr(0) / arr(1)
    }
    for(i <- 1 to operatorsSymbols.length - 1; j <- 2 to arr.length - 1){
      operatorsSymbols(i) match {
        case '+' => res += arr(j)
        case '*' => res *= arr(j)
        case '-' => res -= arr(j)
        case '/' => res /= arr(j)
      }
    }
    for (i <- 0 to arr.length-1) println(arr(i))
    println(operatorsSymbols)
    res.toString()
  }

  println(calculate("1*3*2+1="))
}