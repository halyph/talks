import groovy.json.JsonSlurper

def json = new JsonSlurper().parse('http://api.icndb.com/jokes?exclude=[nerdy]'.toURL())
json.value.findAll {it.id % 100 == 0 } each {
	println """$it.id
$it.joke"""
}