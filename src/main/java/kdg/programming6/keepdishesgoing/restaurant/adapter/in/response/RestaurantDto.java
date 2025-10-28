package kdg.programming6.keepdishesgoing.restaurant.adapter.in.response;

public record RestaurantDto(String restaurantId,
                            String name,
                            AddressDto address,
                            String contactEmail,
                            String pictureUrls,
                            int defaultPreparationTimeInMinutes,
                            String typeOfCuisine,
                            OpeningHourDto openingHours,
                            String priceRange) {
}

