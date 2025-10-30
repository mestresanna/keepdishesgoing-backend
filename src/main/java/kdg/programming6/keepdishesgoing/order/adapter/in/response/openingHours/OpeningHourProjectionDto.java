package kdg.programming6.keepdishesgoing.order.adapter.in.response.openingHours;

import java.util.Map;

public record OpeningHourProjectionDto(Map<String, DayHoursProjectionDto> hoursPerDay) {

}
