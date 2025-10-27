package kdg.programming6.keepdishesgoing.common.vo;

public record Address(String street, int number, String city, String postalCode, String country) {
    public static Address of(String street, int number, String city, String postalCode, String country) {
        return new Address(street, number, city, postalCode, country);
    }
}
