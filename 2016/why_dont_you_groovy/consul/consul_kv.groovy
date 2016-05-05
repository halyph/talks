import groovy.json.JsonSlurper 

/*
Used CURL as a main HTTL client. 
You may consider other clients like RESTClient (https://github.com/jgritman/httpbuilder/wiki/RESTClient)

See https://www.consul.io/intro/getting-started/kv.html
*/
class Consul {
        
    String port
    String host
    String base_uri 
    boolean trace

    Consul (String host, String port) {
        base_uri = "${host}:${port}"
    }

    Consul (String host) {
        base_uri = "${host}"
    }

    def cmd(String line) {
        if (trace) println "CMD $line"
        def process = line.execute()
        process.waitFor()
        if(process.exitValue()) {
           println "ERROR: \n=======\n"
           println process.err.text

           System.exit(1)
        }
        process.text    
    }

    def add(key, val) {
        String line = "curl -X PUT -d ${val} $base_uri/v1/kv/${key}"
        cmd line
    }

    /* list all key-value pairs*/
    def list() {
        String  line = "curl --insecure  \"${base_uri}/v1/kv/?recurse\""
        cmd line 
    }

	/* By default it will delete ALL values!!! */
    def deleteAll(key="") {
        String line = "curl -X DELETE ${base_uri}/v1/kv/${key}?recurse"
        cmd line
    }

    def parseJson(input) {
        def kv = new JsonSlurper().parseText(input)
        //println kv
        kv.each {             
                //println "$it.Key : ${new String(it.Value.decodeBase64())}"
            println "$it.Key : ${new String(it.Value)}"
        }
    }

    def uploadBackup(filename) {
        println "Upload consul KV backup"
        def reader = new FileReader(filename)
        def kv = new JsonSlurper().parse(reader)
        kv.each { 
            def key = it.Key
            def val = new String(it.Value.decodeBase64())
            println "Add: [${key} : ${val}]"
            add(it.Key , val)            
        }
    }
}


def client = new Consul("https://demo.consul.io")

//println client.deleteAll()

//client.add("test/oi/n1", "test:sfsdf")
//client.uploadBackup("ycu_v1.json")
//println client.list()
client.trace = true
client.add("test/oi/n3", "test:N1")
//client.add("test/oi/n2", "test:N2")

//println "\n List"

//println "\n\n List"
//client.trace = true
//println client.list()
//print client.list()
//client.uploadBackup("backup2.json")
//client.deleteAll("")
//client.parseJson(client.list())

//client.add("test/oi/n1", "test:N1")
//client.add("test/oi/n2", "test:N2")



// 1
//curl --insecure -s "https://demo.consul.io/v1/kv/?recurse&dc=ams2" | jq .


//2
//curl -X PUT -d myvalue --insecure  "https://demo.consul.io/v1/kv/test/oi/val&dc=ams2"
