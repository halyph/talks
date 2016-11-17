# Running with Docker
```bash
docker pull swaggerapi/swagger-editor
docker run -p 80:8080 swaggerapi/swagger-editor
```

# Using http-server module
```bash
npm install -g http-server
wget https://github.com/swagger-api/swagger-editor/releases/download/v2.10.4/swagger-editor.zip
unzip swagger-editor.zip
http-server swagger-editor
```

# Building From Source
```bash
git clone https://github.com/swagger-api/swagger-editor.git
cd swagger-editor
npm install
npm start
```

### References:
- https://github.com/swagger-api/swagger-editor/#running-locally
- [Swagger Editor](http://swagger.io/swagger-editor/)
  - Demo http://editor.swagger.io/#/?import=http://petstore.swagger.io/v2/swagger.json