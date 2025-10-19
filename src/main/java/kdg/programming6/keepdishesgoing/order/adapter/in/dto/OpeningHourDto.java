package kdg.programming6.keepdishesgoing.order.adapter.in.dto;

import java.util.Map;

public record OpeningHourDto(Map<String, DayHoursDto> hoursPerDay) {

}
