import groovy.json.*

class PrintValueMethodCategory {
    static def cmd(String line) {
        println "[\$] $line"
		def process = line.execute()
        process.waitFor()
        if(process.exitValue()) {
           println "ERROR: \n=======\n"
           println process.err.text
           return //System.exit(1)
        }
        def output = process.text
        output
    }
	static def json(String s) {
		def kv = new JsonSlurper().parseText(s)    	
    }
    static def print(String s, String msg="") {
        println msg + s
        return s
    }

    static def pp(String s) {
        println JsonOutput.prettyPrint(s)
        return s
    }

}
String.metaClass.mixin(PrintValueMethodCategory)
def step(String msg) {
    println ""
    println "="*msg.size()
    println msg
    println "="*msg.size()
}



def base_uri="https://demo.consul.io"
line = "curl --insecure  \"${base_uri}/v1/kv/?recurse\""
json =  line
        .cmd()
        .pp()
        .json()[2]["Key"]


println json
/*
    .cmd()
    //.pp()
    .json()[0]["Key"].print("Key: ");

/*
def json = new JsonSlurper().parse('http://api.icndb.com/jokes?exclude=[nerdy]'.toURL())

def writer = new StringWriter()  // html is written here by markup builder
def markup = new groovy.xml.MarkupBuilder(writer)  // the builder
markup.html{
    table {
        json.value.findAll {it.id % 50 == 0 }.each {
            def joke = it.joke
            def id = it.id
            tr {
                td(class:"row", id)  
                td(class:"row", joke)  
            }
        }
    }
}
 

def file1 = new File('groovy1.html')
file1 << writer.toString()
*/