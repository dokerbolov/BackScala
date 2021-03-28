import scala.io.StdIn.readLine
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.LoggerOps
import akka.actor.typed.{ActorRef, ActorSystem, Behavior}
import org.slf4j.{Logger, LoggerFactory}

object MainCalculator extends App{

  final case class Symbols(myarr: Array[Double],symbols: String)

  var operatorsSymbols: String = ""
  var arr = Array[Double]()



  var input = readLine()
  while (input != "="){
    if (input == "+" || input == "*" || input == "/" || input == "-")
      operatorsSymbols ++= input
    else {
      arr = arr :+ input.toDouble
    }
    input = readLine()
  }

  implicit val log: Logger = LoggerFactory.getLogger(getClass)

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