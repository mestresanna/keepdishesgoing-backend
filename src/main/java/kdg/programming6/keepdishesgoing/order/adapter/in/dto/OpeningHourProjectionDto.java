package kdg.programming6.keepdishesgoing.order.adapter.in.dto;

import java.util.Map;

public record OpeningHourProjectionDto(Map<String, DayHoursProjectionDto> hoursPerDay) {

}
