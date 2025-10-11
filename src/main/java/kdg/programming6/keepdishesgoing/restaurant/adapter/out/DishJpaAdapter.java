package kdg.programming6.keepdishesgoing.restaurant.adapter.out;

import kdg.programming6.keepdishesgoing.restaurant.domain.Dish;
import kdg.programming6.keepdishesgoing.restaurant.port.out.DishPort;
import org.springframework.stereotype.Repository;

@Repository
public class DishJpaAdapter implements DishPort {
    private final DishJpaRepository jpaRepo;
    private final DishMapper mapper;

    public DishJpaAdapter(DishJpaRepository jpaRepo, DishMapper mapper) {
        this.jpaRepo = jpaRepo;
        this.mapper = mapper;
    }

    @Override
    public void save(Dish dish) {
        DishJpaEntity entity = mapper.toEntity(dish);
        jpaRepo.save(entity);
    }
}
