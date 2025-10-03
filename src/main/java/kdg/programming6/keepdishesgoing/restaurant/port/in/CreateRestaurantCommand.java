package kdg.programming6.keepdishesgoing.restaurant.port.in;

import kdg.programming6.keepdishesgoing.restaurant.domain.*;

import java.util.List;

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
