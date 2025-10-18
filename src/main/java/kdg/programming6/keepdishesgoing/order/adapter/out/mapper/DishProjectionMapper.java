package kdg.programming6.keepdishesgoing.order.adapter.out.mapper;

import kdg.programming6.keepdishesgoing.common.vo.FoodTag;
import kdg.programming6.keepdishesgoing.order.adapter.out.dish.DishProjectionId;
import kdg.programming6.keepdishesgoing.order.adapter.out.dish.DishProjectionJpaEntity;
import kdg.programming6.keepdishesgoing.order.adapter.out.dish.FoodTagProjectionJpaEntity;
import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjection;
import kdg.programming6.keepdishesgoing.order.domain.projections.StatusDish;
import kdg.programming6.keepdishesgoing.order.adapter.out.menu.MenuProjectionJpaEntity;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class DishProjectionMapper {

    public static DishProjection toDomain(DishProjectionJpaEntity entity) {
        if (entity == null) return null;

        Set<FoodTag> tags = entity.getFoodTags().stream()
                .map(tag -> new FoodTag(tag.getName()))
                .collect(Collectors.toSet());

        return new DishProjection(
                entity.getId().getDishId(),
                entity.getName(),
                entity.getRestaurantId(),
                entity.getType(),
                tags,
                entity.getDescription(),
                entity.getPrice(),
                entity.getPictureUrl(),
                StatusDish.valueOf(entity.getStatus().toUpperCase())
        );
    }

    public static DishProjectionJpaEntity toJpa(DishProjection domain, UUID menuId, MenuProjectionJpaEntity menu) {
        if (domain == null) return null;

        DishProjectionJpaEntity entity = new DishProjectionJpaEntity();

        entity.setId(new DishProjectionId(menuId, domain.getDishId()));
        entity.setMenu(menu);
        entity.setRestaurantId(domain.getRestaurantId());
        entity.setName(domain.getName());
        entity.setType(domain.getType());
        entity.setDescription(domain.getDescription());
        entity.setPrice(domain.getPrice());
        entity.setPictureUrl(domain.getPictureUrl());
        entity.setStatus(domain.getStatus().name());

        entity.setFoodTags(domain.getTags().stream()
                .map(tag -> {
                    FoodTagProjectionJpaEntity tagEntity = new FoodTagProjectionJpaEntity();
                    tagEntity.setName(tag.tag());
                    tagEntity.setDish(entity);
                    return tagEntity;
                })
                .collect(Collectors.toList()));

        return entity;
    }
}
