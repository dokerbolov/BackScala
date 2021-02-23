import scala.io.StdIn.readLine

object Main extends App{

  class Variables{
    var num1 : Double = 0.0
    var name: String = ""
  }

  var newVar: Variables = new Variables()
  print("Enter number: ")
  newVar.num1 = readLine().toDouble
  print("Enter expression: ")
  newVar.name = readLine()
  var compute: Double = newVar.num1
  while(newVar.name != "="){
    newVar.name match {
      case "+" => print("Enter number: "); newVar.name = readLine() ; compute = compute + newVar.name.toDouble
      case "-" => print("Enter number: "); newVar.name = readLine() ; compute = compute - newVar.name.toDouble
      case "/" => print("Enter number: "); newVar.name = readLine() ; compute = compute / newVar.name.toDouble
      case "*" => print("Enter number: "); newVar.name = readLine() ; compute = compute * newVar.name.toDouble
    }
    print("Enter expression: ")
    newVar.name = readLine()
  }
  println(compute)
}