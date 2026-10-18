package sg.edu.ntu.spring_demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class SampleController {

    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Boot World!";
    }

    /*
     * @GetMapping("/greet")
     * public String greet(@RequestParam String name) {
     * return "Greetings " + name + "!";
     * }
     * 
     * @GetMapping("/greet")
     * public String greet(@RequestParam(defaultValue = "Spring Boot World") String
     * name) {
     * return "Greetings " + name + "!";
     * }
     */

    @GetMapping("/greet")
    public String greet(@RequestParam(defaultValue = "Spring Boot World") String name,
            @RequestParam(defaultValue = "human tester") String role) {
        return "Greetings " + name + "! You are a " + role + "!";
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable int id) {
        return "User ID: " + id;
    }

    // Optional "search" query param, e.g. /products?search=apple.
    // required=false so the same route works with or without it.
    @GetMapping("/products")
    public String products(@RequestParam(required = false) String search) {
        // If a search term was supplied, report it; otherwise show the default page.
        if (search != null) {
            return "You have searched for " + search;
        }
        return "This is the product page";
    }

    // Path variable: /products/42 -> id = "42"
    @GetMapping("/products/{id}")
    public String getProductById(@PathVariable String id) {

        logger.info("Product requested with id {}", id);
        logger.warn("This is a test warning");
        logger.error("This is a test error");

        return "You have requested for product with id: " + id;
    }
}
