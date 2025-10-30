package kdg.programming6.keepdishesgoing.order.adapter.in.dto;

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
