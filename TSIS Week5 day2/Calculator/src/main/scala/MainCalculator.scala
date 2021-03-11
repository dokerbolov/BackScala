import scala.io.StdIn
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.LoggerOps
import akka.actor.typed.{ ActorRef, ActorSystem, Behavior }

object MainCalculator extends App{
  final case class Symbols(myarr: Array[Double],symbols: String)

  val numbers = new StringBuilder("")
  var operatorsSymbols: String = ""
  var arr = Array[Double]()

  var input = StdIn.readLine()
  while (input != "="){
    if (input == "+" || input == "*" || input == "/" || input == "-")
      operatorsSymbols ++= input
    else {
      arr = arr :+ input.toDouble
    }
    input = StdIn.readLine()
  }

  def apply(): Behavior[Symbols] = Behaviors.setup{context =>
    val replyTo = context.spawn(HelloWorld(), "HelloWorld")
    Behaviors.receiveMessage{ message =>
      context.log.info("Numbers are: {} and Symbols are: {}",message.myarr,message.symbols)
      replyTo ! HelloWorld.Greet(message.myarr, message.symbols)
      Behaviors.same
    }
  }
  val system: ActorSystem[Symbols] = ActorSystem(MainCalculator(), "calculatorObject")
  system ! Symbols(arr, operatorsSymbols)
}