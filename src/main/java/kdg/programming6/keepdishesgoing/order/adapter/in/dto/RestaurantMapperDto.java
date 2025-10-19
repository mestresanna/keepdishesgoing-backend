package kdg.programming6.keepdishesgoing.order.adapter.in.dto;

import kdg.programming6.keepdishesgoing.order.domain.projections.RestaurantProjection;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantMapperDto {
    public RestaurantProjectionDto toDto(RestaurantProjection restaurant) {
        AddressDto address = new AddressDto(
                restaurant.getStreet(),
                restaurant.getNumber(),
                restaurant.getCity(),
                restaurant.getCountry()
        );


        OpeningHourDto openingHours = new OpeningHourDto(
                restaurant.getOpeningHours().hoursPerDay().entrySet().stream()
                        .collect(java.util.stream.Collectors.toMap(
                                e -> e.getKey().name(),
                                e -> new DayHoursDto(e.getValue().open(), e.getValue().close())
                        ))
        );

        return new RestaurantProjectionDto(
                restaurant.getRestaurantId().toString(),
                restaurant.getName(),
                address,
                restaurant.getContactEmail(),
                restaurant.getPictureUrls(),
                restaurant.getDefaultPreparationTimeInMinutes(),
                restaurant.getTypeOfCuisine(),
                openingHours,
                restaurant.getPriceRange()
        );
    }

    public RestaurantDetailsDto toDetailsDto(RestaurantProjection restaurant, List<MenuProjectionDto> menuDtos) {

        AddressDto address = new AddressDto(
                restaurant.getStreet(),
                restaurant.getNumber(),
                restaurant.getCity(),
                restaurant.getCountry()
        );

        OpeningHourDto openingHours = new OpeningHourDto(
                restaurant.getOpeningHours().hoursPerDay().entrySet().stream()
                        .collect(java.util.stream.Collectors.toMap(
                                e -> e.getKey().name(),
                                e -> new DayHoursDto(e.getValue().open(), e.getValue().close())
                        ))
        );

        return new RestaurantDetailsDto(
                restaurant.getRestaurantId().toString(),
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
