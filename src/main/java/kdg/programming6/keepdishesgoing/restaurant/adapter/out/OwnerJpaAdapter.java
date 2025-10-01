package kdg.programming6.keepdishesgoing.restaurant.adapter.out;

import kdg.programming6.keepdishesgoing.restaurant.domain.Owner;
import kdg.programming6.keepdishesgoing.restaurant.port.out.OwnerPort;
import org.springframework.stereotype.Repository;

@Repository
public class OwnerJpaAdapter implements OwnerPort {
    private final OwnerJpaRepository ownerJpaRepository;

    public OwnerJpaAdapter(OwnerJpaRepository ownerJpaRepository) {
        this.ownerJpaRepository = ownerJpaRepository;
    }

    @Override
    public void save(Owner owner) {
        OwnerJpaEntity entity = mapToEntity(owner);
        ownerJpaRepository.save(entity);
    }

    private OwnerJpaEntity mapToEntity(Owner owner) {
        OwnerJpaEntity entity = new OwnerJpaEntity();
        entity.setOwnerId(owner.getOwnerId());
        entity.setName(owner.getName());
        entity.setSurname(owner.getSurname());
        return entity;
    }
}
