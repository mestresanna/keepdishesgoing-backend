package kdg.programming6.keepdishesgoing.order.port.in;

import kdg.programming6.keepdishesgoing.order.domain.vo.OpeningHoursProjection;

import java.util.UUID;

public record CreatedRestaurantProjectionCommand (
        UUID restaurantId,
        UUID ownerId,
        String name,
        String street,
        int number,
        String city,
        String country,
        String pictureUrls,
        OpeningHoursProjection openingHours,
        String typeOfCuisine,
        String contactEmail,
        int defaultPreparationTimeInMinutes,
        String priceRange
) {
}
