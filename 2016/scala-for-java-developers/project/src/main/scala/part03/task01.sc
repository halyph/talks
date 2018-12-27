import scala.collection.mutable.ArrayBuffer

val buf = ArrayBuffer(1, 2, -3, 4, -5, 10, -9, 8)

val index = for(i <- 0 until buf.length if buf(i) <0) yield i
val indexToRemove = index.drop(1)

for (i <- indexToRemove.reverse) buf.remove(i)
buf

// TODO make one expression ;-)
def removeAllButFirstNEgative(buf: ArrayBuffer[Int]) = {
  val index = for(i <- 0 until buf.length if buf(i) <0) yield i
  val indexToRemove = index.drop(1)
  for (i <- indexToRemove.reverse) buf.remove(i)
}

val buf2 = ArrayBuffer(1, 2, -3, 4, -5, 10, -9, 8)
removeAllButFirstNEgative(buf2)
buf2