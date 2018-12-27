class: center, middle, inverse
# Scala for Java Developers

### 2016-12-08

---

# About this couse

- For Java developers

- No previous experience with Scala

- Languages basics

- No frameworks

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

- Be able to implement simple Scala project

- Understand language concepts

- Be able to find required Scala-related info

- Know who-is-who in Scala community

- Get familiar with Scala ecosystem

---

class: center, middle, inverse

# 1 - Scala Basics

---

## Why Scala?

- Multi-paradigm language

- Object-oriented and functional

- Statically typed

- Scalable from simple scripts to sophisticated (but still easy-to-use) libraries

- Invented by Martin Odersky at EPFL

- Works with Java VM tools/libraries/infrastructure

- Used for big-iron projects in industry

- Pragmatic choice as successor to Java (whose evolution has slowed to a crawl)

- Expands your mind and makes you think differently about programming in any language.
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

## Values, variable  and fundamental data type

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

## For Loops

-   No three-part loop `for (i = 1; i <= n; i++)`
-   Use “for each” loop instead (**Note**: No `val`/`var` before `i`):
```
    for (i <- 1 to n)
```
-   Works for any collection:
```
    for (ch <- "Hello")
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

-   Consider this
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
      package mypackage {
        package impatient {
          class Employee
          ...
        }
      }
    }
```
    Fully qualified name: `com.mypackage.impatient.Employee`

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
      package mypackage {
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
    `com.mypackage.App` or `scala.App`.

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
class: center, middle, inverse

# 8 - Implicits

---

## Implicit Conversion

- *Marking rule*: Only definitions marked implicit are available

```scala
implicit def intToString(x: Int) = x.toString
```

- *Scope rule*: An inserted implicit conversion must be in scope as a single identifier, or be associated with the source or target type of the conversion

```
object Dollar {
    implicit def dollarToEuro(x: Dollar): Euro = ...
}

class Dollar { ... }
```

- *One-at-a-time rule*: Only one implicit is inserted.
The compiler will never rewrite `x + y`
```scala
convert1(convert2(x)) + y
```
---

## Implicit parameters

```scala
  class PreferredPrompt(val preference: String)
```

Method with implicit parameters

```scala
object Greeter {
    def greet(name: String)(implicit prompt: PreferredPrompt) = {
      println("Welcome, " + name + ". The system is ready.")
      println(prompt.preference)
    }
}
```

To let the compiler supply the parameter implicitly, you must first define a variable of the expected type, which in this case is `PreferredPrompt`

```scala
object JoesPrefs {
    implicit val prompt = new PreferredPrompt("Yes, master> ")
}
```

---

# Referece

- **Book** [_Scala for the Impatient_ by Cay S. Horstmann, 2012](https://www.goodreads.com/book/show/11335855-scala-for-the-impatient)

- **Slides** ["Scala for the Impatient"](http://horstmann.com/scala/livelessons/)
