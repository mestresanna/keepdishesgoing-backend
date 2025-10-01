package kdg.programming6.keepdishesgoing.restaurant.adapter.out;

import kdg.programming6.keepdishesgoing.restaurant.domain.*;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {

    public static RestaurantJpaEntity toEntity(Restaurant restaurant) {
        RestaurantJpaEntity entity = new RestaurantJpaEntity();
        entity.setRestaurantId(restaurant.getRestaurantId());

        OwnerJpaEntity ownerEntity = new OwnerJpaEntity();
        ownerEntity.setOwnerId(restaurant.getOwner().getOwnerId());
        ownerEntity.setName(restaurant.getOwner().getName());
        ownerEntity.setSurname(restaurant.getOwner().getSurname());
        entity.setOwner(ownerEntity);

        entity.setName(restaurant.getName());
        entity.setContactEmail(restaurant.getContactEmail());
        entity.setTypeOfCuisine(restaurant.getTypeOfCuisine().name());
        entity.setStreet(restaurant.getAddress().street());
        entity.setNumber(restaurant.getAddress().number());
        entity.setCity(restaurant.getAddress().city());
        entity.setCountry(restaurant.getAddress().country());
        entity.setPictureUrls(restaurant.getPictureUrls());
        entity.setDefaultPreparationTimeInMinutes(restaurant.getDefaultPreparationTimeInMinutes());

        return entity;
    }

    public static Restaurant toDomain(RestaurantJpaEntity entity) {
        Owner owner = new Owner(
                entity.getOwner().getOwnerId(),
                entity.getOwner().getName(),
                entity.getOwner().getSurname()
        );

        Address address = new Address(
                entity.getStreet(), entity.getNumber(), entity.getCity(), entity.getCountry()
        );

        return Restaurant.create(
                entity.getName(),
                address,
                entity.getContactEmail(),
                entity.getPictureUrls(),
                entity.getDefaultPreparationTimeInMinutes(),
                TypeOfCuisine.valueOf(entity.getTypeOfCuisine()),
                null, // OpeningHours needs its own mapping
                owner
        );
    }
}
