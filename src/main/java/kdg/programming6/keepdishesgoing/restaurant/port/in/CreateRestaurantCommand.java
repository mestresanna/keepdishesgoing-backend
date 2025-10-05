package kdg.programming6.keepdishesgoing.restaurant.port.in;

import kdg.programming6.keepdishesgoing.restaurant.domain.*;

public record CreateRestaurantCommand (
        String name,
        OwnerId owner,
        Address address,
        String contactEmail,
        String pictureUrls,
        int defaultPreparationTimeInMinutes,
        TypeOfCuisine typeOfCuisine,
        OpeningHours openingHours
) {}
