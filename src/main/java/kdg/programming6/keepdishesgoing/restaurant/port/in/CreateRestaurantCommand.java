package kdg.programming6.keepdishesgoing.restaurant.port.in;

import kdg.programming6.keepdishesgoing.restaurant.domain.Address;
import kdg.programming6.keepdishesgoing.restaurant.domain.OpeningHours;
import kdg.programming6.keepdishesgoing.restaurant.domain.Owner;
import kdg.programming6.keepdishesgoing.restaurant.domain.TypeOfCuisine;

import java.util.List;

public record CreateRestaurantCommand (
        String name,
        Owner owner,
        Address address,
        String contactEmail,
        String pictureUrls,
        int defaultPreparationTimeInMinutes,
        TypeOfCuisine typeOfCuisine,
        OpeningHours openingHours
) {}
