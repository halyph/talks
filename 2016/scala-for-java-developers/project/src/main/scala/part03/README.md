## Lab

1. Removing All But the First Negative Number

2. Word Count

3. Grouping

4. Patitions and Zips

???

### Part 1: Removing All But the First Negative Number

1.  You are given an array buffer with positive and negative integers.
    Your task is to remove all but the first negative number. (Don't
    ask why.) How do you do that in Java? Just write some pseudocode.
2.  You probably set a flag when you saw the first negative number, and
    then when you saw the second, you unset the flag. And you were
    careful not to increment the index after removal. Or maybe you found
    the first negative index, and then went backward. Either way, that's
    a lot of detail that can go wrong. In Scala, you can do better.
    First, get the indexes where `a(i) < 0`. Use `until` and
    `for`/`yield`.
3.  Now drop the first index. (Hint: `drop` in `Seq`)
4.  Now you want to call `a.remove(i)` for the remaining ones. Except,
    you need to do that in reverse order. Use `for` and `reverse`.

### Part 2: Word Count

1.  A classic application for maps is word counting. Read a file, a word
    at a time, and keep a map that yields the frequency of each word.

        val in = new java.util.Scanner(new java.net.URL(
          "http://horstmann.com/scala/livelessons/alice30.txt").openStream)
        val count = scala.collection.mutable.Map[String, Int]()
        while (in.hasNext) {
          val word = in.next();
          count(word) = ...
        }

    What is `count("Alice")`? `count("Rabbit")`?

2.  Repeat with an immutable map. (Hint: `var count`)

### Part 3: Grouping

1.  Read through the description of the `groupBy` method in the
    `Array` class. It may not sound like much, but it is really useful
    in applications. Try this:

        val words = Array("Mary", "had", "a", "little", "lamb", "its", "fleece",
          "was", "white", "as", "snow", "and", "everywhere", "that", "Mary", "went",
          "the", "lamb", "was", "sure", "to", "go")

    What do you get for

        words.groupBy(_.substring(0, 1))

2.  How do you put all words with the same length into one bucket?

### Part 4: Partitions and Zips

1.  Tuples are useful for methods that yield more than one result. Try
    this out:

        "New York".partition(_.isUpper)

    What do you get? Why does the `partition` method yield a tuple?

2.  Look again at the exercise where you are supposed to print all
    positive values and only the first negative value. Suppose ordering
    doesn't matter. How can you trivially solve this with `partition`?
    (Hint: Pass the function `_ < 0`.)
3.  The zip method takes two collections of the same length and “zips”
    them together into a collection of tuples. Try this:

        val symbols = Array("<", "-", ">")
        val counts = Array(2, 10, 2)
        val pairs = symbols.zip(counts)

    What do you get?

4.  Now iterate over the result to produce a printout `<<---------->>`.
    That is, repeat the `<` twice, the `-` ten times, and the `>` twice.
    (Hint: `s * n` yields the string `s` repeated `n` times)
