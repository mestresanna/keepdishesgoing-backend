package kdg.programming6.keepdishesgoing.restaurant.domain.ov;

import kdg.programming6.keepdishesgoing.common.vo.TimeRange;

import java.time.DayOfWeek;
import java.util.Map;

public record OpeningHours(Map<DayOfWeek, TimeRange> hoursPerDay) {
}

