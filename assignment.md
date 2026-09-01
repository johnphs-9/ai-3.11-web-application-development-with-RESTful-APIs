# Assignment (Optional)

## Brief

Create a Spring Boot project called BookstoreAPI and build a RESTful API for managing books in a bookstore.

1. **Book Management API with Multiple Endpoint Types**
   - Create a `Book` class with the following attributes:
     - `String id`
     - `String title`
     - `String author`
     - `double price`
     - `String isbn`
   - Annotate the `Book` class with `@Component`
   - Create a `BookController` class with `@RestController` annotation
   - Implement the following endpoints:
     - `GET /books` - Returns a welcome message "Welcome to the Bookstore API"
     - `GET /books/{id}` - Returns a message with the book ID using path variable (e.g., "Fetching book with ID: 1")
     - `GET /books/search` - Accepts a query parameter `title` and returns a message (e.g., "Searching for books with title: Java")
     - `GET /books/filter` - Accepts two query parameters: `author` and `maxPrice`, both with default values, and returns a formatted message showing both parameters
     - `GET /book` - Uses dependency injection with `@Autowired` to inject a Book bean and returns a Book object with preset values (set at least 3 attributes)
   - Test all endpoints using a browser, Postman, or Thunder Client
   - Add appropriate default values for query parameters where needed

2. **Library System with MVC Pattern and Configuration**
   - Create a new package structure following MVC pattern:
     - `model` package: Create a `Library` class with attributes: `String name`, `String location`, `int totalBooks`
     - `controller` package: Create a `LibraryController` class
   - Annotate the `Library` class with `@Component`
   - In the `LibraryController`:
     - Use `@Autowired` to inject the Library bean
     - Create an endpoint `GET /library/info` that returns the Library object with preset values
     - Use `@Value` annotation to read from `application.properties`:
       - Read `library.name` property (provide a default value)
       - Read `library.location` property (provide a default value)
     - Create an endpoint `GET /library/config` that returns the library name and location read from properties
   - In `application.properties`, add:
     - Server port configuration (use port 8080)
     - Application name
     - Library name and location properties
   - Add SLF4J logging:
     - Log an INFO message when the application starts
     - Log an INFO message when each endpoint is accessed
   - Configure logging to write to both console and file (`logs/bookstore.log`)
   - Test all endpoints and verify that logs are written correctly



## References
- Java: https://docs.oracle.com/javase/
- Spring Boot: https://docs.spring.io/spring-boot/docs/current/reference/html/
- PostgreSQL: https://www.postgresql.org/docs/
- OWASP: https://cheatsheetseries.owasp.org/