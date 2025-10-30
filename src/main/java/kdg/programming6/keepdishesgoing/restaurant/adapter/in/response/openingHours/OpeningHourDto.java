package kdg.programming6.keepdishesgoing.restaurant.adapter.in.response.openingHours;

import java.util.Map;

public record OpeningHourDto(Map<String, DayHoursDto> hoursPerDay) {

}
