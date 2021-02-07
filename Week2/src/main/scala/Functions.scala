object Functions extends App {

  def ex1(a: Int, b:Int) : Int ={
    a+b
  }

  def ex2(a:Int, b:Int) : Int ={
    val x = if (a<b) a else b
    x
  }

  def ex3(i:Int ):String ={
    i match {
      case 1 => "one"
      case 2 => "two"
      case _ => "not 1 or 2"
    }
  }

  def ex4(n:Int): Unit ={
    for (i <- 0 to n) println(i)
  }

  val x = for (i <- 1 to 5) yield i * 2

  println(ex1(1,2))
  println(ex2(1,2))
  println(ex3(1))
  print(ex4(5))
  println(x)

}