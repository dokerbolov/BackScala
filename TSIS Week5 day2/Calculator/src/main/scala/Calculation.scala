import Input.Sign
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.LoggerOps
import akka.actor.typed.{ActorRef, ActorSystem, Behavior}

object Calculation{

  def start(firstValue: Double): Behavior[Input.Sign] = {
    calc(firstValue)
  }

  def calc(firstValue: Double):Behavior[Input.Sign] =
    Behaviors.receive{ (context, message) =>
      var total : Double = 0.0
      message.str match {
        case "+" => total = total + firstValue;
          Behaviors.same
        case "-" => total = total - firstValue
          Behaviors.same
        case "/" => total = total / firstValue
          Behaviors.same
        case "*" => total = total * firstValue
          Behaviors.same
        case "=" =>
          Behaviors.stopped
      }
    }
}