## Lab

- Part 1: The Scala Worksheet

- Part 2: Functions

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

TODO Add more tasks about Scaladoc usage. different tasks
