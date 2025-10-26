package kdg.programming6.keepdishesgoing.order.port.in.basket;

import kdg.programming6.keepdishesgoing.common.Address;
import kdg.programming6.keepdishesgoing.order.domain.Customer;

import java.util.UUID;


public record CheckoutBasketCommand(
        UUID basketId,
        String firstName,
        String lastName,
        String email,
        String street,
        Integer number,
        String city,
        String country,
        String postalCode,
        String message
) {
    public Customer toCustomer() {
        Address address = new Address(street, number, city, postalCode, country);
        return new Customer(firstName, lastName, email, address);
    }
}