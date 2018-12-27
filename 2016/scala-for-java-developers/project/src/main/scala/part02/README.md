Practice writing functions and using control structures
Write a function that tests whether a character is a vowel (for now, a lowercase aeiou).

def isVowel(ch: Char) = ...
Did you use an if statement? If so, write it without an if. (Hint: contains)

Write a function that, given a string, returns a string of all of its vowels. Use isVowel. Use a for loop.

def vowels(s: String) = ...
Repeat with a for ... yield loop. (Hint: Guards)

Repeat with a recursive solution.
Repeat with a while loop.
Pick your favorite version. Add a parameter vowelChars with default "aeiuo" and a parameter ignoreCase with default true.
Call it to find all vowels in the German word "Übeltätergehör". (Yes, those things with dots are vowels in German.)