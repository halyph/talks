import scala.collection.mutable.ArrayBuffer

val buf = ArrayBuffer(1, 2, -3, 4, -5, 10, -9, 8)

def removeAllButFirstNEgative(buf: ArrayBuffer[Int]) = {
  val indexToRemove = (for(i <- 0 until buf.length if buf(i) <0) yield i).drop(1)
  for(i <- 0 until buf.length if !indexToRemove.contains(i)) yield buf(i)
}

removeAllButFirstNEgative(buf)