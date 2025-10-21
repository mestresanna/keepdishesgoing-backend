package kdg.programming6.keepdishesgoing.restaurant.adapter.in.response;

import java.util.Set;

public record DishDto(String dishId,
                      String name,
                      String description,
                      String dishType,
                      Float price,
                      String pictureUrl,
                      String type,
                      Set<String> tags,
                      String state) {
}
