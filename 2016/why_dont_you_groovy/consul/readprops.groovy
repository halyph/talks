import groovy.json.JsonSlurper

def reader = new FileReader(this.args[0])
def kv = new JsonSlurper().parse(reader)

kv.each { 
    println "$it.Key : ${new String(it.Value.decodeBase64())}"
    //println it
}


