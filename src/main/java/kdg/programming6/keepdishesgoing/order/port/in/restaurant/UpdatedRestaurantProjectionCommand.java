package kdg.programming6.keepdishesgoing.order.port.in.restaurant;

import kdg.programming6.keepdishesgoing.order.domain.vo.OpeningHoursProjection;

import java.util.UUID;

public record UpdatedRestaurantProjectionCommand(UUID restaurantId,
                                                 UUID ownerId,
                                                 String name,
                                                 String street,
                                                 int number,
                                                 String city,
                                                 String postalCode,
                                                 String country,
                                                 String pictureUrls,
                                                 OpeningHoursProjection openingHours,
                                                 String typeOfCuisine,
                                                 String contactEmail,
                                                 int defaultPreparationTimeInMinutes,
                                                 String priceRange) {
}
