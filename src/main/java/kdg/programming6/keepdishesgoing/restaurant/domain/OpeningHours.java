package kdg.programming6.keepdishesgoing.restaurant.domain;

import java.time.DayOfWeek;
import java.util.Map;

public record OpeningHours(Map<DayOfWeek, TimeRange> hoursPerDay) {
}

