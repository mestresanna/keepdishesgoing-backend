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
        UUID restaurantId = restaurantProjection.getRestaurantId().restaurantId();
        RestaurantProjectionEntity entity = restaurantProjectionJpaRepository
                .findByRestaurantId(restaurantId)
                .orElse(new RestaurantProjectionEntity());

        entity.setRestaurantId(restaurantId);
        entity.setOwnerId(restaurantProjection.getOwner());
        entity.setName(restaurantProjection.getName());
        entity.setStreet(restaurantProjection.getAddress().street());
        entity.setNumber(restaurantProjection.getAddress().number());
        entity.setCity(restaurantProjection.getAddress().city());
        entity.setPostalCode(restaurantProjection.getAddress().postalCode());
        entity.setCountry(restaurantProjection.getAddress().country());
        entity.setPictureUrls(restaurantProjection.getPictureUrls());
        entity.setOpeningHoursJson(openingHoursMapper.toJson(restaurantProjection.getOpeningHours()));
        entity.setTypeOfCuisine(restaurantProjection.getTypeOfCuisine());
        entity.setContactEmail(restaurantProjection.getContactEmail());
        entity.setDefaultPreparationTimeInMinutes(restaurantProjection.getDefaultPreparationTimeInMinutes());
        entity.setPriceRange(restaurantProjection.getPriceRange());

        RestaurantProjectionEntity savedEntity = restaurantProjectionJpaRepository.save(entity);

        return new RestaurantProjection(
                new RestaurantProjectionId(savedEntity.getRestaurantId()),
                savedEntity.getOwnerId(),
                savedEntity.getName(),
                new Address(savedEntity.getStreet(), savedEntity.getNumber(),
                        savedEntity.getCity(), savedEntity.getPostalCode(),
                        savedEntity.getCountry()),
                savedEntity.getPictureUrls(),
                openingHoursMapper.fromJson(savedEntity.getOpeningHoursJson()),
                savedEntity.getTypeOfCuisine(),
                savedEntity.getContactEmail(),
                savedEntity.getDefaultPreparationTimeInMinutes(),
                savedEntity.getPriceRange()
        );
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
