# Cons

1. Writing big Swagger Spec is really painful
2. Swagger annotations create a lot of redundant noise in code base
3. Swagger annotations can not be generated via unit tests (see [Spring Rest Docs](https://github.com/spring-projects/spring-restdocs))

# Pros

1. Swagger UI can render spec generated from other **languages**
2. API documentation looks the same for different **languages/frameworks** (Language Agnostic)
3. Swagger supports both approached for API Documentation creation
  1. Top-Down
  2. Bottom-Up
4. Swagger Spec can be rendered by Swagger UI and/or ReDoc
5. Swagger UI can be used for API exploratory analysis