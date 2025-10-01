package kdg.programming6.keepdishesgoing.restaurant.port.in;

public record CreateOwnerCommand (
        String name,
        String surname
){
}
