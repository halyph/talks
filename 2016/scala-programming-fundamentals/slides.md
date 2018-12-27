class: center, middle, inverse
# Scala Programming Fundamentals

### by [@halyph](http://twitter.com/halyph)

---

# About this couse

- For Java devs

- Languages basics:
 - Scala basics
 - Control structure and functions
 - Arrays, Maps and Tuples
 
- OOP: Class and Objects

- OOP: Packages, Inheritance and Traits 

- Functional Programming

- Case Classes and Pattern Matching

???

Thoughts about next courses
- Implicits
- For comprehension

Libs:
- Akka
- Play
- Sprark

---

# Learning Objectives

---

class: center, middle, inverse

# 1 - Scala Basics

---

## Why Scala?

--

- Multi-paradigm language

--

- Object-oriented and functional

--

- Statically typed

--

- Scalable from simple scripts to sophisticated (but still easy-to-use) libraries

--

- Invented by Martin Odersky at EPFL

--

- Works with Java VM tools/libraries/infrastructure

--

- Used for big-iron projects in industry

--

- Pragmatic choice as successor to Java (whose evolution has slowed to a crawl)

--

- Expands your mind and makes you think differently about programming in any language.

???

- Java evolution is very slow. Scala is not so constrained. 
- Scala expends Ur mind

This slide is too verbose. It must be cleaned up.

---

## Scala Levels of Expertise

&nbsp;
&nbsp;

| Application Programmer   | Library Designer | Overall Scala Level |
|--------------------------|------------------|---------------------|
| Beginning **A1**         |                  | Beginning           |
| Intermediate **A2**      | Junior **L1**    | Intermediate        |
| Expert **A3**            | Senior **L2**    | Advanced            |
|                          | Expert **L3**    | Expert              |


.footnote[.red[*] See Martin's Odersky post http://www.scala-lang.org/old/node/8610]

???

1. Level A1: Beginning application programmer
  - Java-like statements and expressions: standard operators, method calls, conditionals, loops, try/catch
  - class, object, def, val, var, import, package
  - Infix notation for method calls
  - Simple closures
  - Collections with map, filter, etc
  - for-expressions
2. Level A2: Intermediate application programmer
  - Pattern matching
  - Trait composition
  - Recursion, in particular tail recursion
  - XML literals
3. Level A3: Expert application programmer
  - Folds, i.e. methods such as foldLeft, foldRight
  - Streams and other lazy data structures
  - Actors
  - Combinator parsers
4. Level L1: Junior library designer
  - Type parameters
  - Traits
  - Lazy vals
  - Control abstraction, currying
  - By-name parameters
5. Level L2: Senior library designer
  - Variance annotations
  - Existential types (e.g., to interface with Java wildcards)
  - Self type annotations and the cake pattern for dependency injection
  - Structural types (aka static duck typing)
  - Defining map/flatmap/withFilter for new kinds of for-expressions
  - Extractors
6. Level L3: Expert library designer
  - Early initializers
  - Abstract types
  - Implicit definitions
  - Higher-kinded types

---

## Scala Worksheets


_Demo IDEA Scala plugin worksheet feature_


---

## DEMO: Values, variable  and fundamental data type

- `val`, `var` variable

``` 
val valVariable = 8 * 4 + 3 
valVariable = 10  // Error

var varVariable = 12
varVariable = 44
 
```

Assign another type vales

```
varVariable = "String value"
```

---

## Declaring Variables

- Types `Int`, `Double`, `Boolean`, `String`.

- Arithmetic operators ` + - * / % ` like in Java.

- Variable type is inferred:

 ```
 val answer = 8 * 5 + 2 // answer is an Int
 ```

- Can specify type after the name:

 ```
 var greeting: String = null
 ```

- `val`: immutable, `var`: mutable.

- Use `val` whenever you can (use it as often as you can. Only use `var` when you really _must_ mutate a variable)

- Semicolons ("`;`") at the end of a statement are optional.

- No primitive type (see differences in Java: `int` _vs_ `Integer`)

???

Scala compiler can define val/var type

---

## Commonly Used Types

- `Int`, `Double`, `Byte`, `Char`, `Short`, `Long`, `Float`, `Boolean`

- Everything is an object: 
 ```
 1.to(10) // Apply to method to 1, returns Range(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
 ```
- The `to` method is actually defined in `RichInt`.

- `java.lang.String` is available in Scala.

- Augmented by > 100 methods in `StringOps`:
 ```
 "Hello".intersect("World") // Yields "lo"
 ```
 
???

Show in worksheet

---
## Commonly Used Types (cont.)

- Big numbers are actually usable:
  ```
  val x: BigInt = 1234567890
  x * x * x // Yields 1881676371789154860897069000
  ```

- Infix notation:
  ```
  1 to 10 // Same as 1.to(10)
  1 + 10 // Same as 1.+(10)
  ```

- The `++`, `--` operators do not exist in Scala.
 - Use `+=` instead: `counter += 1`.
 
---
 
## Calling Functions and Methods

- Scala has both functions and methods:
  ```
  import scala.math._
  sqrt(2) // A function
  BigInt.probablePrime(100, scala.util.Random) // A method
  ```

- Methods without parameters usually don't use ():
  ```
  "Hello".distinct
  ```

- Rule of thumb: `()` only required for mutators

- Common to use `(`arg`)` for methods that are similar to function calls:
  ```
  "Hello"(4) // Yields 'o'
  ```
- Provided by `apply` method:
  ```
  "Hello".apply(4) // same as "Hello"(4)
  ```
  
???
1. Methods which act as accessors of any sort (either encapsulating a field or a logical property) should be declared without parentheses except if they have side effects. 

---
## Scaladoc


- Permalink: http://www.scala-lang.org/api/current

- Use the filter.

- Look inside companion objects (**O** = Object, **C** = Class).

- Look at packages (`scala.math`), `Rich`Xxx, `StringOps`.

- Methods can have function parameters: 
```
def count(p: (Char) => Boolean): Int
```
- Lots of classes. Use your intuition. `Range`, `Seq[Char]` mean what you think they do.

- Lots of methods.

- Don't freak out when you see the occasional indecipherable incantation:
```
def scan[B >: Char, That](z: B)(op: (B, B) ⇒ B)(implicit cbf:
  CanBuildFrom[String, B, That]): That 
  ```

---

## Lab

- Part 1: The Scala Worksheet

-  Part 2: Functions

- Part 3: Scaladoc

???

### Part 1: The Scala Worksheet

1.  Start the Scala IDE and make a new Scala project: File → New → Scala
    Project.\
    Give a name `Lesson1`.\
    Right-click on the project in the Package Explorer, then select New
    → Scala Worksheet. Call it `Sheet1`.\
    Select Project from the menu and uncheck the Build Automatically
    checkbox.\
    Make a new line before the `}` . Type `6 * 7` and then
    save (Ctrl+S/⌘+S). What do you get?
2.  Edit the line to read `val a = 6 * 7` and save. What do you get?
3.  Add `a`. What do you get?
4.  Add `a = 43`. What do you get? Why?
5.  Remove that line and add `val b;` (This time with a semicolon.) What
    do you get? Why?
6.  Change the line to `val b: BigInt = 6 * 7`. What do you get?
7.  Add `b.pow(1000)`. What do you get?

### Part 2: Functions

1.  Type `import scala.math._` just above the `}`.
2.  Below the import statement, type:

        sqrt(10)

    What do you get?

3.  Type `1.to(10)`. What do you get?
4.  Type `1.to(10).map(sqrt(_))`. What do you get?
5.  Type `6.*(7)`. What do you get? Why?

###  Part 3: Scaladoc

1.  Open Scaladoc:
    [http://www.scala-lang.org/api/current](http://www.scala-lang.org/api/current/).\
    Click on `Int`, then click on the large C icon. Watch it turn into
    an O.\
    What is the Scala analog of `java.lang.Integer.MAX_VALUE`?
2.  Type Str into the search box (next to the magnifying class Icon).\
    Click on `StringOps`.\
    Read the description of `distinct`.\
    What do you expect the result of `"Mississippi".distinct` to be?
3.  Try it out in the worksheet. Is that what you got?
4.  Read the description of `permutations`.\
    What do you expect the result of `"Rhine".permutations` to be?
5.  That's not so good. Add `.toArray`. Now what do you get?
6.  What do you get for `"ABC".sum`? Why? (Hint: Try `'A'.toInt` and
    `"ABC".sum.toInt`)

TODO Add more tasks about Scaladoc usage. different taks

---

class: center, middle, inverse

# 2 - Control Structures and Functions

---

## Conditional Expressions


-   An `if` expression has a value:
```
    if (x > 0) 1 else -1
```
    Like `x > 0 ? 1 : -1` in Java/C/C++.

-   Type is the common supertype of the branches:
```
    if (x > 0) "positive" else -1 // Type is Any
```
-   Scala `Unit` type is similar to `void`, but it has one value,
    denoted `()`.
-   Missing `else` has value `Unit`:
```
    if (x > 0) 1
```
    is the same as
```
    if (x > 0) 1 else ()
```
---

## Block Expressions

-   Value of block is the value of the last expression:
```
    val distance = {
      val dx = x - x0
      val dy = y - y0
      sqrt(dx * dx + dy * dy)
    }
```
-   If the last expression is an assignment, the block value is `()`:
```
    while (n > 0) {
      i += 1
      n = n / 2
    }
```

---

## Use the “for each” and “for comprehension” control structures

---

## For Loops

-   No three-part loop `for (i = 1; i <= n; i++)`
-   Use “for each” loop instead (**Note**: No `val`/`var` before `i`):
```
    for (i <- 1 to n)
```
-   Works for any collection:
```
    for (ch <- "Hello"
```
-   Multiple “generators”:
```
    for (i <- 1 to 3; j <- 1 to 3) print((10 * i + j) + " ")
```
-   Guards:
```
    for (i <- 1 to 3; j <- 1 to 3 if i != j) print((10 * i + j) + " ")
```
-   Collecting results:
```
    for (i <- 1 to 10) yield i % 3
```

---

## Functions

-   To define a function, specify name, parameters, body:
```
    def abs(x: Double) = if (x >= 0) x else -x
```
-   Return type is inferred *unless* the function is recursive:
```
    def fac(n: Int): Int = if (n <= 0) 1 else n * fac(n - 1)
```
-   If you omit the `=`, the function doesn't return a value:
```
    def box(s: String) { // Look carefully: no =
      val border = "-" * s.length + "--\n"
      println(border + "|" + s + "|\n" + border)
    }
```
-   Caution—easy to accidentally omit the `=` symbol:
```
    def fac(n: Int) { // Probably an error
      var r = 1
      for (i <- 1 to n) r = r * i
      r
    }
```

---

## Understand named, default, and variable function arguments 

---

## Named and Default Arguments

-   “Named” argument is useful for greater clarity:
```
    regionMatches(ignoreCase = true, ...)
```
-   Default arguments let you omit argument values:
```
    def decorate(str: String, left: String = "[", right: String = "]") =
      left + str + right
```
-   Here, defaults are used for both `left` and `right`:
```
    decorate("Hello") // [Hello]
```
-   Default is only used for `right`:
```
    decorate("Hello", ">>>[") // >>>[Hello]
```
-   Default is only used for `left`, named parameter for `right`:
```
    decorate("Hello", right = "]<<<") // [Hello]<<<
```

---

## Varargs

-   Variable number of arguments indicated with `*` after type:
```
    def sum(args: Int*) = { // args is a Seq[Int]
      var result = 0
      for (arg <- args) result += arg
      result
    }
```
-   Can call with any number of arguments:
```
    val s = sum(1, 4, 9, 16, 25)
```
-   If you already have a `Seq[Int]`, need decoration to pass it:
```
    val s = sum(1 to 5: _*)
```
-   Needed in recursive calls:
```
    def recursiveSum(args: Int*): Int = {
      if (args.length == 0) 0
      else args.head + recursiveSum(args.tail : _*)
    }
```

---

## Lab

???

### Practice writing functions and using control structures 


1.  Write a function that tests whether a character is a vowel (for now,
    a lowercase `aeiou`).

        def isVowel(ch: Char) = ...

2.  Did you use an `if` statement? If so, write it without an `if`.
    (Hint: `contains`)
3.  Write a function that, given a string, returns a string of all of
    its vowels. Use `isVowel`. Use a `for` loop.

        def vowels(s: String) = ...

4.  Repeat with a `for ... yield` loop. (Hint: Guards)
5.  Repeat with a recursive solution.
6.  Repeat with a `while` loop.
7.  Pick your favorite version. Add a parameter `vowelChars` with
    default `"aeiuo"` and a parameter `ignoreCase` with default `true`.
8.  Call it to find all vowels in the German word `"Übeltätergehör"`.
    (Yes, those things with dots are vowels in German.)

---

class: center, middle, inverse

# 3 - Arrays, Maps, Tuples

---

## Arrays

-   Use square brackets for the type:
```
    val nums = new Array[Int](10) // Ten integers, all initialized with zero
```

-   Use `Array` without `new` to specify initial values:
```
    val a = Array("Hello", "World") //  The type is inferred
```

-   Use parentheses to access the elements:
```
    a(0) = "Goodbye"
```
-   `for (element <- a)` traverses the array elements

-   `for (i <- 0 until a.length)` traverses the array indexes

-   In the JVM, an `Array` is a Java array (`int[]`,
    `java.lang.String[]`).

---

## Variable-Length Arrays

-   Use `ArrayBuffer` as analog for Java `ArrayList`/C++ `vector`:
```
    import scala.collection.mutable.ArrayBuffer
    val b = new ArrayBuffer[Int]
```
-   Use `+=` to add at the end:
```
    b += 1
    b += (1, 2, 3, 5)
    b ++= Array(8, 13, 21)
```
-   Lots of methods to manipulate buffer:
```
    b.insert(2, 6)
    b.insert(2, 7, 8, 9)
    b.remove(2)
    b.remove(3)
    b.trimEnd(5)
    ...
```
-   Conversion between buffers and arrays:
```
    val a = b.toArray
    val b2 = a.toBuffer
```

---

## Transforming Arrays

-   Easy to transform the values of an array/array buffer/any collection
    with `for`/`yield`

-   Result is a new array/array buffer/collection with the transformed
    values:
```
    val a = Array(2, 3, 5, 7, 11)
    val result = for (elem <- a) yield 2 * elem
      // result is Array(4, 6, 10, 14, 22)
```
-   Use a guard to keep only wanted values:
```
    for (elem <- a if elem % 2 == 0) yield 2 * elem
```

---

## Common Algorithms

-   Common computations are easy in Scala:
```
    Array(1, 7, 2, 9).sum // 19
    ArrayBuffer("Mary", "had", "a", "little", "lamb").max // "little"
    ArrayBuffer(1, 7, 2, 9).sorted // ArrayBuffer(1, 2, 7, 9)
    Array(1, 7, 2, 9).reverse // Array(9, 2, 7, 1)
```
-   `toString` works like in Java:
```
    a.toString() // I@b73e5
    b.toString() // ArrayBuffer(Hello, World)
```
-   Use `mkString` for useful results:
```
    a.mkString(", ") // 1, 7, 2, 9
    b.mkString("[", ", ", "]") // [Hello, World]
```

---

## Maps

-   Construct from key/value pairs:
```
    val scores = Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
```
-   Use `scala.collection.mutable.Map` if you want the map to
    be mutable.

-   A map is a collection of *pairs* such as `("Alice", 10)`.

-   The `->` operator makes a pair: `"Alice" -> 10` yields
    `("Alice", 10)`.

-   Access values with `()`:
```
    val bobsScore = scores("Bob")
```
-   Or better, to avoid exceptions:
```
    val bobsScore = scores.getOrElse("Bob", 0)
```

---

## Updating Maps

-   If `scores` is mutable, you can assign to `scores(key)`:
```
    scores("Bob") = 20
```
-   You can append key/value pairs and remove keys (and their values):
```
    scores += ("Bob" -> 10, "Fred" -> 7)
    scores -= "Alice"
```
-   With immutable maps, use `+` and `-`:
```
    val newScores = scores + ("Bob" -> 10, "Fred" -> 7)
    val newScores2 = newScores - "Alice"
```
    The results share most of their structure with the original.

---

## Iterating over Maps

-   Easy with `for` and pattern matching:
```
    for ((k, v) <- scores)
      println(k + " has score " + v)
```
-   Use `for`/`yield` to get a new map:
```
    for ((k, v) <- scores) yield (v, k)
```
-   As in Java, can get key and value collections with
    `scores.keySet`/`scores.values`.

---

## Tuples

-   Aggregates values of *different* types:
```
    val t = (1, 3.14, "Fred") // An instance of (Int, Double, java.lang.String)
```
-   Access components with `_`n:
```
    val second = t._2
```
-   Tuple positions start with 1!

-   Better to use pattern matching:
```
    val (_, second, third) = t
```
---

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

---

class: center, middle, inverse

# 4 - Classes and Objects

---

## Classes

-   A simple class:
```
    class Point(val x: Double, val y: Double) {
      def move(dx: Double, dy: Double) = new Point(x + dx, y + dy)
      def distanceFromOrigin = math.sqrt(x * x + y * y)
      override def toString = f"(${x}, ${y})"
    }
```
-   Three methods: `move`, `distanceFromOrigin`, `toString`

-   Need `override` when overriding methods (here `Object.toString`)

-   No `()` for parameterless accessor methods

-   Two instance variables `x`, `y` with getters, call `p.x`, `p.y`

-   Unrelated aside—string interpolation:
```
    f"Hello, ${name}!"
```
    fills the value of the expression `name` into the string.

-   Works with `printf`-style formatters: `f"${x}%8.2f"`

---

## Immutable and Mutable Classes

-   `Point` is an immutable class—can't change the `val` fields:
    -   `p.x`, `p.y` can only be used to read, not to mutate, the
        instance fields
    -   `move` yields a new object!

-   Prefer immutable classes:
    -   Sharing is safe
    -   Especially important in concurrent programs

-   Use `var` for mutable instance variables:
```
    class Point(var x: Double, var y: Double)
```
-   Then you can assign to `p.x`, `p.y`.

-   Can also have private `var` or `val`:
```
    class BankAccount {
      private var balance = 0.0
      ...
    }
```

---

## Constructors

-   Consider thi
```
    class Point(val x: Double, val y: Double) {
      def this() { this(0, 0) }
      ...
     }
```
-   A “primary constructor” called as `new Point(3, 4)`

-   An “auxiliary constructor” called as `new Point()`

-   Could have eliminated the auxiliary constructor by using default
    args:
```
    class Point(val x: Double = 0, val y: Double = 0)
```

-   Can have arbitrary code in class body—part of primary constructor:
```
    class Point(val x: Double, val y: Double) {
      println(f"Welcome to (${x}, ${y})")
        // Printed whenever a new point is constructed
      ...
    }
```

---

## The Uniform Access Principle

-   In the expression `p.x`, you don't know whether `x` is a field or
    a method.

-   `x` could have been defined as a field:
```
    class Point(val x: Double, ...)
```

-   Or `x` could have been a method:
```
    class Point(...) {
      private val r = ...
      private val theta = ...
      def x = r * cos(theta)
      ...
    }
```

-   “Uniform access” means the class user doesn't know or care.

-   The implementor can switch between fields and methods without
    changing the interface.

-   No need to fear public instance variables.

---

## More About Methods

-   Infix notation: `x op y` is the same as `x.op(y)`.
-   Example:
```
    1 to 10 map (3 * _) filter (_ % 5 == 2)
```
-   Identifiers (method names, etc.) can be sequences of symbols:
```
    class Point(...) {
      ...
      def *(factor: Double) = new Point(x * factor, y * factor)
    }
```
-   Call as `p.*(2)` or `p * 2`, whichever you prefer.

-   If an operator ends in `:`, it is right-associative.

-   For example, consider the following expression:
```
    1 :: 2 :: 3 :: Nil 
```
    The parentheses group to the right:
```
    1 :: (2 :: (3 :: Nil))
```
-   To implement “function call” notation, provide an `apply` method:
```
    `map(key)` is `map.apply(key)`.
```
---

## Objects

-   Use `object` for singletons, static methods:
```
    object Accounts {
      private var lastNumber = 0
      def newUniqueNumber() = { lastNumber += 1; lastNumber }
        // Aside: Use () since it mutates state
    }
```
-   An object extending `App` is like `main`:
```
    object MyApp extends App {
       println(f"Hello, ${args(0)}!")
    }
```
---

## Companion Objects

-   "Companion object" of class = object with the same name in the same
    source file:
```
    class Point { ... }
    object Point { ... }
```
-   Have access to each other's private features.

-   Common to have `apply` in companion object for factory methods:
```
    object Point {
      def apply(x: Double, y: Double) = new Point(x, y)
    }
```
-   Client doesn't call `new`:
```
    val p = Point(3, 4) * factor
      // prettier than new Point(3, 4)
```

---

## Lab

 _see slide notes_

???

### Part 1: It's About Time

1.  Write a class `Time` with read-only fields `hours` and `minutes`, a
    method `toString`, and a method `before(other: Time): Boolean` that
    checks whether this time comes before the other. A `Time` object
    should be constructed as `new Time(h, m)`, where `h` is between 0
    and 23 and `m` between 0 and 59. If they aren't, call
    `throw new IllegalArgumentException`.
2.  Construct a couple of `Time` objects and test your `before` method.
3.  Make it so that a full hour can be constructed as `new Time(hrs)`.
    There are two different ways. What are they?

### Part 2: Uniform Access

1.  In a new worksheet, reimplement the `Time` class from the preceding
    exercise so that the internal representation is the number of
    minutes since midnight (between 0 and 24 × 60 – 1). Do not change
    the public interface.

    Do *not* use `var` or `val` in the primary constructor!

        class Time(h: Int, m: Int) {
          private val minutesSinceMidnight = ...
          ...
        }

    Supply parameterless *methods* `hours`, `minutes`.

2.  Now we'll make this a little harder. In the *original* class, make
    `minutes` into a mutable field, so that the following is okay:

        val start = new Time(13, 0)
        start.minutes = 15

    What did you have to do?

3.  Do the same for the reimplemented class. You need to provide a
    setter method with the special name property`_=`, like this:

        def minutes_=(newValue: Int) { ... }

4.  In the original implementation, as changed in part 2b, it would have
    been possible to corrupt the field values by calling:

        start.minutes = -100

    How can you avoid that in the modified implementation?

5.  Explain what “uniform access” means in this context. What changes
    does a programmer using the `Time` class have to make when switching
    from the original to the reimplemented class?
6.  Why are getters and setters less evil in Scala than in Java?

### Part 3: Operators

Do the following with either the original or the reimplemented `Time`
class (your choice):

1.  Change the `before` method of part 1 so that one can call:

        if (t1 < t2) ...

2.  Add a method `-` that, given a `Time` object, yields the number of
    minutes between them (between -1439 and 1439).
3.  Make it so that a `Time` object can be constructed without calling
    `new`.

---

class: center, middle, inverse

# 5 - Packages, Inheritance, and Traits

---

## Packages

-   Purpose of package: unique names.
```
    val now = new java.util.Date // and not java.sql.Date
```
-   Scala packages nest:
```
    package com {
      package horstmann {
        package impatient {
          class Employee 
          ...
        }
      }
    }
```
    Fully qualified name: `com.horstmann.impatient.Employee`

-   Just like nested classes

---

## Packages (Cont.)

-   As in Java, a Scala package can have contributions from multiple
    source files.

-   In Scala, can have contributions to multiple packages in a single
    source file.

-   In Scala, no relationship between packages and source directories.

-   Top-of file package statement without braces:
```
    package com.mypackage.service
```
    Like in Java: The entire source file is in this package.

---

## Imports

-   Purpose of imports: short names.
```
    import java.util.Date
    val now = new Date // shortcut for new java.util.Date
```
-   Wildcard imports use the Scala wildcard symbol:
```
    import java.util._
    import java.lang.Math._ // like import static in Java
```
-   Other variants:
```
    import java.awt.{Color, Font} // import two classes
    import java.util.{HashMap => JavaHashMap} // alias 
    import java.util.{HashMap => _, _} // hide a class
```

---

## Imports (Cont.)

-   Imports can be *anywhere*:
```
    class Manager {
      import scala.collection.mutable._
      val subordinates = new ArrayBuffer[Employee]
      ...
    }
```
-   `java.lang`, `scala`, `Predef` are always imported.

---

## Package Nesting

-   Java packages do not nest—no relationship between `java.util` and
    `java.util.regex`.

-   Scala packages nest:
```
    package com {
      package horstmann {
        object App {
          def run { ... }
        }
        package impatient {
          new App(...)
        }
      }
    }
```
-   Caution: Names can be either relative or absolute. `App` could be
    `com.horstmann.App` or `scala.App`.

-   Use `_root_.scala.App` to disambiguate.

---

## Inheritance

-   Subclass declaration like in Java:
```
    class Manager extends Employee
```
-   Run-time type inquiry:
```
    e.isInstanceOf[Manager] // like e instanceof Manager
    e.asInstanceOf[Manager] // like (Manager) e
    e.getClass == classOf[Manager] // like Manager.class
```
-   Superclass construction:
```
    class Employee(name: String, age: Int, val salary: Double)
      extends Person(name, age)
```

---

## Traits

-   Superficially similar to Java interface

-   A class can extend multiple traits:
```
    class Employee extends Person with Cloneable with Serializable
```
-   Traits can have concrete methods (like Java 8 default methods).

-   Traits can have abstract fields.
    -   A concrete implementing class must supply them.

-   Traits can have concrete fields.
    -   Concrete trait fields are added to the implementing class.

-   Traits cannot have construction parameters.
    -   Technically, this is the *only* difference between classes
        and traits.

---

## Mixins


-   Use traits to “mix in” small amounts of functionality into a class:
```
    class ArrayBuffer[A] extends AbstractBuffer[A]
       with Buffer[A] with GenericTraversableTemplate[A, ArrayBuffer]
       with BufferLike[A, ArrayBuffer[A]]
       with IndexedSeqOptimized[A, ArrayBuffer[A]]
       with Builder[A, ArrayBuffer[A]] with ResizableArray[A]
       with CustomParallelizable[A, ParArray[A]] with Serializable
```

---

## Mixins (Cont.)

-   Can also mix into objects:
```
    trait Logged {
      def log(msg: String) {}
    }

    trait ConsoleLogger extends Logged {
      override def log(msg: String) { println(msg) }
    }

    class SavingsAccount extends Logged {
      private var balance: Double = 0
      def withdraw(amount: Double) {
        if (amount > balance) log("Insufficient funds")
        else balance -= amount
      }
      // ...
    }

    val acct = new SavingsAccount with ConsoleLogger
    val acct2 = new SavingsAccount with FileLogger
```

---

## Layers

-   Trait methods can invoke others in a prior layer:
```
        trait TimestampLogger extends Logged {
          override def log(msg: String) {
            super.log(new java.util.Date() + " " + msg)
          }
        }
          
        trait ShortLogger extends Logged {
          val maxLength = 15
          override def log(msg: String) {
            super.log(
              if (msg.length <= maxLength) msg 
              else msg.substring(0, maxLength - 3) + "...")
          }
        }

        val acct1 = new SavingsAccount with ConsoleLogger
          with TimestampLogger with ShortLogger
        acct1.withdraw(1000) // Sun Nov 01 17:45:45 ICT 2015 Insufficient...  
```

---

## Lab

- Part 1: Mixing in Missing Methods
- Part 2: Reversing the Mixin Order
- Part 3: Buffering

???

### Part 1: Mixing in Missing Methods

1.  The `java.awt.Rectangle` class has a method `translate` that moves a
    rectangle by a given amount. Try it out: In a worksheet, import the
    `java.awt` package, construct a `new Rectangle(5, 10, 20, 30)`, call
    `translate(10, 20)`, and see what its coordinates are now.
2.  To make ellipses, you use the class
    `java.awt.geom.Ellipse2D.Double`. Make an instance
    `val egg = new geom.Ellipse2D.Double(5, 10, 20, 30)`. Why didn't you
    have to put `java.awt` before `geom`?
3.  Now call `egg.translate(10, 20)`. What happens?
4.  That's eggsasperating. Why didn't they add the method to
    `Ellipse2D`, or better, its superclass `RectangularShape`? We can
    fix that in Scala. Define a trait:

        trait RectangleLike {
            def setFrame(x: Double, y: Double, w: Double, h: Double): Unit
            def getX: Double
            def getY: Double
            def getWidth: Double
            def getHeight: Double
            def translate(dx: Double, dy: Double) { setFrame(getX + dx, getY + dy, getWidth, getHeight) }
          }

    Now change the definition of `egg` so that the call to
    `translate` works. What did you do?

### Part 2: Reversing the Mixin Order

1.  Run through the “Layers” example and reverse the order of the
    mixins:

        val acct2 = new SavingsAccount
          with ConsoleLogger with ShortLogger with TimestampLogger {
          override val maxLength = 20
        }

    *Without running the program*, what do you think will happen when
    you call `acct2.withdraw(1000)?`

2.  Now run the program. Were you right?
3.  What happens when you try the following?

        val acct3 = new SavingsAccount
          with ShortLogger with TimestampLogger with ConsoleLogger 

### Part 3: Buffering

1.  In the `java.io` library, one has to work hard to get services such
    as buffering:

        new BufferedReader(new InputStreamReader(new FileInputStream("/usr/share/dict/words")))

    It shouldn't be so hard to add buffering. (Buffering means not to
    read each byte separately but to read them a chunk at a time.) Here
    is a Scala trait:

        trait Buffered extends InputStream {
             val SIZE = 1024
             private var end = 0
             private val buffer = new Array[Byte](SIZE)
             private var pos = 0
             
             override def read() = {
               if (pos == end) {
                 end = super.read(buffer, 0, SIZE)
                 pos = 0
               }
               if (pos == end) -1 else {
                 pos += 1
                 buffer(pos - 1)
               }
             }
          }

    Mix in `Buffered` into a
    `new FileInputStream("/usr/share/dict/words")` (or some other file
    if you aren't running Linux/Mac OS). How do you do that? Then call
    `read` a few times. What do you get?

2.  How can you tell that buffering happens? Mix in a logger!

---

class: center, middle, inverse

# 6 - Functional Programming

---

## Functions as Values

-   A function is a “first-class citizen”, just like a number:
```
    import scala.math._
    val num = 3.14 // type: Double
    val fun = ceil _ // type: (Double) => Double 
```
-   What can you do with a function?
    -   Call it
    -   Store it
    -   Give it to another function/method
-   To call a function, use `()`:
```
    fun(num) // 4.0; that's ceil(3.14)
```
-   Here is how to give a function to another function/method:
```
    Array(3.14, 1.42, 2.0).map(fun) // Array(4.0, 2.0, 2.0)
```

---

## Anonymous Functions

-   You don't give each number a name: `y = x * 2` and not
    `val factor = 2; y = x * factor`

-   You don't need to give each function a name:
```
    Array(3.14, 1.42, 2.0).map((x: Double) => 3 * x) // Array(9.42, 4.26, 6.0)
```
-   Of course, you can store the function in a variable:
```
    val triple = (x: Double) => 3 * x
```
-   Same as a `def`:
```
    def triple(x: Double) = 3 * x
```
-   Only give a function a name if you need it multiple times.

---

## Functions with Function Parameters

-   Some functions/methods have function parameters:
```
    def valueAtOneQuarter(f: (Double) => Double) = f(0.25)
```
-   Note that the parameter `f` has type `(Double) => Double`.

-   Usage examples:
```
    valueAtOneQuarter(ceil _) // 1.0
    valueAtOneQuarter(sqrt _) // 0.5 (because 0.5 × 0.5 = 0.25)
```
-   Type of `valueAtOneQuarter`:
```
    ((Double) => Double) => Double
```
-   `valueAtOneQuarter` is a *higher-order function*: It is a function
    that consumes a function

---

## Functions That Produce Functions

-   A function can *produce* another function:
```
    def mulBy(factor: Double) = (x: Double) => factor * x
```
-   For example, `mulBy(3)` returns `(x: Double) => 3 * x`.

-   That's the function that we previously stored in `triple`.

-   We can produce a function with any multiplier:
```
    val quintuple = mulBy(5)
    quintuple(20) // 100
```
-   Type of `mulBy:`
```
    (Double) => ((Double) => Double)
```

---

## Parameter Inference

-   Scala can deduce types:
```
    valueAtOneQuarter((x) => 3 * x)
```
    instead of
```
    valueAtOneQuarter((x: Double) => 3 * x)
```
-   Special bonus: Can omit `()` if there is just one parameter:
```
    valueAtOneQuarter(x => 3 * x)
```
-   If the parameter variable occurs just once, can replace with `_`:
```
    valueAtOneQuarter(3 * _)
```
---

## Map, Filter, Reduce

-   `map` applies a function to each element of a sequence:
```
    (1 to 9).map(0.1 * _) // 0.1, 0.2, . . . , 0.9
```
-   `filter` retains the elements that fulfill a predicate:
```
    (1 to 9).filter(_ % 2 == 0) // 2, 4, 6, 8
```
-   `reduceLeft` applies a binary function, going from left to right:
```
    (1 to 9).reduceLeft(_ * _) // (...((1 * 2) * 3) * ... * 9)
```
-   You have seen `map` and `filter` before:
```
    (1 to 9).filter(_ % 2 == 0).map(0.1 * _)
```
    is the same as
```
    for (n <- 1 to 9 if n % 2 == 0) yield 0.1 * n
```

---

## Closures

-   A function has access to any variable from any enclosing scope...
-   ...even if the variable is no longer around when you call the
    function!

-   Consider this in slow motion:
```
        def mulBy(factor: Double) = (x: Double) => factor * x
        val triple = mulBy(3)
        triple(14) // Yields 42
```
    1.  When calling `mulBy(3)`, `factor` is set to 3.
    2.  When `mulBy(3)` returns, `triple` is set to the function...
    3.  ...and the parameter variable `factor` is gone.
    4.  When `triple(14)` is called, `factor * 14` is computed.

-   The function “captures” the variables that it needs.

-   Function + values for free variables = *closure*.

-   Implemented as objects with instance variables for
    captured variables.

---

## Currying

-   Currying = turning a function that takes two arguments into a
    function that takes one argument.

-   That function returns a function that consumes the second argument.
-   Compare
```
    def mul(x: Int, y: Int) = x * y
```
    and the “curried” version:
```
    def mulOneAtATime(x: Int) = (y: Int) => x * y
```

-   `mulOneAtATime(3)` is the function `y => 3 * y`.

-   `mulOneAtATime(3)(14)` is 42.

-   Syntactic sugar:
```
    def mulOneAtATime(x: Int)(y: Int) = x * y
```

---

## Currying for Type Inference

-   The `corresponds` method compares whether two sequences are the same
    under some comparison criterion:
```
    val a = Array("Hello", "World")
    val b = Array("hello", "world")
    a.corresponds(b)(_.equalsIgnoreCase(_)) // true
```
-   Note the currying:
```
    a.corresponds(b)(_.equalsIgnoreCase(_))
```
-   The method is declared like this:
```
    def corresponds[B](that: Seq[B])(p: (A, B) => Boolean): Boolean
```
-   When the compiler sees `a.corresponds(b)`, it can infer the type `B`
    as `String`.

-   Then you can omit the parameter types on the predicate.

-   Without currying, it would have to be like this:
```
    a.corresponds(b, (sString, tString) => s.equalsIgnoreCase(t))
```
---

## Control Abstractions

-   Can model a sequence of statements as a function with no parameters

-   Then provide “control abstractions” that manipulate that function

-   For example, run some statements in a separate thread:
```
    runInThread { () =>
      println("Hi"); Thread.sleep(10000); println("Bye")
    }
```
-   Implementation is straighforward:
```
    def runInThread(block: () => Unit) {
      new Thread {
        override def run() { block() }
      }.start()
    }
```

---

## Control Abstractions (Cont.)

-   Avoid the unsightly `() =>` in the call with *call by name*
    notation:
```
    def runInThread(block: => Unit) { 
      new Thread {
        override def run() { block }
      }.start()
    }
```
-   Now the call looks prettier:
```
    runInThread { println("Hi"); Thread.sleep(10000); println("Bye") }
```
---

## Lab

- Part 1: Life Without Loops

- Part 2: Reductions

- Part 3: Do-It-Yourself `while`

???

### Part 1: Life Without Loops

1.  In Scala, we prefer to use higher-order functions instead of loops.
    Let's explore that with an interesting data set. In a new worksheet,
    enter the following:

        val zones = java.util.TimeZone.getAvailableIDs

    What do you get?

2.  We want to get rid of the continents. Try this:

        zones.map(s => s.split("/"))

3.  Okay, halfway there. Add a map that takes an array and yields
    `a(1)`. What did you do? What happens?
4.  Hmmm, that's weird. There seem to be arrays of length &lt; 2. How
    can you find them?
5.  Okay, now get rid of them and try again. What did you do?
6.  That's a lot of values. Can we get every tenth of them? (Hint:
    `grouped(10)`)
7.  What would it have taken to write that in Java?

### Part 2: Reductions

1.  Evaluate

        1.to(10).reduceLeft(_ * _)

    What do you get?

2.  Write a function that computes n! in this way.
3.  Surely you have written a factorial function before. How did you
    used to do it? Which approach do you like better?
4.  Now we'd like to compute 2^n^ with the same trick. How can you get a
    sequence of `n` copies of the number 2? Hint: `map`
5.  What is your function that computes 2^n^?
6.  Given a `Seq[String]`, how can you use `reduceLeft` to concatenate
    them with some separator in between? Write a function
    `concat(strings: Seq[String], separator: String)` that does this.
    For example,
    `concat(Array("Mary", "had", "a", "little", "lamb"), " ")` should
    give a string `"Mary had a little lamb"`.

### Part 3: Do-It-Yourself `while`

1.  You've seen how to implement a `runInThread` statement in Scala. You
    can even implement basic control statements such as `while`. We'll
    have to call it `While` so it doesn't conflict with the
    Scala keyword. We'll have two arguments, the condition and the body.
    We start out the ugly way, with zero-arg functions. For example:
```
    val n = 10
    var i = 1
    var f = 1
    While(() => i < n, () => { f *= i; i += 1 })
```
    How do you declare `While`? (Just the header, not
    the implementation)

2.  Now on with the implementation. If the condition is true, execute
    the body. Then call the function recursively. What do you get when
    running the code snippet above?
3.  Now use by-name parameters so that you can make the call in this
    way:
```
    While(i < n, { f *= i; i += 1 })
```
4.  It's still a little ugly. Make it so that you can call the
    following:

        While(i < n) { f *= i; i += 1 }

    Hint: Curry

---

class: center, middle, inverse

# 7 - Case Classes and Pattern Matching 

---

## A Better Switch

-   Match is a deluxe version of the `switch` statement:
```
    sign = ch match {
      case '+' => 1
      case '-' => -1
      case _ => 0
    }
```
-   An expression, like `if`.

-   No `break`.

-   Guards:
```
    ch match {
      case '+' => sign = 1
      case '-' => sign = -1
      case _ if Character.isDigit(ch) => digit = Character.digit(ch, 10)
      case _ => sign = 0
    }
```

---

## Variables in Patterns

-   `case` var assigns the match expression to the variable:

```
    str(i) match {
      case '+' => sign = 1
      case '-' => sign = -1
      case ch if Character.isDigit(ch) => digit = Character.digit(ch, 10)
    }
```
-   Unfortunately, that conflicts with the following:

```
    import scala.math._
    x match {
      case Pi => ...
      ...
    }
```
-   Icky rule: Variable must start with a lowercase letter.

-   Include lowercase constants in \` ... \`:
```
    import java.io.File._
    str match {
      case `pathSeparator` => ...
      ...
    }
```

---

## Type Patterns

-   `case` var`: `Type matches if var has the given type:
```
    obj match {
      case x: Int => x
      case s: String => Integer.parseInt(s)
      case _: BigInt => Int.MaxValue
      case _ => 0
    }
```
-   Preferred over `var.isInstanceOf[Type]`

-   Caution: If you omit the variable, you match the *companion object*!
```
    obj match {
      case BigInt => -1 // Matches the BigInt object of type Class
      ..
    }
```

---

## Catching Exceptions

-   Uses `case` syntax:
```
    try {
      ...
    } catch {
      case e: IOException => println("Caught " + e)
    }
```
-   Use wildcard if you don't care about the exception object:
```
    try {
      ...
    } catch {
      case _: Throwable => println("Oh noes!")
    }
```

---

## Extractors

-   Can “extract” contents from tuples (i.e., bind variables to the
    contents):
```
    pair match {
      case (0, _) => "0 ..."
      case (y, 0) => y + " 0"
      case _ => "neither is 0"
    }
```
-   Also with arrays:
```
    arr match {
      case Array(0) => "0"
      case Array(x, y) => x + " " + y
      case Array(0, _*) => "0 ..."
      case _ => "something else"
    }
```

---

## Extractors in Variable Declarations

-   Define variables just like in `case` clause:
```
    val (uppercase, lowercase) = "Hello, World".partition(Character.isUpperCase(_))
      // partition returns a pair
    val Array(first, second, _*) = arr
```
-   Also works in `for` expressions:
```
    for ((key, value) <- map) // a map is a sequence of pairs
      println(key + "->" + value)
```
-   Special syntax for binding to `_*`:
```
    val Array(first, second, rest @ _*) = arr
```

---

## Case Classes

-   Case class: class that is optimized for use in pattern matching.
```
    abstract class Amount
    case class Dollar(value: Double) extends Amount
    case class Currency(value: Double, unit: String) extends Amount
    case object Nothing extends Amount
```
-   Extractors work out of the box:
```
    amt match {
      case Dollar(v) => "$" + v
      case Currency(_, u) => "Oh noes, I got " + u
      case Nothing => ""
    }
```

---

## Case Classes

-   Each of the constructor parameters becomes a `val`:
```
    case class Currency(value: Double, unit: String) extends Amount
```
-   The companion object gets an `apply` factory method:
```
    Currency(1000, "EUR")
```
-   Methods `toString`, `equals`, `hashCode`, `unapply`, and `copy`
    are generated.

-   `unapply` makes extractors work.

-   `copy` lets you copy values:
```
    val amt = Currency(1000, "EUR")
    val price = amt.copy()
    val price2 = amt.copy(unit = "CHF")
```

---

## Example: Option

-   `Option[T]` is a safe alternative to providing a value of type `T`
    or `null`.

-   Case class `Some[T]` that wraps a value.

-   Case object `None` that indicates that there is no value.

-   Example: `Map[K, V].get` returns an `Option[V]`, either `Some(v)` or
    `None`.

-   Can use pattern matching to process:
```
    val scores = Map("Alice" -> 1, "Bob" -> 2)
    scores.get("Alice") match {
      case Some(score) => println(score)
      case None => println("No score")
    }
```

---

## Recursive Data Structures

-   An expression is either a number, sum, or product:
```
    abstract class Expr
    case class Num(value: Int) extends Expr
    case class Sum(left: Expr, right: Expr) extends Expr
    case class Product(left: Expr, right: Expr) extends Expr
```
-   Sample instance:
```
    val e = Product(Num(3), Sum(Num(4), Num(5)))
```
-   Want to evaluate expressions:
```
    def eval(e: Expr): Int = e match {
      case Num(v) => v
      case Sum(l, r) => eval(l) + eval(r)
      case Product(l, r) => eval(l) * eval(r)
    }
```

---

## Case Classes or Polymorphism?

-   In OO programming, one would have used polymorphism for expressions:
```
    abstract class Expr {
      def eval: Int
    }
    class Sum(val left: Expr, val right: Expr) extends Expr {
      def eval: Int = left.eval + right.eval
    }
```
-   Polymorphism is appropriate for an *open-ended* collection
    of subclasses.

-   Case classes and pattern matching are best for a
    *bounded* collection.

-   Code is more concise with case classes.

-   All cases are in one place.

-   Use what works: Scala is functional *and* object oriented.

---

### Lab

- Part 1: Pattern Matching

- Part 2: Articles and Bundles

- Part 3: The `Option` Type

???

### Part 1: Pattern Matching

1.  Using pattern matching, write a function `swap` that receives a pair
    of integers and returns the pair with the components swapped.
2.  Using pattern matching, write a function `swap` that receives an
    `Array[Int]` and returns the array with the first two elements
    swapped, provided the length is at least two.

### Part 2: Articles and Bundles

1.  A store sells items. Some are articles. Others are bundles of
    articles—stuff that you buy together for a discount.

        abstract class Item
        case class Article(description: String, price: Double) extends Item
        case class Bundle(description: String, discount: Double, items: Item*) extends Item

    Declare an `Article`: the book “Scala for the Impatient” for
    \$39.95:

        val book = ...

2.  Declare a bundle, sold at a \$10 discount: the book from before, and
    a bottle of Old Potrero Straight Rye Whiskey for \$79.95:

        val gift = ...

3.  Write a function `price(it: Item): Double` that computes the price
    of an item. Use pattern matching. Start with filling in the case for
    `Article`. What is your code?
4.  What happens when you call `price(book)`? `price(gift)`?
5.  Now add the `Bundle` case to `price`. The hard part is to match
    the varargs. Use `case Bundle(_, disc, its @ _*)`. You want to
    recursively compute the sum of the prices of the items (Hint: `map`,
    `sum`), and subtract the discount.
6.  Suppose you have a bundle made up of an article and another bundle,
    like this:

        val special = Bundle("Father's day special", 20.0,
          Article("Scala for the Impatient", 39.95),
          Bundle("Anchor Distillery Sampler", 10.0,
            Article("Old Potrero Straight Rye Whiskey", 79.95),
            Article("Junípero Gin", 32.95)))

    Write a one-line assigment that extracts the description and price
    of the first article:

        val ...(descr, price)... = special

### Part 3: The `Option` Type

1.  In this part, we will reimplement the `Option` type for
    `Double` values. Provide classes `DoubleOption`, `SomeDouble`, and
    `NoDouble`. What are they?
2.  Write a function `inv` that maps x into its inverse (1 / x),
    returning a `DoubleOption`. Return `NoDouble` when x is zero. What
    is your function? What happens when you call `inv(2)`? `inv(0)`?
3.  Write a function that composes two functions of type
    `Double => DoubleOption`, yielding another function of the
    same type. The composition should yield `NoDouble` if either
    function does. For example, with

        def f(x: Double) = if (x <= 1) SomeDouble(sqrt(1 - x)) else NoDouble
        val h = compose(inv, f) // inv(f(x)) when defined

    the result of `h(1)` and `h(2)` are `NoDouble`, but `h(0)` is
    `SomeDouble(1)`.

4.  Define a method `isEmpty` that returns `true` for `NoDouble` and
    `false` for `SomeDouble`. Use pattern matching.
5.  Define a method `get` that returns the value wrapped in `SomeDouble`
    and throws a `NoSuchElementException` if there is no value. Use
    pattern matching.
6.  Repeat the previous two steps, using polymorphism. Define abstract
    methods in `DoubleOption` and override them in `SomeDouble` and
    `NoDouble`.