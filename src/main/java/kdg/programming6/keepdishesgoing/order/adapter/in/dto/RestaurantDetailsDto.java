package kdg.programming6.keepdishesgoing.order.adapter.in.dto;

import java.util.List;
import java.util.UUID;

public record RestaurantDetailsDto(
        UUID restaurantId,
        String name,
        AddressProjectionDto address,
        String contactEmail,
        String pictureUrls,
        int defaultPreparationTimeInMinutes,
        String typeOfCuisine,
        OpeningHourProjectionDto openingHours,
        String priceRange,
        List<MenuProjectionDto> menus
) {}
