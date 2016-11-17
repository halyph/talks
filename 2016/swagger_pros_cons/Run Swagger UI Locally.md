# Running with Docker
```bash
docker run -d --name swagguer-ui -p 8888:8888 sjeandeaux/docker-swagger-ui
```

# Run from dist folder
* With Node, globally install package http-server `npm install -g http-server`
* Change directories to where my.json is located, and run the command `http-server --cors` (CORS has to be enabled for this to work)
* Open swagger ui (i.e. `dist/index.html`)
* Type `http://localhost:8080/my.json` in input field and click "Explore"


### References
- [Github repo](https://github.com/swagger-api/swagger-ui)
  - [Github Swagger UI Releases](https://github.com/swagger-api/swagger-ui/releases) 
- [Swagger UI](http://swagger.io/swagger-ui/) 
  - Demo http://petstore.swagger.io/