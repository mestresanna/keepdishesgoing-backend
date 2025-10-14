package kdg.programming6.keepdishesgoing.restaurant.domain.exceptions;

public class MenuNotFoundException extends RuntimeException {
    public MenuNotFoundException(String message) {
        super(message);
    }
}
