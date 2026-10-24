# Lesson 3.11 — Activity Solutions

---

# Activity 1 — Product Endpoints

## The tasks

1. `GET /products` → "This is the product page"
2. `GET /products/{id}` → "You have requested for product with id: {id}"
3. Update the first endpoint to also handle `/products?search=apple` → "You have searched for apple"

## Solution

These go into your application class, which is annotated with `@RestController`.

```java
@GetMapping("/products")
public String getProducts(@RequestParam(required = false) String search) {
    if (search == null) {
        return "This is the product page";
    }
    return "You have searched for " + search;
}

@GetMapping("/products/{id}")
public String getProductById(@PathVariable int id) {
    return "You have requested for product with id: " + id;
}
```

## Full class for reference

```java
package sg.edu.ntu.spring_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/greet")
    public String greet(@RequestParam(defaultValue = "World") String name,
                        @RequestParam(defaultValue = "unknown") String role) {
        return "Hello " + name + "! You are a " + role + "!";
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable int id) {
        return "User ID: " + id;
    }

    @GetMapping("/products")
    public String getProducts(@RequestParam(required = false) String search) {
        if (search == null) {
            return "This is the product page";
        }
        return "You have searched for " + search;
    }

    @GetMapping("/products/{id}")
    public String getProductById(@PathVariable int id) {
        return "You have requested for product with id: " + id;
    }
}
```

## Expected results

| Request | Response |
|---|---|
| `localhost:8080/products` | This is the product page |
| `localhost:8080/products?search=apple` | You have searched for apple |
| `localhost:8080/products/7` | You have requested for product with id: 7 |

## Explanation

Tasks 1 and 3 are handled by **one method**, not two. Task 3 asks you to *update* the first endpoint, not create a new one.

The important part is `required = false`. By default `@RequestParam` is mandatory, so `/products` on its own would return `400 Bad Request` instead of the product page. Making it optional lets the same method serve both cases — if `search` is null, no search term was given, so return the product page.

## Common mistakes

**Writing two methods for `/products`.** The application will not start. Spring cannot decide which method should handle the request, and you get an ambiguous mapping error at startup. Route plus HTTP method must be unique.

**Forgetting `required = false`.** `/products` returns a 400 error page while `/products?search=apple` works. Nothing else is wrong — the parameter is just still mandatory.

**Naming the parameter something other than `search`.** The task specifies `?search=apple`, so the method parameter must be `String search`. If you name it differently, the endpoint only responds to that other name.

**Using `String id` instead of `int id`.** This works, but `int` is better here — Spring converts the value for you and automatically rejects `/products/abc` with a 400.

---

# Activity 2 — simple-crm

## The task

Create a new Spring Boot project called `simple-crm`, add a `Customer` POJO as a component, and create a `CustomerController` that injects it and returns it from a `/customer` endpoint.

## Project setup

| Setting | Value |
|---|---|
| Project | Maven |
| Language | Java |
| Spring Boot | latest stable |
| groupId | `sg.edu.ntu` |
| artifactId | `simple-crm` |
| Packaging | jar |
| Java | 21 |
| Dependencies | Spring Web, Spring Boot DevTools |

## Solution — `Customer.java`

```java
package sg.edu.ntu.simple_crm;

import org.springframework.stereotype.Component;

@Component
public class Customer {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;
    private String jobTitle;
    private int yearOfBirth;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
```

> **Tip:** Do not type all the getters and setters by hand. In VS Code, right-click inside the class → **Source Action** → **Generate Getters and Setters**.

## Solution — `CustomerController.java`

```java
package sg.edu.ntu.simple_crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    private Customer customer;

    @GetMapping("/customer")
    public Customer getCustomer() {
        customer.setId("C001");
        customer.setFirstName("Tony");
        customer.setLastName("Stark");
        customer.setEmail("tony.stark@starkindustries.com");
        customer.setContactNo("91234567");
        customer.setJobTitle("Chief Executive Officer");
        customer.setYearOfBirth(1970);
        return customer;
    }
}
```

## `SimpleCrmApplication.java` — unchanged

```java
package sg.edu.ntu.simple_crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleCrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleCrmApplication.class, args);
    }
}
```

## Expected output

`GET localhost:8080/customer`

```json
{
  "id": "C001",
  "firstName": "Tony",
  "lastName": "Stark",
  "email": "tony.stark@starkindustries.com",
  "contactNo": "91234567",
  "jobTitle": "Chief Executive Officer",
  "yearOfBirth": 1970
}
```

## Explanation

Three steps make the dependency injection work:

1. `@Component` on `Customer` tells Spring to create an instance of this class at startup.
2. Spring registers that instance as a bean in the application context.
3. `@Autowired` in the controller tells Spring to hand that instance over.

Notice there is no `new Customer()` anywhere. That is the point of the exercise — Spring creates the object, not your code.

The JSON conversion is automatic. You return a `Customer` object and the client receives JSON, because Jackson is included with the web starter and converts it for you.

## Checklist

- [ ] Project named `simple-crm`, groupId `sg.edu.ntu`
- [ ] `Customer` annotated with `@Component`
- [ ] All seven fields present, with `yearOfBirth` as `int`
- [ ] Getters **and** setters for every field
- [ ] `CustomerController` annotated with `@RestController`
- [ ] `Customer` injected with `@Autowired`, no `new` keyword used
- [ ] `@GetMapping("/customer")` returns the `Customer` object
- [ ] Endpoint returns JSON with all seven fields filled in

## Common mistakes

**"Port 8080 was already in use."** Your `spring-demo` project is still running. Stop it with `Ctrl+C`, or set `server.port=8081` in `application.properties` for this project.

**The response is an empty `{}`.** Your class has no getters. Jackson builds the JSON from getter methods, not from the fields directly, so a class without getters produces nothing.

**Some fields are missing from the JSON.** A getter is named incorrectly. Jackson takes the property name from the getter, so `getFirstname()` produces `"firstname"` and a mistyped getter changes or hides the field.

**"Required a bean of type Customer that could not be found."** The `@Component` annotation is missing from `Customer`, so Spring never created it and has nothing to inject. Note that this fails when the application starts, not when you call the endpoint.

**Wrong `Component` import.** It must be `org.springframework.stereotype.Component`. VS Code sometimes suggests a different one.

**Still using `new Customer()`.** The endpoint will work, so this is easy to miss — but the whole point of the activity is to let Spring create the object.

**Classes placed outside the main package.** Everything must sit inside `sg.edu.ntu.simple_crm` or a package below it. Spring only scans from the application class downwards, so a class outside that will never be found.
