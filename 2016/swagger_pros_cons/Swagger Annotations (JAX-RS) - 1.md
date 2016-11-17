```java
@Path("/user")
@Api(value="/user", description = "Operations about user")
@Produces({"application/json", "application/xml"})

public class UserResource {
  static UserData userData = new UserData();
...
}  
```