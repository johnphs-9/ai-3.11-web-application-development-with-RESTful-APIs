package sg.edu.ntu.simple_crm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    // private final Customer customer;
    @Autowired
    private Customer customer;

    // @Autowired
    // public CustomerController(Customer customer) {
    // this.customer = customer;
    // }

    @GetMapping("/customer")
    public Customer getCustomer() {
        logger.info("Fetching customer information");
        return customer;
    }
}
