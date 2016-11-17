```java
  @GET
  @Path("/{username}")
  @ApiOperation(value = "Get user by user name",
    response = User.class,
    position = 0)
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "Invalid username supplied"),
      @ApiResponse(code = 404, message = "User not found") })
  public Response getUserByName(
      @ApiParam(value = "The name that needs to be fetched. Use user1 for testing. ", required = true) @PathParam("username") String username)
    throws ApiException {
    User user = userData.findUserByName(username);
    if (null != user) {
      return Response.ok().entity(user).build();
    } else {
      throw new NotFoundException(404, "User not found");
    }
  }
```