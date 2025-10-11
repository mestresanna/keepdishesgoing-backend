package kdg.programming6.keepdishesgoing.restaurant.adapter.out.dish;

import kdg.programming6.keepdishesgoing.restaurant.domain.Dish;
import kdg.programming6.keepdishesgoing.restaurant.port.out.LoadDishPort;
import kdg.programming6.keepdishesgoing.restaurant.port.out.SaveDishPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class DishJpaAdapter implements SaveDishPort, LoadDishPort {
    private final DishJpaRepository jpaRepo;
    private final DishMapper mapper;

    public DishJpaAdapter(DishJpaRepository jpaRepo, DishMapper mapper) {
        this.jpaRepo = jpaRepo;
        this.mapper = mapper;
    }
    @Override
    public Optional<Dish> loadById(UUID dishId) {
        return jpaRepo.findById(dishId)
                .map(mapper::toDomain);
    }
    @Override
    public void save(Dish dish) {
        DishJpaEntity entity = mapper.toEntity(dish);
        jpaRepo.save(entity);
    }
}
