package kdg.programming6.keepdishesgoing.order.adapter.out.order;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CustomerEmbeddable {
    @Column(name = "customer_first_name")
    private String firstName;

    @Column(name = "customer_last_name")
    private String lastName;

    @Column(name = "customer_email")
    private String email;

    // delivery address fields (flattened)
    @Column(name = "delivery_street")
    private String street;

    @Column(name = "delivery_number")
    private Integer number;

    @Column(name = "delivery_postal_code")
    private String postalCode;

    @Column(name = "delivery_city")
    private String city;

    @Column(name = "delivery_country")
    private String country;

    public CustomerEmbeddable() {}

    // getters & setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public Integer getNumber() { return number; }
    public void setNumber(Integer number) { this.number = number; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}