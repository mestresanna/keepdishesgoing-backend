package kdg.programming6.keepdishesgoing.restaurant.adapter.in.request;

import kdg.programming6.keepdishesgoing.common.vo.TimeRange;
import kdg.programming6.keepdishesgoing.restaurant.domain.*;
import kdg.programming6.keepdishesgoing.common.Address;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.OpeningHours;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.OwnerId;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateRestaurantCommand;

import java.time.DayOfWeek;
import java.util.Map;
import java.util.UUID;

public record CreateRestaurantRequest(String name,
                                      String street,
                                      int number,
                                      String city,
                                      String country,
                                      String contactEmail,
                                      String pictureUrls,
                                      int defaultPreparationTimeInMinutes,
                                      TypeOfCuisine typeOfCuisine,
                                      Map<DayOfWeek, TimeRange> openingHours,
                                      OwnerId owner) {

    public CreateRestaurantCommand toCommand(UUID ownerId) {
        return new CreateRestaurantCommand(
                name,
                new OwnerId(ownerId),
                new Address(street, number, city, country),
                contactEmail,
                pictureUrls,
                defaultPreparationTimeInMinutes,
                typeOfCuisine,
                new OpeningHours(openingHours)
        );
    }
}