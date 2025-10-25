package kdg.programming6.keepdishesgoing.order.domain.exceptions;

public class BasketNotFoundException extends RuntimeException {
    public BasketNotFoundException(String message) {
        super(message);
    }
}
