package kdg.programming6.keepdishesgoing.restaurant.domain.exceptions;

public class RestaurantOrderNotFoundException extends RuntimeException {
    public RestaurantOrderNotFoundException(String message) {
        super(message);
    }
}
