package kdg.programming6.keepdishesgoing.restaurant.adapter.out.dish;

import kdg.programming6.keepdishesgoing.common.vo.FoodTag;
import kdg.programming6.keepdishesgoing.restaurant.domain.*;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.DishId;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.RestaurantId;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class DishMapper {
    public DishJpaEntity toEntity(Dish dish) {
        DishJpaEntity entity = new DishJpaEntity();
        entity.setId(dish.getId() != null ? dish.getId().dishId() : UUID.randomUUID());
        entity.setRestaurantId(dish.getRestaurantId().restaurantId());
        entity.setName(dish.getName());
        entity.setDescription(dish.getDescription());
        entity.setType(dish.getType().name());
        entity.setPrice(BigDecimal.valueOf(dish.getPrice()));
        entity.setPictureUrl(dish.getPictureUrl());
        entity.setState(dish.getState().name());
        entity.setHasPendingChanges(dish.isHasPendingChanges());
        if (dish.getScheduledChange() != null) {
            entity.setScheduledAt(dish.getScheduledChange().scheduledAt());
        }
        if (dish.getTags() != null) {
            Set<FoodTagJpaEntity> tagEntities = dish.getTags().stream()
                    .map(tag -> {
                        FoodTagJpaEntity tagEntity = new FoodTagJpaEntity();
                        tagEntity.setId(UUID.randomUUID());
                        tagEntity.setName(tag.tag());
                        tagEntity.setDish(entity); // link back
                        return tagEntity;
                    })
                    .collect(Collectors.toSet());

            entity.getFoodTags().addAll(tagEntities);
        }
        return entity;
    }

    public Dish toDomain(DishJpaEntity entity) {
        Set<FoodTag> tags = entity.getFoodTags().stream()
                .map(tagEntity -> new FoodTag(tagEntity.getName()))
                .collect(Collectors.toSet());

        if (entity.getId() == null) {
            return Dish.create(
                    entity.getName(),
                    new RestaurantId(entity.getRestaurantId()),
                    DishType.valueOf(entity.getType()),
                    tags,
                    entity.getDescription(),
                    entity.getPrice().floatValue(),
                    entity.getPictureUrl(),
                    DishState.valueOf(entity.getState())
            );
        } else {
        return Dish.rehydrate(
                DishId.of(entity.getId()),
                entity.getName(),
                new RestaurantId(entity.getRestaurantId()),
                DishType.valueOf(entity.getType()),
                tags,
                entity.getDescription(),
                entity.getPrice().floatValue(),
                entity.getPictureUrl(),
                DishState.valueOf(entity.getState())
        );
        }
    }
}
