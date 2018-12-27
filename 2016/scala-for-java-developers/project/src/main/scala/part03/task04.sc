import scala.collection.mutable.ArrayBuffer

"Ivano-Frankivsk".partition(_.isUpper)

val buf = ArrayBuffer(1, 2, -3, 4, -5, 10, -9, 8)
val (neg, pos) = buf.partition(_ < 0)
val result = pos
result += neg(0)
result


val symbols = List("<", "-", ">")
val counts = List(2,10,2)
val pairs = symbols.zip(counts)

for( (s, n) <- pairs) print(s * n)
println()