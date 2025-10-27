package kdg.programming6.keepdishesgoing.common.vo;

public record AddressDto(String street, String number, String postalCode, String city) {
    public static AddressDto from(Address address) {
        return new AddressDto(
                address.street(),
                String.valueOf(address.number()),
                address.postalCode(),
                address.city()
        );
    }
}