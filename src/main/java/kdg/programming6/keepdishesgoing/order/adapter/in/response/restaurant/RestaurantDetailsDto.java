package kdg.programming6.keepdishesgoing.order.adapter.in.response.restaurant;

import kdg.programming6.keepdishesgoing.order.adapter.in.response.AddressProjectionDto;
import kdg.programming6.keepdishesgoing.order.adapter.in.response.menu.MenuProjectionDto;
import kdg.programming6.keepdishesgoing.order.adapter.in.response.openingHours.OpeningHourProjectionDto;

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
        Boolean isOpen,
        List<MenuProjectionDto> menus
) {}
