package kdg.programming6.keepdishesgoing.restaurant.adapter.in.response.restaurant;

import kdg.programming6.keepdishesgoing.restaurant.adapter.in.response.AddressDto;
import kdg.programming6.keepdishesgoing.restaurant.adapter.in.response.openingHours.DayHoursDto;
import kdg.programming6.keepdishesgoing.restaurant.adapter.in.response.openingHours.OpeningHourDto;
import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapperDto {
    public RestaurantDto toDto(Restaurant restaurant) {
        AddressDto address = new AddressDto(
                restaurant.getAddress().street(),
                restaurant.getAddress().number(),
                restaurant.getAddress().city(),
                restaurant.getAddress().country()
        );


        OpeningHourDto openingHours = new OpeningHourDto(
                restaurant.getOpeningHours().hoursPerDay().entrySet().stream()
                        .collect(java.util.stream.Collectors.toMap(
                                e -> e.getKey().name(),
                                e -> new DayHoursDto(e.getValue().open(), e.getValue().close())
                        ))
        );

        return new RestaurantDto(
                restaurant.getRestaurantId().toString(),
                restaurant.getName(),
                address,
                restaurant.getContactEmail(),
                restaurant.getPictureUrls(),
                restaurant.getDefaultPreparationTimeInMinutes(),
                restaurant.getTypeOfCuisine().toString(),
                openingHours,
                (restaurant.getPriceRange()).toString());
    }
}
