package sg.edu.ntu.simple_crm_soln;

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
