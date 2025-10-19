package kdg.programming6.keepdishesgoing.order.adapter.in.dto;

import java.util.UUID;

public record RestaurantProjectionDto(
        String restaurantId,
        String name,
        AddressDto address,
        String contactEmail,
        String pictureUrls,
        int defaultPreparationTimeInMinutes,
        String typeOfCuisine,
        OpeningHourDto openingHours,
        String priceRange) {
}
