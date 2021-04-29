import java.util.UUID

import akka.http.scaladsl.model.StatusCodes

import scala.concurrent.{ExecutionContext, Future}

object ErrorType{
  final case class DublicateTitle(createTodo: CreateTodo) extends Exception("Title Exists")
}

class InMemoryTodoRepository(initialTodos:Seq[Todo] = Seq.empty)(implicit ec:ExecutionContext) extends TodoRepository {

  private var todos: Vector[Todo] = initialTodos.toVector

  override def all(): Future[Seq[Todo]] = Future.successful(todos)

  override def done(): Future[Seq[Todo]] = Future.successful(todos.filter(_.done))

  override def pending(): Future[Seq[Todo]] = Future.successful(todos.filterNot(_.done))

  override def create(createTodo: CreateTodo): Future[Todo] =
    todos.find(_.title == createTodo.title) match {
      case Some(_) =>
        Future.failed {
          ErrorType.DublicateTitle(createTodo)
        }
      case None =>
        Future.successful {
          val todo = Todo(
            id = UUID.randomUUID().toString,
            title = createTodo.title,
            description = createTodo.description,
            done = false
          )
          todos = todos :+ todo
          todo
        }
    }
}