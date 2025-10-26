package kdg.programming6.keepdishesgoing.order.port.in.basket;

import kdg.programming6.keepdishesgoing.common.Address;
import kdg.programming6.keepdishesgoing.order.domain.Customer;

import java.util.UUID;

public class CheckoutBasketCommand {
    private final UUID basketId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Address deliveryAddress;
    private final String message;

    public Customer toCustomer() {
        return new Customer(firstName, lastName, email, deliveryAddress);
    }

    // getters, constructor...
}
