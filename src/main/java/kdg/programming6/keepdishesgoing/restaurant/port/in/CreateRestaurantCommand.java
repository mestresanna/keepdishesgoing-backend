package kdg.programming6.keepdishesgoing.restaurant.port.in;

import kdg.programming6.keepdishesgoing.restaurant.domain.*;
import kdg.programming6.keepdishesgoing.common.Address;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.OpeningHours;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.OwnerId;

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
