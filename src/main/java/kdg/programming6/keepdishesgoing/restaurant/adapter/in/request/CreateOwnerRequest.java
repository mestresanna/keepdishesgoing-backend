package kdg.programming6.keepdishesgoing.restaurant.adapter.in.request;

import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateOwnerCommand;

public record CreateOwnerRequest (String name, String surname){
    public CreateOwnerCommand toCommand(){
        return new CreateOwnerCommand(name, surname);
    }
}
