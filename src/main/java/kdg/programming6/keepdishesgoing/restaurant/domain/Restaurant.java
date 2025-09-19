package kdg.programming6.keepdishesgoing.restaurant.domain;

import java.util.List;

public class Restaurant {
    private String name;
    private String street;
    private String city;
    private String country;
    private int number;
    private TypeOfCuisine typeOfCuisine;
    private String contactEmail;
    private List<Dish> menu;
    private List<Order> orders;

    public Restaurant(String name, String street, String city, String country, int number, TypeOfCuisine typeOfCuisine, String contactEmail) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.country = country;
        this.number = number;
        this.typeOfCuisine = typeOfCuisine;
        this.contactEmail = contactEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public TypeOfCuisine getTypeOfCuisine() {
        return typeOfCuisine;
    }

    public void setTypeOfCuisine(TypeOfCuisine typeOfCuisine) {
        this.typeOfCuisine = typeOfCuisine;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}


