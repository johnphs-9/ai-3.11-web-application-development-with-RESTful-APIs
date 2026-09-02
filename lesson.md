# Lesson: Web Application Development with RESTful APIs

## Lesson Overview :
This lesson introduces students to modern web application development using RESTful APIs. Students will learn the fundamental principles of REST architecture, API design best practices, and how to build backend services that can be consumed by multiple client applications (web, mobile, desktop). Using Spring Boot as the implementation framework, students will understand the API-centric approach where frontend and backend are developed separately and communicate via HTTP. The lesson covers project setup, routing, request handling, dependency injection, and the Model-View-Controller (MVC) architectural pattern. By the end of this lesson, students will be able to build a functional RESTful API backend that follows industry best practices and can serve as the foundation for full-stack applications.

---

## Lesson Objectives

By the end of this lesson, students will be able to:

1. **Explain** the API-centric approach and the core principles of REST architecture
2. **Set up** and run a Spring Boot project using Spring Initializr and Maven
3. **Create** RESTful endpoints with query parameters and path variables following REST design guidelines
4. **Apply** the MVC pattern by separating concerns into a dedicated Controller class
5. **Implement** Dependency Injection using `@Component` and `@Autowired`

---

## Part 1: API-Centric Approach

In a modern application, we usually separate our frontend and backend. The frontend is a web application that is built using React, Vue, Angular, etc.

The backend is usually a server that is built using a framework such as Spring Boot, NodeJS/Express, etc.

The frontend and backend communicate with each other via an API.

This is known as an API-centric approach. The frontend (user interface) and backend (data and logic) are developed separately and the API is the primary means of communication between them.

Spring Boot Backend API can be consumed by:

- ReactJS Frontend
- ReactNative Frontend
- Native Windows App
- Native MacOS App

<img src="./assets/images/api-centric-app.png" width=600 />

The API can be consumed by other applications such as mobile apps, desktop apps, etc, thus allowing us to reuse the backend code while catering to different platforms and devices.

---

## Part 2: REST API

We have been using the term **API** loosely to refer to a REST API e.g. loading data using GET request, creating data using POST request, updating data using PUT request, deleting data using DELETE request, etc.

As a matter of fact, the term API emerged long before REST APIs were invented, sometime in the 1960s.

API stands for **A**pplication **P**rogramming **I**nterface. It defines how two software components should interact with each other.

There are many types of APIs such as

- Library APIs,
- Operating System APIs,
- Hardware APIs,
- Web APIs,
- and more

When we write a Java class with public methods, we are creating a Library/Class API for other Java classes to use.

A **REST API** (also known as RESTful API) is a web API that conforms to the constraints of the REST architectural style. REST stands for representational state transfer and was created by computer scientist Roy Fielding to guide the design and development of the architecture for the World Wide Web.

REST architectural style is based on the following 6 constraints:

1. Client-server architecture

- separation UI concerns from data. Client and server can be developed independently.

2. Stateless

- Each request from client to server must contain all of the information necessary to understand the request, and cannot take advantage of any stored context on the server. This simplifies the server components because no session information needs to be stored between requests.

3. Cacheable

- Responses can be marked as cacheable or not cacheable. If a response is cacheable, then a client cache is given the right to reuse that response data for later, equivalent requests.

4. Uniform interface

- a uniform way to manipulate resources (data) through their representations

5. Layered system

- proxy servers, load balancers, etc can be added to improve scalability

6. Code on demand (optional)

- Servers can temporarily extend or customize the functionality of a client by transferring executable code.

---

## Part 3: REST API Design Guidelines

### Define Endpoints using Nouns

APIs are about accessing resources. A resource is basically just information or data e.g. users, expenses, products, etc. We specify `HTTP` methods to access the resources e.g. `GET`, `POST`, `PUT`, `DELETE`, etc.

In our backend, we will define endpoints that will allow us to access the resources e.g.
```
www.example.com/api/users
www.example.com/api/expenses
www.example.com/api/products
```

We should use plural **nouns** instead of verbs in the endpoints. The HTTP methods (verbs) will be used to specify the actions to be performed on the resources.
```
GET www.example.com/api/users // ✅ Get all users
POST www.example.com/api/users // ✅ Create a new user
GET www.example.com/api/users/5 // ✅ Get the user with id 5
PUT www.example.com/api/users/5 // ✅ Update the user with id 5
DELETE www.example.com/api/users/5 // ✅ Delete the user with id 5
```

Notice the pattern: the **collection** endpoint (`/api/users`) is used to get all users or create a new one. The **single resource** endpoint (`/api/users/5`) is used to read, update or delete one specific user.

Update and delete always target a specific resource. We do not expose `DELETE /api/users`, because that would mean "delete every user" — not something a real API should allow on a collection URL.

Do not use verbs in the endpoints.
```
GET www.example.com/api/getUsers ❌
POST www.example.com/api/addUser ❌
PUT www.example.com/api/updateUser ❌
DELETE www.example.com/api/deleteUser ❌
```

### Use JSON for data exchange

The recommended data exchange format for REST APIs is JSON. JSON stands for JavaScript Object Notation. It is a lightweight data-interchange format that is easy for humans to read and for machines to parse and generate.

### Stateless

Stateless means that the server does not store any state about the client session on the server side. The client is responsible for sending its state to the server. For example, if the client needs to browse page 5 of a list of users, the client will send this information to the server and the server will return the list of users starting from page 5.
```
GET www.example.com/api/users?page=5
```

The server does not store any information about the client session. If the client wants to access page 6, it will have to send the page number to the server again.

### Versioning

APIs are usually versioned to allow for backward compatibility. If we make improvements to the API, we can create a new version of the API. At the same time, the older version can still be maintained to support existing clients.
```
www.example.com/api/v1/users
www.example.com/api/v2/users
```

### HTTP Status Codes

HTTP status codes are used to indicate the status of the request. For example, if the request is successful, the server will generally return a status code of `200`. If the request is not successful, the server will return a status code of `400` or `500`.

We should return the appropriate status code for each request.

The list of HTTP status codes can be found [here](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status).

---

## Part 4: What is Spring Boot

Java Spring Boot is a framework that allows us to build web applications quickly and easily. It is built on top of the Spring framework and provides a simpler and faster way to set up, configure, and startup web-based applications.

### Opinionated vs Non-opinionated Framework

Java Spring Boot is an **opinionated** framework. This means that it provides a set of predefined conventions, best practices, and structures that developers are expected to follow. This allows us to focus on building the application and reduces the number of decisions to make.

This is in contrast to a **unopinionated** library such as React, where we have to make a lot of decisions on how to structure the application. Developers have to decide on the folder structure, the libraries to use, etc. It does not enforce any conventions or best practices. As a result , the codebases of non-opinionated libraries tend to be more diverse and inconsistent.

### Inversion of Control (IoC) and Dependency Injection (DI)

Spring Boot uses the **Inversion of Control (IoC)** principle to manage the dependencies of the application. Inversion of Control means that the control of the application is inverted.

In traditional programming, our code directly controls the creation and management of objects. e.g. :
```java
public class MyApplication {
  public static void main(String[] args) {
    // Create a new object
    MyObject myObject = new MyObject();
    // Call a method on the object
    myObject.doSomething();
  }
}
```

With IoC, instead of the application controlling the objects, the objects are controlled by the framework.

Spring Boot achieves this by using Dependency Injection (DI). This is done by injecting the dependencies i.e., the objects that a class depends on, into the class. This is in contrast to the class creating the dependencies itself as in the example above.

We will see this in action later.

### Creation of Spring Project via Initializr

> **Note on versions:** We will use the **current stable release of Spring Boot offered by Spring Initializr** (4.x at the time of writing). Do not pin to an exact version number — Initializr updates its list regularly, and an older version may no longer be available in the dropdown. Spring Boot 4.x requires Java 21, which is the version we use in this module.

First, let's install the [Spring Boot Extension Pack](https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack) in VS Code. Restart VS Code after installation.

We can create a new Spring Boot Project using Spring Initializr. This can be done using the Spring Boot Extension Pack or by going to [Spring Initializr](https://start.spring.io/).

1. Open your Command Palette (Ctrl/Cmd + Shift + P) and type in "Spring Initializr" and select "Create a Maven Project".
1. Choose the latest stable version offered (do not choose a SNAPSHOT or milestone version).
1. Choose Java as language.
1. The `groupId` identifies your organization. Usually we use the reverse domain name of your organization e.g. `sg.edu.ntu`.
1. The `artifactId` is the name of your application. The convention is use lowercase and hyphenate multiple words e.g. `spring-demo`.
1. For packaging type, choose `jar`.
1. For Java version, choose `21`.

When prompted to choose dependencies, select these **two**:

- **Spring Web** — everything needed to build a web application, including the embedded Tomcat server
- **Spring Boot DevTools** — automatically restarts the application when you save a change

Press Enter/Continue when both are selected.

> **Note — display name vs artifactId:** The picker lists it as **"Spring Web"**, but if you open `pom.xml` afterwards you will see the artifact it actually added is `spring-boot-starter-webmvc`. "Spring Web" is the human-readable name of the feature and has been kept unchanged for years; `spring-boot-starter-webmvc` is the Maven coordinate, which was renamed in Spring Boot 4. The same pattern applies elsewhere: "Spring Data JPA" adds `spring-boot-starter-data-jpa`. The label in the picker never matches the Maven coordinate exactly — `pom.xml` is the source of truth.

> **Why we select dependencies now rather than later:** a Spring Boot project generated with no dependencies at all will not build. Spring Initializr always creates a test class (`SpringDemoApplicationTests.java`), and with no dependencies there is no test library for it to compile against. Because `mvn spring-boot:run` compiles the test sources before starting the application, the build fails before your app ever runs.

Select the folder to save the project, and open the project in VS Code.

### Structure of a Spring Boot Project

A Spring Boot project is a Maven project. Maven is a build automation tool used primarily for Java projects. It is used to manage the dependencies of the project and to build the project.

The structure of a Spring Boot project is as follows:
```
spring-demo
├── src
│   ├── main
│   │   ├── java
│   │   │   └── sg
│   │   │       └── edu
│   │   │           └── ntu
│   │   │               └── spring_demo
│   │   │                   └── SpringDemoApplication.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java
│           └── sg
│               └── edu
│                   └── ntu
│                       └── spring_demo
│                           └── SpringDemoApplicationTests.java
├── target
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

- `src` : source code of the application.
- `main` : functional code of the application.
- `test` : test code.
- `target` : compiled code. This folder is created when we compile the code.
- `resources` : resources such as images, etc.
- `application.properties` file : configuration properties for the application.
- `pom.xml` file : the configuration for Maven.
- `mvnw` and `mvnw.cmd` files: Maven wrappers
- `README.md` file : documentation for the project.

### Running the Spring Boot Application

Every application has an entry point. For Spring Boot, the entry point is the `main` method in the `SpringDemoApplication.java` file (the file is named after your `artifactId`, so it will differ if you named your project something else).
```
src/main/java/sg/edu/ntu/spring_demo/SpringDemoApplication.java
```

Notice that the initial code is annotated with `@SpringBootApplication`. This instruments the class to be the entry point for the Spring Boot application.
```java
@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

}
```

We can run the Spring Boot application using the `mvn` command in the terminal. If you look at the project folder, you will also see a `mvnw` file. This is a Maven wrapper that allows us to run Maven commands without having to install Maven on our computer. Both commands will work.

> **Note:** Maven commands must be run from the folder that contains `pom.xml`. If you get "there is no POM in this directory", `cd` into your project folder first.

To run the Spring Boot app:
```bash
mvn spring-boot:run
```

You can also use the Maven wrapper:
```bash
# For Bash (WSL)
./mvnw spring-boot:run
# For PowerShell
.\mvnw spring-boot:run
# For Windows Command Prompt
mvnw spring-boot:run
```

This compiles the code and runs the application.

We can specify the `clean` option to clean the target folder before compiling the code. This is useful if you have made changes to the code and want to recompile the code from scratch. Sometimes, this fixes some random issues with running the application.
```bash
mvn clean spring-boot:run
```

> **Note:** While the application is running, that terminal is busy — anything you type into it will be ignored. To run another command, open a second terminal, or stop the app first with `Ctrl+C`.

### Dependencies

Dependencies are external libraries that our application depends on. For example, if we want to use the Spring Web library, we need to add the dependency to our project.

We can search for dependencies in [Maven Central](https://mvnrepository.com/).

Dependencies are specified in the `pom.xml` file. The `pom.xml` file is the Maven Project Object Model (POM) file. It is an XML file that contains information about the project and configuration details used by Maven to build the project.

Because we selected **Spring Web** and **Spring Boot DevTools** in Spring Initializr, those dependencies are already in our `pom.xml`. Open the file and look at the `<dependencies>` section.

You will see the web starter:
```xml
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webmvc</artifactId>
  </dependency>
```

Notice there is no `<version>` tag. The version is inherited from the `<parent>` tag at the top of the file, which is why every Spring Boot library in your project stays on matching versions. If you ever copy a dependency from Maven Central, omit its `<version>` tag for the same reason.

> **Note on the starter name:** In Spring Boot 3.x and earlier, this starter was called `spring-boot-starter-web`. Spring Boot 4 renamed it to `spring-boot-starter-webmvc`, to distinguish Spring MVC from Spring WebFlux. The old name still resolves, but it is deprecated and will be removed in a future release. Most online tutorials and older Stack Overflow answers still show `spring-boot-starter-web` — if you see that, it is the same starter under its old name.

> Sidenote: you can install an [extension](https://marketplace.visualstudio.com/items?itemName=redhat.vscode-xml) for XML formatting in VS Code. This will help you to format the XML nicely.

The `groupId` identifies the group of the dependency while the `artifactId` identifies the artifact (library) of the dependency.

The `spring-boot-starter-webmvc` dependency is a **starter** dependency. A starter is not one library — it is a bundle that pulls in everything needed for a particular job. This one brings in Spring MVC, the Jackson library for JSON conversion, and the Tomcat web server, among others. That is why our `pom.xml` is so short while the application has so much available to it.

By having this dependency, we are embedding the Tomcat web server into our application. This is also what keeps the application running: without a web server there is nothing listening for requests, so the program would simply start and exit.

You will also see the DevTools dependency:
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-devtools</artifactId>
  <scope>runtime</scope>
  <optional>true</optional>
</dependency>
```

DevTools automatically restarts the application when you save a change, so you do not have to stop and start it manually. `runtime` means it is not needed to compile your code, only to run it. `optional` stops DevTools being passed on to anything that later depends on your application — which matters because DevTools must never end up in a production build.

> **Note (WSL Users):** For auto-restart to work in VS Code, ensure the following are enabled:
> - `File → Auto Save` in VS Code
> - `Java › Autobuild: Enabled` under `Ctrl + ,` settings
>
> If auto-restart still does not trigger, manually restart with `Ctrl+C` then `mvn spring-boot:run`.

Try starting the app now.

Open your browser and access the app at `localhost:8080`. You will see a Whitelabel Error Page, which means that it is working correctly. This is because we have not defined any routes yet.

We can add a placeholder page by adding an `index.html` file in the `resources/static` folder.

To change the port number, we can specify the port number in the `application.properties` file. The default port number is `8080`.
```
# Server Configuration
server.port=8081
```

You will need to restart the app for the changes to take effect.

### Central vs Local Maven Repository

Maven downloads the dependencies from a central repository. The central repository is located at [https://repo.maven.apache.org/maven2/](https://repo.maven.apache.org/maven2/). This is the default repository that Maven uses.

If you are using Maven for the first time, it will take some time to download the dependencies from the central repository. Subsequent downloads will be faster as the dependencies are cached locally.

When you run your application, Maven looks for the dependencies in the local repository first. If it cannot find the dependencies, it will download the dependencies from the central repository.

You can check out your local Maven repository at `C:\Users\<username>\.m2\repository` (Windows) or `~/.m2/repository` (Mac/Linux).

### Adding a Dependency Manually

Selecting dependencies in Spring Initializr is convenient, but it only works at the moment you create the project. Once a project exists, any new library has to be added to `pom.xml` by hand — which is what you will do for the rest of this module.

The process is:

1. Search for the library on [Maven Central](https://mvnrepository.com/).
2. Copy the Maven XML snippet.
3. Paste it inside the `<dependencies>` block in `pom.xml`.
4. If it is a Spring Boot library, delete the `<version>` tag so the version is inherited from `<parent>`.
5. Save. Maven downloads the library the next time you build.

We will do this for real in a later lesson when we add a database. For now, the two dependencies Initializr gave us are all we need.

---

## Part 5: Basic Routing

A route means the path to a resource. For example, the route to the homepage is `/` and the route to the about page is `/about`.

In our application class file, annotate it with `@RestController` to tell Spring Boot that this is a controller class that handles web requests.
```java
@SpringBootApplication
@RestController
public class SpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

}
```

When you annotate a class with `@RestController`, it becomes the entry point for all web requests. This means that any object you return from this class will be serialized i.e., converted to JSON and returned to the client.

Under the hood, Spring Boot uses the Jackson library to convert the object to JSON for us.

> **Note:** We are putting routes in the main application class only to get started quickly. This is not how we would organise a real project — in Part 6 we will move them into a dedicated controller class.

### Basic Mapping

Let's add our first route. The route we want to define is `/hello`.

We will use the `@GetMapping` annotation to specify the route. The `@GetMapping` annotation is a shortcut for `@RequestMapping(method = RequestMethod.GET)`. This annotation tells Spring Boot that this method will handle GET requests to the `/hello` route.
```java
@GetMapping("/hello")
public String hello() {
  return "Hello World!";
}
```

### Accessing Endpoints

There are few ways to access the endpoints.

1. Open your browser and access the route at `localhost:8080/hello`.
1. Use Postman and send a GET request to `localhost:8080/hello`.
1. Use Thunder Client in VS Code to send a GET request to `localhost:8080/hello`.
1. Use YARC in Chrome to send a GET request to `localhost:8080/hello`.
1. Use the `curl` command in the terminal.
```bash
curl localhost:8080/hello
```

It will be useful to try out the different methods to access the endpoints.

> **Note:** A browser address bar can only send GET requests. To send POST, PUT or DELETE requests, you will need Postman, Thunder Client, YARC or `curl`.

Open your browser and access the route at `localhost:8080/hello`.

In the sidebar, open the Spring Boot dashboard and you can see that the endpoint `/hello` is listed.

### Basic Mapping with Query Parameters

Next let's add another route. The route we want to define is `/greet`. This time, we want to accept a query parameter `name`. This can be done by adding a parameter to the method and annotating it with `@RequestParam`. Spring Boot will automatically map the query parameter to the method parameter.
```java
@GetMapping("/greet")
public String greet(@RequestParam String name) {
  return "Hello " + name + "!";
}
```

Test the route by accessing `localhost:8080/greet?name=Tony`.

The portion after the `?` is known as the **query string** or **query parameters**.

It is used to pass data to the server. The query string is made up of key-value pairs. In this case, the key is `name` and the value is `Tony`.

> **Note:** Query parameter values must have no spaces. If spaces are needed, use `%20` e.g. `localhost:8080/greet?name=Tony%20Stark`.

> **Note:** `@RequestParam` is **required by default**. If you access `localhost:8080/greet` without the parameter, you will get a `400 Bad Request` rather than a null value. Use `defaultValue` (below) or `required = false` to make it optional.

We can add a default value by specifying the `defaultValue` attribute.
```java
@GetMapping("/greet")
public String greet(@RequestParam(defaultValue = "World") String name) {
  return "Hello " + name + "!";
}
```

We can accept additional query parameters by adding additional `@RequestParam` annotations.
```java
@GetMapping("/greet")
public String greet(@RequestParam(defaultValue = "World") String name, @RequestParam(defaultValue = "unknown") String role) {
  return "Hello " + name + "! You are a " + role + "!";
}
```

To pass additional query parameters, we can use the `&` symbol.

Test the route by accessing `localhost:8080/greet?name=Tony&role=inventor`.

### Basic Mapping with Path Variables

A **path variable** is a variable that is part of the route. For example, in the route `/users/1`, the `1` is a path variable.

We can create a dynamic path by adding a path variable to the route.
```java
@GetMapping("/users/{id}")
public String getUserById(@PathVariable int id) {
  return "User ID: " + id;
}
```

Test the route by accessing `localhost:8080/users/1`.

**Path variable or query parameter?** A path variable identifies *which* resource you want, and is mandatory (`/users/5`). A query parameter describes *how* you want it — filtering, sorting, searching, pagination — and is usually optional (`/users?page=5`).

---

# 👨‍💻 Activity (10 minutes)

Add these endpoints to your existing application class.

1. Create an endpoint `localhost:8080/products` that returns the phrase "This is the product page".
1. Create an endpoint `localhost:8080/products/{id}` that returns the phrase "You have requested for product with id: {id}". Replace `{id}` with the actual id.
1. Update the **first** endpoint to also allow for a search term using a query parameter e.g. `localhost:8080/products?search=apple`. Return the phrase "You have searched for {searchTerm}". Replace `{searchTerm}` with the actual search term.

> **Hint for task 3:** This is still **one** method handling `/products`, not a second one. Two methods mapped to the same route and method type will cause the application to fail on startup with an ambiguous mapping error. Make the query parameter optional and decide what to return based on whether it was provided.

---

## Part 6: Separation of Controller file

Currently we have all our routes in the main application file. This is not ideal as the file will become very long and difficult to maintain.

### Introduction to Model View Controller (MVC)

The Model-View-Controller (MVC) is an architectural pattern that separates an application into three main logical components: the model, the view, and the controller.

- **Model**: The model represents the data, and does nothing else. The model does NOT depend on the controller or the view.

- **View**: The view displays the data through the controller, and sends user actions (e.g. button clicks) to the controller. The view does NOT depend on the model.

- **Controller**: The controller provides model data to the view.

You can read more about MVC [here](https://www.freecodecamp.org/news/model-view-controller-mvc-explained-through-ordering-drinks-at-the-bar-efcba6255053/).

In a typical API-centric application, the view is usually a frontend application (e.g. React, Vue, Angular, etc). Our Spring Boot backend has no view of its own — it returns data, and the client application decides how to display it.

In our Spring Boot application, the controller is the entry point for all web requests. It is responsible for handling the request and returning the response.

Thus, we will create a new file called `SampleController.java` and move all the routes to the controller file. Remember to annotate the class with `@RestController`. This annotation tells Spring Boot that this is a controller class.
```java
@RestController
public class SampleController {

}
```

Steps:

1. Create `SampleController.java` in the same package as your application class.
2. Annotate it with `@RestController`.
3. **Move** all the route methods out of the application class and into `SampleController`. Move them — do not copy them.
4. **Remove `@RestController` from the application class.** It no longer handles any routes, so it should go back to having a single job: starting the application.

```java
@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

}
```

> **Warning:** If the same route ends up in both classes — for example because a method was copied instead of moved — the application will **fail on startup** with an ambiguous mapping error. Spring cannot decide which method should handle the request. Check that each route exists in exactly one place.

Notice that the URLs do not change. Spring finds handler methods in any `@RestController` class within your application's package, so which class a route lives in is a matter of organisation for us, not something Spring cares about.

Test the endpoints again to make sure that they are working.

---

## Part 7: `application.properties`

The `application.properties` file is used to store configuration properties for the application. Typically we store properties such as database connection string, server port, etc.
```properties
# Server Configuration
server.port=8081
spring.application.name=Demo Spring Boot Application
```

We can use the `@Value` annotation to access the properties in the file.
```java
@Value("${spring.application.name}")
private String appName;

@Value("${server.port}")
private String port;

@GetMapping("/app-info")
public String getAppInfo() {
  return "App " + appName + " is running on port: " + port;
}
```

> **Note:** If a property does not exist in the file and no default is provided, the application will **fail to start** rather than leaving the field null. This is intentional — a missing configuration value is found at startup instead of causing an unexplained failure later.

Default values can be specified using the `:` symbol.
```java
@Value("${spring.application.name:Demo Spring Boot Application}")
private String appName;

@Value("${server.port:8080}")
private String port;
```

Test the endpoint at `localhost:8080/app-info`. Remember to update the port number if you have changed it.

> **Note:** Never put secrets such as database passwords in `application.properties`, because this file is committed to Git. In production these values come from environment variables or a secrets manager. Spring reads environment variables automatically and they override values in this file.

### Logging

You have been using `System.out.println()` to see what your code is doing. That is fine while learning, but not in a real application. There are no severity levels, no timestamps, you cannot switch it off, and it only ever goes to the console — never to a file.

A logging framework fixes all of that. You attach a **level** to each message, you **filter** by level without touching your code, and you can write to the console and a file at the same time.

**SLF4J and Logback.** SLF4J is the API you write your code against. Logback is the implementation that actually writes the messages.

```
Your code  →  SLF4J (the API)  →  Logback (does the work)
```

The point of that split is that your Java code never changes if you switch to a different logging library. Same idea as coding against `List` instead of `ArrayList`.

Both are already included — `spring-boot-starter` brings them in, so there is nothing to add to `pom.xml`.

### Writing Log Messages

A logger belongs to the class it is in, so we add one to `SampleController` — the class where our endpoints live. This is where logging normally goes in a real project: in the controllers, services and repositories where work actually happens, not in the main application class.

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class SampleController {

    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    @GetMapping("/products/{id}")
    public String getProductById(@PathVariable int id) {
        logger.info("Product requested with id {}", id);
        logger.warn("This is a test warning");
        logger.error("This is a test error");
        return "You have requested for product with id: " + id;
    }
}
```

Call `localhost:8080/products/7` and all three messages appear in the console.

Two things to notice:

- `private static final` — one logger per class, created once. You will write this line at the top of nearly every class in a real project. **Every class that logs declares its own logger.**
- We pass in `SampleController.class`, so the class name appears automatically in every log line and you always know where a message came from.

**Use `{}` placeholders, not string joining.**

```java
logger.info("Product requested with id {}", id);        // ✅
logger.info("Product requested with id " + id);         // ❌
```

With `+`, the message is built every time even when that level is switched off. With `{}`, it is only assembled if the message is actually going to be written. This is the standard habit in production code.

### Logging Levels

| Level | Use for |
|---|---|
| ERROR | Something failed |
| WARN | Something suspicious, but the app continues |
| INFO | Normal progress — the default level |
| DEBUG | Detail useful while developing |
| TRACE | Even finer detail, rarely used |

Levels are hierarchical. Setting the level to `INFO` gives you `INFO`, `WARN` and `ERROR`, but not `DEBUG` or `TRACE`.

### Configuring Logging in `application.properties`

Two properties do most of the work:

```properties
# Logging Configuration
logging.level.root=INFO
logging.file.name=logs/application.log
```

`logs/application.log` is created in your project root, next to `pom.xml`. It appears the first time a log message is written, so run the app and call an endpoint, then open the file — the same lines are in the console and in the file.

> **Note:** `application.properties` is read once at startup. After changing it, stop the app with `Ctrl+C` and run it again — DevTools does not reliably pick up property changes.

You will notice the file also contains Spring Boot's own startup messages — Tomcat starting, the active profile, the port. Those are not special: Spring Boot's own classes use SLF4J exactly as we just did. Since `logging.level.root=INFO` applies to every logger in the application, the framework's messages go to the same destinations as yours. All those startup lines you have been watching scroll past are just log statements.

**Console output disappears when the app stops. The file stays.** Stop the app and restart it — the old lines are still there and new ones are appended below.

Now set the root level to `DEBUG` and restart. You will see an enormous amount of Spring's own output, and your own messages get buried. That is why in practice you set the level for **your package only**:

```properties
logging.level.root=INFO
logging.level.sg.edu.ntu=DEBUG
```

Your code verbose, the framework quiet. This is the line you will actually use at work.

### What Happens to Logs in Production

There are two stages to this, and it helps to keep them apart.

**Stage 1 — the log line gets written.** Some of these lines you write yourself, like the `logger.info` we just added. Others are written for you: Spring Boot's own classes log their startup messages, and in most real projects request logging is set up once in a single place so that every endpoint is logged automatically, rather than each developer adding a log line to every method. Either way, the line ends up in the console and in the log file, exactly as you just saw.

**Stage 2 — the log line leaves the server.** On a real server nobody opens the log file. There is no console to watch, the application may be running as one of ten identical instances, and no one is going to connect to each machine to read a text file. Instead, a small program called a **log agent** runs on the server. Its only job is to watch your log file, pick up each new line as it is written, and send it to a **central monitoring tool** — Splunk, Datadog, CloudWatch, or the ELK stack. Every instance ships to the same place.

The team then works entirely in that tool: searching across all instances at once, filtering by level, building dashboards, and setting up alerts — for example, notify the on-call engineer if more than fifty `ERROR` lines appear in five minutes.

This is why levels matter so much. When an exception is caught, it is logged at `ERROR`. That line reaches the monitoring tool within seconds and an alert fires, without any person having been watching anything. The log file on the server is just the pickup point; the monitoring tool is where logs are actually read.

> **Note:** `application.properties` covers most of what you need. When you want full control — a custom line format, several destinations at once, log files that rotate daily so they never fill the disk — you configure Logback directly using an XML file instead. 

---

## Part 8: Dependency Injection through annotations

Create a `SampleItem.java`.
```java
public class SampleItem {

  private int id;
  private String name;
  private double price;
  private String desc;

  // Add your getters and setters
}
```

> **Note:** The getters are required. Jackson builds the JSON from the getter methods, so a field without a getter will not appear in the response.

In `SampleController.java`, create a new method that returns a `SampleItem`.
```java
// Instantiate a new SampleItem
SampleItem item = new SampleItem();

@GetMapping("/item")
public SampleItem getItem() {
  item.setId(1);
  item.setName("Apple");
  item.setPrice(1.99);
  item.setDesc("A red apple");
  return item;
}
```

Currently, we are creating a new `SampleItem` object in the controller. This is not ideal as we want to separate the creation of the object from the controller. We want to inject the object into the controller. This is known as **dependency injection**. To this, we will use the `@Autowired` annotation instead of the `new` keyword.
```java
@Autowired
private SampleItem item;
```

To use the `@Autowired` annotation, we need to annotate the `SampleItem` class with `@Component`.
```java
@Component
public class SampleItem {
  // ...
}
```

The `@Component` annotation tells Spring Boot that this class is a component that can be injected into other classes.

Test the endpoint at `localhost:8080/item`.

This is how dependency injection works:

1. Annotate the class using `@Component`.
2. Spring Boot will register this class as bean.
3. Use `@Autowired` to inject the bean into the class that depends on it.

> **Important — this example is for learning the annotations, not a pattern to copy.**
>
> Spring beans are **singletons** by default: one instance shared by the whole application, across every request and every thread. Our method calls setters on that shared object, so two users hitting `/item` at the same time would be mutating the same instance and could see each other's data.
>
> The rule: a singleton bean should not hold mutable data that belongs to a single request. Beans should hold behaviour (services, repositories) or configuration that does not change.
>
> In real code, data objects like `SampleItem` are created per request with `new` inside the method, and what you inject are the classes that *do work*. A useful rule of thumb: **inject the things that do work, create the things that hold data.**

> **Note on injection style:** Field injection (`@Autowired` on a field) is shown here because it is the shortest form. Modern Spring code normally uses **constructor injection** instead — the dependency becomes a constructor parameter and the field can be `final`. This means the object can never exist half-constructed, and the class can be tested with plain `new` without Spring.

---

# 👨‍💻 Activity (15 mins)

Use what you have learned to create a **new** Spring Boot project titled `simple-crm`, using Spring Initializr exactly as you did earlier for `spring-demo`.

> **⚠️ Important — this project carries forward.** `simple-crm` is the base project we will build on for the rest of the Spring Boot lessons in this module. Every subsequent lesson starts from it.
>
> **If you do not finish this in class, it becomes homework.** You must have `simple-crm` created and running with the `/customer` endpoint working **before the next lesson**, otherwise you will not be able to follow along.

Set it up with the same choices as before: Maven project, Java, latest stable Spring Boot version, `groupId` `sg.edu.ntu`, `artifactId` `simple-crm`, `jar` packaging, Java 21. When prompted for dependencies, select **Spring Web** and **Spring Boot DevTools**, exactly as you did for `spring-demo`.

Add a Customer POJO class with the following properties:
```java
private String id;
private String firstName;
private String lastName;
private String email;
private String contactNo;
private String jobTitle;
private int yearOfBirth;
```

Remember to add getters and setters — Jackson needs the getters to produce the JSON.

Use the `@Component` annotation to tell Spring Boot that this class is a component that can be injected into other classes.

Create a controller file `CustomerController.java`. Inject the customer bean into the controller using the `@Autowired` annotation.

Create a new endpoint `/customer` that returns a Customer object with some preset values.

Test it at `localhost:8080/customer`. You should see the Customer returned as JSON.

> **Tip:** Stop `spring-demo` before running `simple-crm`, or change the port in `application.properties`. Two applications cannot use port 8080 at the same time — the second one will fail to start with a "port already in use" error.

---

## Part 9: Dev vs Prod Environment (Optional)

> This section is optional and is provided for self-study.

A common and simple way to manage different environments is by using **profile-specific properties files**.

These files are named using the pattern `application-{profile}.properties`.

This could be used to specify different port numbers or database connection strings for the development and production environments.

We can create a new `application-dev.properties` file to store the properties for the development environment.
```properties
# Dev Server Configuration
server.port=8080
```

And create a new `application-prod.properties` file to store the properties for the production environment.
```properties
# Prod Server Configuration
server.port=8081
```

To run the application in development or production mode, we need to specify the profile.
```bash
# Run in Development mode
mvn spring-boot:run -Dspring-boot.run.profiles=dev
# Run in Production mode
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

Spring Boot will load the properties from both the `application.properties` file and the profile-specific properties file. If there are duplicate properties, the profile-specific properties will override the properties in the `application.properties` file.

We can also specify the active profile in the `application.properties` file.
```properties
spring.profiles.active=dev
```

In this case, when we run `mvn spring-boot:run`, it will use the `application-dev.properties` file by default.

---

END