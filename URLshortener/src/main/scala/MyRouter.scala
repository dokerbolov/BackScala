import akka.actor.typed.ActorSystem
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCode, StatusCodes, Uri}
import akka.http.scaladsl.server.{Directives, Route}

import scala.concurrent.ExecutionContext

trait  Router {
  def route:Route
}

class MyRouter(urlRepository: UrlRepository)(implicit system: ActorSystem[_],  ex:ExecutionContext)
  extends Router
    with  Directives
    with UrlDirectives {

  import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
  import io.circe.generic.auto._

  override def route = concat(
    path("urls") {
      get {
        parameters("originalUrl") { originalUrl =>
          complete {
            HttpEntity(ContentTypes.`text/html(UTF-8)`, "done")
            urlRepository.createUrlList(originalUrl)
          }
        }
      }
    },
    path("allUrls") {
      get {
        pathEndOrSingleSlash {
          handleWithGeneric(urlRepository.all()) {
            urls => complete(urls)
          }
        }
      }
    },
    path(Segment) { url =>
      get {
        handleWithGeneric(urlRepository.findUrl(url)) { newUrl =>
          val uri = Uri.apply(newUrl)
          redirect(uri, StatusCodes.PermanentRedirect)
        }
      }
    }
  )
}
