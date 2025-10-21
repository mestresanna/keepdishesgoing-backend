package kdg.programming6.keepdishesgoing.restaurant.adapter.out.restaurant;

import com.fasterxml.jackson.core.type.TypeReference;
import kdg.programming6.keepdishesgoing.common.vo.TimeRange;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kdg.programming6.keepdishesgoing.restaurant.domain.*;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RestaurantMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule()); // support LocalTime
    }

    public static RestaurantJpaEntity toEntity(Restaurant restaurant) {
        RestaurantJpaEntity entity = new RestaurantJpaEntity();
        entity.setRestaurantId(restaurant.getRestaurantId());
        entity.setOwner(restaurant.getOwner().ownerId());

        entity.setName(restaurant.getName());
        entity.setContactEmail(restaurant.getContactEmail());
        entity.setTypeOfCuisine(restaurant.getTypeOfCuisine().name());
        entity.setStreet(restaurant.getAddress().street());
        entity.setNumber(restaurant.getAddress().number());
        entity.setCity(restaurant.getAddress().city());
        entity.setCountry(restaurant.getAddress().country());
        entity.setPictureUrls(restaurant.getPictureUrls());
        entity.setDefaultPreparationTimeInMinutes(restaurant.getDefaultPreparationTimeInMinutes());
        entity.setPriceRange(restaurant.getPriceRange().name());
        try {
            if (restaurant.getOpeningHours() != null) {
                String json = objectMapper.writeValueAsString(
                        restaurant.getOpeningHours().hoursPerDay()
                );
                entity.setOpeningHoursJson(json);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error serializing openingHours", e);
        }
        return entity;
    }

    public Restaurant toDomain(RestaurantJpaEntity entity) {
        OwnerId orderId = OwnerId.of(entity.getOwner());
        Address address = new Address(
                entity.getStreet(), entity.getNumber(), entity.getCity(), entity.getCountry()
        );

        OpeningHours openingHours = null;
        String json = entity.getOpeningHoursJson();
        if (json != null && !json.isEmpty()) {
            try {
                Map<String, TimeRange> stringMap = objectMapper.readValue(
                        json,
                        new TypeReference<Map<String, TimeRange>>() {}
                );

                Map<DayOfWeek, TimeRange> hoursPerDay = stringMap.entrySet().stream()
                        .collect(Collectors.toMap(
                                e -> DayOfWeek.valueOf(e.getKey()),
                                Map.Entry::getValue
                        ));

                openingHours = new OpeningHours(hoursPerDay);
            } catch (Exception e) {
                throw new RuntimeException("Error deserializing openingHours", e);
            }
        }

        if (entity.getRestaurantId() == null) {
            if (entity.getPriceRange() == null || entity.getPriceRange().isEmpty()) {
                return Restaurant.create(
                        entity.getName(),
                        address,
                        entity.getContactEmail(),
                        entity.getPictureUrls(),
                        entity.getDefaultPreparationTimeInMinutes(),
                        TypeOfCuisine.valueOf(entity.getTypeOfCuisine()),
                        openingHours,
                        orderId);
            } else {
                PriceRange priceRange = PriceRange.valueOf(entity.getPriceRange());
                return Restaurant.create(
                        entity.getName(),
                        address,
                        entity.getContactEmail(),
                        entity.getPictureUrls(),
                        entity.getDefaultPreparationTimeInMinutes(),
                        TypeOfCuisine.valueOf(entity.getTypeOfCuisine()),
                        openingHours,
                        orderId,
                        priceRange);
            }
        } else {
            return Restaurant.update(
                    RestaurantId.of(entity.getRestaurantId()),
                    entity.getName(),
                    address,
                    entity.getContactEmail(),
                    entity.getPictureUrls(),
                    entity.getDefaultPreparationTimeInMinutes(),
                    TypeOfCuisine.valueOf(entity.getTypeOfCuisine()),
                    openingHours,
                    orderId,
                    PriceRange.valueOf(entity.getPriceRange()));
        }

    }
}
