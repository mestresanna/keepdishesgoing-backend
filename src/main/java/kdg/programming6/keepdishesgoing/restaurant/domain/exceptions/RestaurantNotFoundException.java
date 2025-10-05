package kdg.programming6.keepdishesgoing.restaurant.domain.exceptions;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(String message) {
        super(message);
    }
}
