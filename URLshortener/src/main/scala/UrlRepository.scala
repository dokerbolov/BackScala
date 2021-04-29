import java.util.UUID
import scala.util.hashing.MurmurHash3

import scala.concurrent.{ExecutionContext, Future}

trait UrlRepository{
  def all(): Future[Seq[Url]]

  def done(): Future[Seq[Url]]

  def createUrlList(originalUrl: String) : Future[String]

  def findUrl(shortUrl: String) : Future[String]
}

class UrlMemoryRepository(initialUrls: Seq[Url] = Seq.empty)(implicit ec:ExecutionContext) extends
  UrlRepository{

  private var urls: Vector[Url] = initialUrls.toVector

  override def all(): Future[Seq[Url]] = Future.successful(urls)

  override def done(): Future[Seq[Url]] = Future.successful(urls.filter(_.done))

  override def createUrlList(originalUrl: String) : Future[String] =
    Future.successful{
      val url = Url(
        id = UUID.randomUUID().toString(),
        givenUrl = originalUrl,
        shortUrl = MurmurHash3.stringHash(originalUrl).toString,
        done = true
      )
      urls = urls :+ url
      val show = "localhost:9000/find?shortUrl=" + url.shortUrl
      show
    }

  override def findUrl(shortUrl: String): Future[String] =
    Future.successful{
      var mainUrl:String = ""
        for(url <- urls){
          if (url.shortUrl == shortUrl ){
            mainUrl = url.givenUrl
          }
          else{
            mainUrl = "No such url"
          }
      }
      mainUrl
    }

}
