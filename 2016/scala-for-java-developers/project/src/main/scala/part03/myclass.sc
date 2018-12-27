case class Foo(private val a: Int) {
  var bInt : Int = 0
  def b_=(s: Int) = bInt = s
  private[this] def b = bInt
}


val foo  = new Foo(1)

foo.b = 2
foo
