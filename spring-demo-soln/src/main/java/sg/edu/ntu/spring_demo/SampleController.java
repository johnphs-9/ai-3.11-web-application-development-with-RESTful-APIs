package sg.edu.ntu.spring_demo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
public class SampleController {

    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.port}")
    private String port;

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring-boot";
    }

    @GetMapping("/app-info")
    public String getAppInfo() {
        return "App " + appName + " is running on port: " + port;
    }

    @GetMapping("/products/{id}")
    public String getProductById(@PathVariable int id) {
               logger.info("Product requested with id {}", id);
               logger.warn("This is a test warn");
               return "You have requested for product with id: "+id;

    }

  @Autowired 
  private SampleItem item;

    @GetMapping("/item")
    public SampleItem getItem() {
       item.setId(1);
       item.setName("Apple");
       item.setPrice(1.99);
       item.setDesc(("A red apple"));
       return item;
    }
    
    

}
