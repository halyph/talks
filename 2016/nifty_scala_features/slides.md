class: center, middle, inverse
# Nifty [@Scala](https://twitter.com/scala_lang) Features

### 2016-12-14


## [@halyph](http://twitter.com/halyph)

## I ❤️ Scala

---

# Disclaimer

It's **not** about:

- Functional Programming 
- _Scala_ vs _Java_
- Frameworks

It's **only** about _small_ and _nitfy_ language features

---

# Semicolons and Return

- Scala does not require semicolons to end statements

- The `return` operator is unnecessary in a function (although allowed); the value of the last executed statement or expression is normally the function's value.

```scala
// Scala: Direct conversion from Java

// no import needed; scala.math
// already imported as `math`
def mathFunction(num: Int): Int = {
  var numSquare: Int = num*num
  return (math.cbrt(numSquare) + math.log(numSquare)).asInstanceOf[Int];
}
```

```scala
// Scala: More idiomatic
// Uses type inference, omits `return` statement,
// uses `toInt` method, declares numSquare immutable

import math._
def intRoot23(num: Int) = {
  val numSquare = num*num
  (cbrt(numSquare) + log(numSquare)).toInt
}
```


---

# String Interpolation

- String Interpolation allows users to embed variable references directly in processed string literals

```scala
val name = "James"
println(s"Hello, $name")  // Hello, James
```

- Any arbitrary expression can be embedded in ${}

```scala
println(s"1 + 1 = ${1 + 1}")
```

- The `f` Interpolator. Prepending f to any string literal allows the creation of simple formatted strings, similar to printf in other languages

```scala
val height = 1.9d
val name = "James"
println(f"$name%s is $height%2.2f meters tall")  // James is 1.90 meters tall
```


---


# Multiline string

```scala
val foo = """Line 1.
Line 2.
Line 3."""

println(foo)

//Line 1.
//Line 2.
//Line 3.
```


```scala
val foo = """Line 1.
             Line 2.
             Line 3."""

println(foo)

//Line 1.
//             Line 2.
//             Line 3.
```


---

# Tuples

```
scala> val stuff = (42, "fish")
stuff: (Int, java.lang.String) = (42,fish)

scala> stuff.getClass
res0: java.lang.Class[_ <: (Int, java.lang.String)] = class scala.Tuple2
```

```
scala> val things = ("a", 1, 3.5)
things: (java.lang.String, Int, Double) = (a,1,3.5)

scala> println(things._1)
a

scala> println(things._2)
1

scala> println(things._3)
3.5
```

---

# Tuples (cont.)

```
scala> def getUserInfo = ("Al", 42, 200.0)
getUserInfo: (java.lang.String, Int, Double)

scala> val (name, age, weight) = getUserInfo

scala> name
res4: java.lang.String = Al

scala> age
res5: Int = 42

scala> weight
res6: Double = 200.0

scala> val t = ("Al", 42, 200.0)

scala> t.productIterator.foreach(println)
Al
42
200.0
```

---

# Expressions, Not Statements

```scala
var result = ""
if(marks >= 50)
  result = "passed"
else
  result = "failed"
  
println("Your results just came in, you " + result + ".")
```

## vs

```scala
println("Your results just came in, you " + (if(marks >= 50) "passed" else "failed") + ".")
```

---


# Values and variables

- `val` defines an immutable value

- `var` defines a mutable value

```
val valVariable = 8 * 4 + 3
valVariable = 10  // Error

var varVariable = 12
varVariable = 44
```

---

# Imports

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

-   Imports can be *anywhere*:
```
    class Manager {
      import scala.collection.mutable._
      val subordinates = new ArrayBuffer[Employee]
      ...
    }
```
-   `java.lang`, `scala`, `Predef` are always imported


---

# Default Parameter Values

- Scala provides the ability to give parameters default values that can be used to allow a caller to omit those parameters.

```scala
class HashMap[K,V](initialCapacity:Int = 16, loadFactor:Float = 0.75f) {
}
// Uses the defaults
val m1 = new HashMap[String,Int]

// initialCapacity 20, default loadFactor
val m2= new HashMap[String,Int](20)

// overriding both
val m3 = new HashMap[String,Int](20,0.8f)

// override only the loadFactor via
// named arguments
val m4 = new HashMap[String,Int](loadFactor = 0.8f)
```

---
# Named Parameters 

- When calling methods and functions, you can use the name of the variables explicitly in the call, like so:

```scala
  def printName(first:String, last:String) = {
    println(first + " " + last)
  }
  
  
  printName("John","Smith")
  // Prints "John Smith"
  printName(first = "John",last = "Smith")
  // Prints "John Smith"
  printName(last = "Smith",first = "John")
  // Prints "John Smith"
```

- Note that once you are using parameter names in your calls, the order doesn’t matter, so long as all parameters are named. This feature works well with default parameter values:

```scala
 def printName(first:String = "John", last:String = "Smith") = {
    println(first + " " + last)
  }
  printName(last = "Jones")
  // Prints "John Jones"
```

---

# Nested Methods

```scala
def sum(xs: List[Int]): Int = {
  // Nested helper method
  def helper(accu: Int, rest: List[Int]): Int = rest match {
    case Nil => accu
    case _   => helper(accu + rest.head, rest.tail)
  }

  // Call the nested helper method
  helper(0, xs)
}
```

---

# Methods can have multiple parameter lists

```scala
// A method with two parameter lists
def add(x: Int)(y: Int) = x + y
 
// The method add2 has no parameter list; it fills in
// only the first parameter list of the method add
def add2 = add(2) _
 
// Call add2, which returns a function that takes one parameter,
// then call that function with the argument 3
val result = add2(3)
```

---

# Traits

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

# Mixins


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

# Mixins (Cont.)

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

# Layers

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

# Objects instead of static methods

```
class Hello(name: String) {
  def sayHello = println("Hello, " + name + "!")
}
 
// Companion object of class Hello
object Hello {
  // Factory method
  def apply(name: String) = new Hello(name)
}
```

- The `apply` method is treated in a special way by Scala; there’s a special shortcut syntax to call it.

```
val hello1 = Hello.apply("World")
 
// This is the special shortcut syntax, which is equivalent to the above
val hello2 = Hello("World")
```

---

# Operators are just methods

```
val a = 23
val b = 65
 
// + is a method in class Int, which can be called using
// the regular method call syntax...
val sum1 = a.+(b)
 
// ... or using the infix syntax
val sum2 = a + b
 
// You can use infix syntax with any method that takes one parameter
val result = List(2, 5, 13, 8) contains 4
```

---

# Type Inference

```
object InferenceTest1 extends App {
  val x = 1 + 2 * 3         // the type of x is Int
  val y = x.toString()      // the type of y is String
  def succ(x: Int) = x + 1  // method succ returns Int values
}
```

```
object InferenceTest2 {
  def fac(n: Int) = if (n == 0) 1 else n * fac(n - 1)
}
```

```
case class MyPair[A, B](x: A, y: B);
object InferenceTest3 extends App {
  def id[T](x: T) = x
  val p = MyPair(1, "scala") // type: MyPair[Int, String]
  val q = id(1)              // type: Int
}
```

The last two lines of this program are equivalent to the following code where all inferred types are made explicit:

```
val x: MyPair[Int, String] = MyPair[Int, String](1, "scala")
val y: Int = id[Int](1)
```
---

# Referece

- [Wikipedia](http://bit.ly/2f8xVC6)
- [A Tour of Scala](http://docs.scala-lang.org/tutorials/)
- [7 Scala features that surprise Java developers](http://www.jesperdj.com/2015/11/08/7-scala-features-that-surprise-java-developers/)