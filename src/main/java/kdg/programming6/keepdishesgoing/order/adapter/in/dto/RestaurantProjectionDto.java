package kdg.programming6.keepdishesgoing.order.adapter.in.dto;

public record RestaurantProjectionDto(
        String restaurantId,
        String name,
        AddressProjectionDto address,
        String contactEmail,
        String pictureUrls,
        int defaultPreparationTimeInMinutes,
        String typeOfCuisine,
        OpeningHourProjectionDto openingHours,
        String priceRange) {
}
