import java.util.UUID
import scala.util.hashing.MurmurHash3

import scala.concurrent.{ExecutionContext, Future}

object ErrorType{
  final case class NoSuchUrl(url:Url) extends Exception("No such Url")
}

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
        val show = "https://urlsshortertask.herokuapp.com/find?shortUrl=" + url.shortUrl
        show
    }

  override def findUrl(shortUrl: String): Future[String] =
    Future.successful {
      var mainUrl: String = ""
      var bool: Boolean = false
      for (url <- urls) {
        if (url.shortUrl == shortUrl) {
          mainUrl = url.givenUrl
          bool = true
        }
      }
      if (bool) {
          mainUrl
      }
      else {
        val show = "No such Directory"
        show
      }
    }
}
