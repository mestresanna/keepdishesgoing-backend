package kdg.programming6.keepdishesgoing.order.adapter.out.restaurant;

import kdg.programming6.keepdishesgoing.common.vo.Address;
import kdg.programming6.keepdishesgoing.order.adapter.out.mapper.OpeningHoursProjectionMapper;
import kdg.programming6.keepdishesgoing.order.domain.projections.RestaurantProjection;
import kdg.programming6.keepdishesgoing.order.domain.projections.RestaurantProjectionId;
import kdg.programming6.keepdishesgoing.order.port.out.restaurant.LoadRestaurantsProjectionsPort;
import kdg.programming6.keepdishesgoing.order.port.out.restaurant.SaveRestaurantProjectionPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RestaurantProjectionJpaAdapter implements SaveRestaurantProjectionPort, LoadRestaurantsProjectionsPort {
    private final RestaurantProjectionJpaRepository restaurantProjectionJpaRepository;
    private final OpeningHoursProjectionMapper openingHoursMapper;

    public RestaurantProjectionJpaAdapter(RestaurantProjectionJpaRepository restaurantProjectionJpaRepository, OpeningHoursProjectionMapper openingHoursMapper) {
        this.restaurantProjectionJpaRepository = restaurantProjectionJpaRepository;
        this.openingHoursMapper = openingHoursMapper;
    }

    @Override
    public RestaurantProjection save(RestaurantProjection restaurantProjection) {

        RestaurantProjectionEntity restaurantProjectionEntity = new RestaurantProjectionEntity(
                restaurantProjection.getRestaurantId().restaurantId(),
                restaurantProjection.getOwner(),
                restaurantProjection.getName(),
                restaurantProjection.getAddress().street(),
                restaurantProjection.getAddress().number(),
                restaurantProjection.getAddress().city(),
                restaurantProjection.getAddress().postalCode(),
                restaurantProjection.getAddress().country(),
                restaurantProjection.getPictureUrls(),
                openingHoursMapper.toJson(restaurantProjection.getOpeningHours()),
                restaurantProjection.getTypeOfCuisine(),
                restaurantProjection.getContactEmail(),
                restaurantProjection.getDefaultPreparationTimeInMinutes(),
                restaurantProjection.getPriceRange());

        this.restaurantProjectionJpaRepository.save(restaurantProjectionEntity);

        return new RestaurantProjection(
                restaurantProjection.getRestaurantId(),
                restaurantProjection.getOwner(),
                restaurantProjection.getName(),
                restaurantProjection.getAddress(),
                restaurantProjection.getPictureUrls(),
                restaurantProjection.getOpeningHours(),
                restaurantProjection.getTypeOfCuisine(),
                restaurantProjection.getContactEmail(),
                restaurantProjection.getDefaultPreparationTimeInMinutes(),
                restaurantProjection.getPriceRange());
    }

    @Override
    public List<RestaurantProjection> loadAllRestaurants() {
        return restaurantProjectionJpaRepository.findAll().stream().map(entity -> new RestaurantProjection(
                new RestaurantProjectionId(entity.getRestaurantId()),
                entity.getOwnerId(),
                entity.getName(),
                new Address(entity.getStreet(), entity.getNumber(),entity.getCity(), entity.getPostalCode(), entity.getCountry() ),
                entity.getPictureUrls(),
                openingHoursMapper.fromJson(entity.getOpeningHoursJson()),
                entity.getTypeOfCuisine(),
                entity.getContactEmail(),
                entity.getDefaultPreparationTimeInMinutes(),
                entity.getPriceRange())).toList();
    }

    @Override
    public Optional<RestaurantProjection> loadbyId(UUID restaurantId) {
        return restaurantProjectionJpaRepository.findByRestaurantId(restaurantId).map(entity -> new RestaurantProjection(
                new RestaurantProjectionId(entity.getRestaurantId()),
                entity.getOwnerId(),
                entity.getName(),
                new Address(entity.getStreet(), entity.getNumber(),entity.getCity(), entity.getPostalCode(), entity.getCountry() ),
                entity.getPictureUrls(),
                openingHoursMapper.fromJson(entity.getOpeningHoursJson()),
                entity.getTypeOfCuisine(),
                entity.getContactEmail(),
                entity.getDefaultPreparationTimeInMinutes(),
                entity.getPriceRange()));
    }
}
