import scala.io.StdIn.readLine

object Main extends App{

  class Variables{
    private var num1 : Double = 0.0
    private var num2: String = ""
    def setNum1(x : Double){
      num1 = x;
    }
    def getNum1(): Double ={
      num1
    }
    def setNum2(x : String){
      num2 = x;
    }
    def getNum2(): String ={
      num2
    }
  }

  var newVar: Variables = new Variables()
  print("Enter number: ")
  newVar.setNum1(readLine().toDouble)
  print("Enter expression: ")
  newVar.setNum2(readLine())
  var compute: Double = newVar.getNum1()
  while(newVar.getNum2() != "="){
    newVar.getNum2() match {
      case "+" => print("Enter number: "); newVar.setNum2(readLine()) ; compute = compute + newVar.getNum2().toDouble
      case "-" => print("Enter number: "); newVar.setNum2(readLine()) ; compute = compute - newVar.getNum2().toDouble
      case "/" => print("Enter number: "); newVar.setNum2(readLine()) ; compute = compute / newVar.getNum2().toDouble
      case "*" => print("Enter number: "); newVar.setNum2(readLine()) ; compute = compute * newVar.getNum2().toDouble
    }
    print("Enter expression: ")
    newVar.setNum2(readLine())
  }
  println(compute)
}