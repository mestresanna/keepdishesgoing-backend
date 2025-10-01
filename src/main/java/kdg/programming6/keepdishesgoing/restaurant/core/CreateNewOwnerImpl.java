package kdg.programming6.keepdishesgoing.restaurant.core;

import kdg.programming6.keepdishesgoing.restaurant.domain.Owner;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateOwnerCommand;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateOwnerUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.OwnerPort;
import org.springframework.stereotype.Service;

@Service
public class CreateNewOwnerImpl implements CreateOwnerUseCase {
    private final OwnerPort ownerPort;

    public CreateNewOwnerImpl(OwnerPort ownerPort) {
        this.ownerPort = ownerPort;
    }

    @Override
    public void handle(CreateOwnerCommand command) {
        Owner owner = Owner.create(
                command.name(),
                command.surname()
        );
        ownerPort.save(owner);
    }
}
