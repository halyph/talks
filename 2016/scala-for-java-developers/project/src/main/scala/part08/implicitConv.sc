case class Euro(i: Int) {
  def +(euro: Euro) = Euro(this.i + euro.i)
}

object Dollar {
  implicit def dollarToEuro(x: Dollar): Euro = Euro(x.i + 10)
}

case class Dollar(i: Int)

Euro(1) + Euro(2)

Euro(1) + Dollar(2)