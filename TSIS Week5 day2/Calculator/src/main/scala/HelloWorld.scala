import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.LoggerOps
import akka.actor.typed.{ ActorRef, ActorSystem, Behavior }

object HelloWorld {
  final case class Greet(myarr: Array[Double], str: String)

  def apply(): Behavior[Greet] = Behaviors.receive{(context, message) =>
    val calculation = new Calculation(message.myarr, message.str)
    context.log.info("Processing")
    calculation.printNum()
    Behaviors.stopped
  }
}