package kdg.programming6.keepdishesgoing.restaurant.domain.exceptions;

public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException(String message) {
        super(message);
    }
}
