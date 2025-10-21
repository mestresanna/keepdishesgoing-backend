package kdg.programming6.keepdishesgoing.restaurant.adapter.in.response;

import kdg.programming6.keepdishesgoing.restaurant.adapter.in.response.DayHoursDto;

import java.util.Map;

public record OpeningHourDto(Map<String, DayHoursDto> hoursPerDay) {

}
