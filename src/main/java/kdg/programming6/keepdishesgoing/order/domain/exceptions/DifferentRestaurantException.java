package kdg.programming6.keepdishesgoing.order.domain.exceptions;

public class DifferentRestaurantException extends RuntimeException {
    public DifferentRestaurantException() {
        super("Cannot add a dish from a different restaurant to the same basket.");
    }
}
