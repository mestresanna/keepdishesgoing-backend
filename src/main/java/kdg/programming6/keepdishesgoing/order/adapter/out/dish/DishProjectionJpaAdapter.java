package kdg.programming6.keepdishesgoing.order.adapter.out.dish;

import kdg.programming6.keepdishesgoing.order.adapter.out.mapper.DishProjectionMapper;
import kdg.programming6.keepdishesgoing.order.adapter.out.menu.MenuProjectionJpaEntity;
import kdg.programming6.keepdishesgoing.order.adapter.out.menu.MenuProjectionJpaRepository;
import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjection;
import kdg.programming6.keepdishesgoing.order.port.out.dish.DeleteDishProjectionPort;
import kdg.programming6.keepdishesgoing.order.port.out.dish.LoadDishProjectionPort;
import kdg.programming6.keepdishesgoing.order.port.out.dish.SaveDishProjectionPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class DishProjectionJpaAdapter implements SaveDishProjectionPort, DeleteDishProjectionPort, LoadDishProjectionPort {
    private final DishProjectionJpaRepository  dishProjectionJpaRepository;
    private final MenuProjectionJpaRepository menuProjectionJpaRepository;
    private final DishProjectionMapper dishProjectionMapper;

    public DishProjectionJpaAdapter(DishProjectionJpaRepository dishProjectionJpaRepository, MenuProjectionJpaRepository menuProjectionJpaRepository, DishProjectionMapper dishProjectionMapper) {
        this.dishProjectionJpaRepository = dishProjectionJpaRepository;
        this.menuProjectionJpaRepository = menuProjectionJpaRepository;
        this.dishProjectionMapper = dishProjectionMapper;
    }


    @Override
    public void deleteDishProjection(DishProjection dishProjection, UUID menuProjection) {
        MenuProjectionJpaEntity menu = menuProjectionJpaRepository.findById(menuProjection)
                .orElseThrow(() -> new IllegalStateException("Menu not found for dish: " + dishProjection.getDishId()));
        DishProjectionId dishId = new DishProjectionId(menu.getMenuId(), dishProjection.getDishId());
        DishProjectionJpaEntity dish = dishProjectionJpaRepository.findById(dishId)
                .orElse(new DishProjectionJpaEntity());

        dishProjectionJpaRepository.delete(dish);
    }

    @Override
    public Optional<DishProjection> loadbyIdByMenu(UUID dishProjectionId, UUID menuProjection) {
        DishProjectionId dishId = new DishProjectionId(menuProjection, dishProjectionId);
        DishProjectionJpaEntity dish = dishProjectionJpaRepository.findById(dishId)
                .orElseThrow(() -> new IllegalStateException("Dish not found for dish: " + dishProjectionId));
        return dishProjectionJpaRepository.findById(dishId).map(DishProjectionMapper::toDomain);
    }

    @Override
    public List<DishProjection> loadAllWithDishId(UUID dishId) {
        return dishProjectionJpaRepository.findById_DishId(dishId).stream()
                .map(DishProjectionMapper::toDomain)
                .toList();
    }

    @Override
    public List<DishProjection> loadAllDishProjections(UUID menuId) {
        return dishProjectionJpaRepository.findById_MenuId(menuId).stream()
                .map(DishProjectionMapper::toDomain)
                .toList();
    }

    @Override
    public DishProjection saveDishProjection(DishProjection dishProjection, UUID menuProjection) {
        MenuProjectionJpaEntity menu = menuProjectionJpaRepository.findById(menuProjection)
                .orElseThrow(() -> new IllegalStateException("Menu not found for dish: " + dishProjection.getDishId()));

        DishProjectionId dishId = new DishProjectionId(menu.getMenuId(), dishProjection.getDishId());
        DishProjectionJpaEntity dish = dishProjectionJpaRepository.findById(dishId)
                .orElse(new DishProjectionJpaEntity());

        dish.setId(dishId);
        dish.setMenu(menu);
        dish.setRestaurantId(dishProjection.getRestaurantId().restaurantId());
        dish.setName(dishProjection.getName());
        dish.setType(dishProjection.getType());
        dish.setDescription(dishProjection.getDescription());
        dish.setPrice(dishProjection.getPrice());
        dish.setPictureUrl(dishProjection.getPictureUrl());
        dish.setStatus(dishProjection.getStatus().toString());

        // Clear old tags and add new
        dish.getFoodTags().clear();
        dishProjection.getTags().forEach(tag -> {
            FoodTagProjectionJpaEntity tagEntity = new FoodTagProjectionJpaEntity();
            tagEntity.setId(UUID.randomUUID());
            tagEntity.setName(tag.tag());
            tagEntity.setDish(dish);
            dish.getFoodTags().add(tagEntity);
        });

        dishProjectionJpaRepository.save(dish);
        return dishProjection;
    }

    @Override
    public List<DishProjection> saveDishProjections(List<DishProjection> dishProjections) {
        for (DishProjection dish : dishProjections) {
            List<DishProjectionJpaEntity> entities = dishProjectionJpaRepository.findById_DishId(dish.getDishId());

            for (DishProjectionJpaEntity entity : entities) {
                entity.setStatus(dish.getStatus().name());
            }
            dishProjectionJpaRepository.saveAll(entities);
        }
        return dishProjections;
    }
}
