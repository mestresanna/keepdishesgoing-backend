package kdg.programming6.keepdishesgoing.order.domain.exceptions;

public class DishProjectionNotFoundException extends RuntimeException {
    public DishProjectionNotFoundException(String message) {
        super(message);
    }
}
