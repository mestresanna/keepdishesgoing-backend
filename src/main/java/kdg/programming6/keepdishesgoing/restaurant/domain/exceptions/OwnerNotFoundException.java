package kdg.programming6.keepdishesgoing.restaurant.domain.exceptions;

public class OwnerNotFoundException extends RuntimeException {
    public OwnerNotFoundException(String message) {
        super(message);
    }
}
