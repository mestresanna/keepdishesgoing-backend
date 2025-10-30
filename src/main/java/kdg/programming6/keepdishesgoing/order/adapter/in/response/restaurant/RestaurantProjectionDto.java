package kdg.programming6.keepdishesgoing.order.adapter.in.response.restaurant;

import kdg.programming6.keepdishesgoing.order.adapter.in.response.AddressProjectionDto;
import kdg.programming6.keepdishesgoing.order.adapter.in.response.openingHours.OpeningHourProjectionDto;

import java.util.UUID;

public record RestaurantProjectionDto(
        UUID restaurantId,
        String name,
        AddressProjectionDto address,
        String contactEmail,
        String pictureUrls,
        int defaultPreparationTimeInMinutes,
        String typeOfCuisine,
        OpeningHourProjectionDto openingHours,
        String priceRange,
        Boolean isOpen) {
}
