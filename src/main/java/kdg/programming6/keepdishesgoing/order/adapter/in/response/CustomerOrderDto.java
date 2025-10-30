package kdg.programming6.keepdishesgoing.order.adapter.in.response;

public record CustomerOrderDto(
        String firstName,
        String lastName,
        String email,
        String street,
        String number,
        String city,
        String postalCode,
        String country
) {
}
