package kdg.programming6.keepdishesgoing.order.domain;

import kdg.programming6.keepdishesgoing.common.Address;

public class Customer {
    String firstName;
    String lastName;
    String email;
    Address address;

    public Customer(String firstName, String lastName, String email, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }
    public Address getAddress() {
        return address;
    }
    public String getEmail() {
        return email;
    }

}
