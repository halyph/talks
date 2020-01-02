object JoesPrefs {
  implicit val prompt = new PreferredPrompt("Yes, master> ")
}

class PreferredPrompt(val preference: String)

class Foo(i: Int) {
  def buzz = i * 10
}

implicit def int2Foo(i: Int) = new Foo(i)

object Greeter {
  def greet(name: String)(implicit prompt: PreferredPrompt) = {
    println("Welcome, " + name + ". The system is ready.")
    println(prompt.preference)
  }
}


Greeter.greet("Hello team")(JoesPrefs.prompt)

{
  import JoesPrefs.prompt
  Greeter.greet("Hello in block")
}

"1".toInt

10.buzz