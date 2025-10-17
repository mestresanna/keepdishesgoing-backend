package kdg.programming6.keepdishesgoing.order.adapter.out;

import kdg.programming6.keepdishesgoing.order.adapter.out.mapper.OpeningHoursProjectionMapper;
import kdg.programming6.keepdishesgoing.order.domain.projections.RestaurantProjection;
import kdg.programming6.keepdishesgoing.order.port.out.SaveRestaurantProjectionPort;
import kdg.programming6.keepdishesgoing.restaurant.adapter.out.restaurant.OpeningHoursMapper;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class RestaurantProjectionJpaAdapter implements SaveRestaurantProjectionPort {
    private final RestaurantProjectionJpaRepository restaurantProjectionJpaRepository;
    private final OpeningHoursProjectionMapper openingHoursMapper;

    public RestaurantProjectionJpaAdapter(RestaurantProjectionJpaRepository restaurantProjectionJpaRepository, OpeningHoursProjectionMapper openingHoursMapper) {
        this.restaurantProjectionJpaRepository = restaurantProjectionJpaRepository;
        this.openingHoursMapper = openingHoursMapper;
    }

    @Override
    public RestaurantProjection save(RestaurantProjection restaurantProjection) {

        RestaurantProjectionEntity restaurantProjectionEntity = new RestaurantProjectionEntity(
                restaurantProjection.getRestaurantId(),
                restaurantProjection.getOwner(),
                restaurantProjection.getName(),
                restaurantProjection.getStreet(),
                restaurantProjection.getNumber(),
                restaurantProjection.getCity(),
                restaurantProjection.getCountry(),
                restaurantProjection.getPictureUrls(),
                openingHoursMapper.toJson(restaurantProjection.getOpeningHours()),
                restaurantProjection.getTypeOfCuisine(),
                restaurantProjection.getContactEmail(),
                restaurantProjection.getDefaultPreparationTimeInMinutes(),
                restaurantProjection.getPriceRange());

        this.restaurantProjectionJpaRepository.save(restaurantProjectionEntity);

        return new RestaurantProjection( restaurantProjection.getRestaurantId(),
                restaurantProjection.getOwner(),
                restaurantProjection.getName(),
                restaurantProjection.getStreet(),
                restaurantProjection.getNumber(),
                restaurantProjection.getCity(),
                restaurantProjection.getCountry(),
                restaurantProjection.getPictureUrls(),
                restaurantProjection.getOpeningHours(),
                restaurantProjection.getTypeOfCuisine(),
                restaurantProjection.getContactEmail(),
                restaurantProjection.getDefaultPreparationTimeInMinutes(),
                restaurantProjection.getPriceRange());
    }
}
