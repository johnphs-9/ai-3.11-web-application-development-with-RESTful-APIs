package sg.edu.ntu.simple_crm;

import org.springframework.stereotype.Component;

/**
 * Customer class representing a customer entity.
 */
// tell Spring Boot that this class is a component that can be injected into
// other classes
@Component
public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;
    private String jobTitle;
    private int yearOfBirth;

    // all-fields constructor
    public Customer(String id, String firstName, String lastName, String email, String contactNo, String jobTitle,
            int yearOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNo = contactNo;
        this.jobTitle = jobTitle;
        this.yearOfBirth = yearOfBirth;
    }

    // no-args constructor
    public Customer() {
        this.id = "Blank id";
        this.firstName = "Blank first name";
        this.lastName = "Blank last name";
    }

    /**
     * Getters and setters for all fields generated using vscode Source Action
     */

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
