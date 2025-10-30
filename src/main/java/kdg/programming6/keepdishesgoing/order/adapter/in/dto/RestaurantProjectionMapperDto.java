package kdg.programming6.keepdishesgoing.order.adapter.in.dto;

import kdg.programming6.keepdishesgoing.order.domain.projections.RestaurantProjection;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantProjectionMapperDto {
    public RestaurantProjectionDto toDto(RestaurantProjection restaurant) {
        AddressProjectionDto address = new AddressProjectionDto(
                restaurant.getAddress().street(),
                restaurant.getAddress().number(),
                restaurant.getAddress().city(),
                restaurant.getAddress().postalCode(),
                restaurant.getAddress().country()
        );


        OpeningHourProjectionDto openingHours = new OpeningHourProjectionDto(
                restaurant.getOpeningHours().hoursPerDay().entrySet().stream()
                        .collect(java.util.stream.Collectors.toMap(
                                e -> e.getKey().name(),
                                e -> new DayHoursProjectionDto(e.getValue().open(), e.getValue().close())
                        ))
        );

        return new RestaurantProjectionDto(
                restaurant.getRestaurantId().restaurantId(),
                restaurant.getName(),
                address,
                restaurant.getContactEmail(),
                restaurant.getPictureUrls(),
                restaurant.getDefaultPreparationTimeInMinutes(),
                restaurant.getTypeOfCuisine(),
                openingHours,
                restaurant.getPriceRange(),
                restaurant.isOpenNow()
        );
    }

    public RestaurantDetailsDto toDetailsDto(RestaurantProjection restaurant, List<MenuProjectionDto> menuDtos) {

        AddressProjectionDto address = new AddressProjectionDto(
                restaurant.getAddress().street(),
                restaurant.getAddress().number(),
                restaurant.getAddress().city(),
                restaurant.getAddress().postalCode(),
                restaurant.getAddress().country()
        );

        OpeningHourProjectionDto openingHours = new OpeningHourProjectionDto(
                restaurant.getOpeningHours().hoursPerDay().entrySet().stream()
                        .collect(java.util.stream.Collectors.toMap(
                                e -> e.getKey().name(),
                                e -> new DayHoursProjectionDto(e.getValue().open(), e.getValue().close())
                        ))
        );

        return new RestaurantDetailsDto(
                restaurant.getRestaurantId().restaurantId(),
                restaurant.getName(),
                address,
                restaurant.getContactEmail(),
                restaurant.getPictureUrls(),
                restaurant.getDefaultPreparationTimeInMinutes(),
                restaurant.getTypeOfCuisine(),
                openingHours,
                restaurant.getPriceRange(),
                menuDtos
        );
    }
}
