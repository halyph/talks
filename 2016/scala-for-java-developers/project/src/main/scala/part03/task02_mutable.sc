import java.util.Scanner

import part03.Words.words
import scala.collection.mutable

val count = mutable.Map[String, Int]()

val in = new Scanner(words)

while(in.hasNext) {
  val word = in.next()
  count(word) = count.getOrElse(word, 0) + 1
}
count
count("Alice")
count("and")

