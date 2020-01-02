val words = List("Here","is","sentence","one",".", "The","third","sentence","is","empty","!")

words.groupBy(_.substring(0,1))

words.groupBy(_.length)