package kdg.programming6.keepdishesgoing.order.adapter.in.dto;

import java.util.List;

public record RestaurantDetailsDto(
        String restaurantId,
        String name,
        AddressDto address,
        String contactEmail,
        String pictureUrls,
        int defaultPreparationTimeInMinutes,
        String typeOfCuisine,
        OpeningHourDto openingHours,
        String priceRange,
        List<MenuProjectionDto> menus
) {}
