def isVowel(ch: Char) = ch == 'a' || ch == 'e' || ch =='i' || ch == 'o' || ch == 'u'

isVowel('x')
isVowel('a')

def isVowel2(ch: Char) = "aeiou".contains(ch)

isVowel2('x')
isVowel2('a')


def vowels(s: String) = {
  var result = ""

  for(ch <- s) {
    if(isVowel2(ch)) result += ch
   }
  result
}

vowels("Ukraine")

def vowels2(s: String) = {
  for(ch <- s if isVowel2(ch)) yield ch
}


vowels2("Ukraine")


def vowels3(s: String): String = {
  if(s.length == 0) ""
  else {
    val ch = s(0)
    val rest = vowels3(s.substring(1))
    if(isVowel2(ch)) ch + rest else rest
  }
}

vowels3("Ukraine")

def vowels4(s: String) = {
  var i = 0
  var result = ""

  while(i < s.length) {
    var ch = s(i)
    if(isVowel(ch)) result += ch
    i += 1
  }

  result
}

vowels4("Ukraine")
