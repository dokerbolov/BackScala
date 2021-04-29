import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.ExecutionContext



object Boot extends App {

  implicit val log: Logger = LoggerFactory.getLogger(getClass)

  val rootBehavior = Behaviors.setup[Nothing] { context =>

    implicit val ec = context.executionContext
    implicit val sys = context.system

    val mockUrls:Seq[Url] = Seq(
    )

    val urls = new UrlMemoryRepository(mockUrls)
    val router = new MyRouter(urls)

    MyServer.startHttpServer(router.route)
    Behaviors.empty

  }
  val system = ActorSystem[Nothing](rootBehavior, "HelloAkkaHttpServer")
}