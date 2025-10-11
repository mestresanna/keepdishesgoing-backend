package kdg.programming6.keepdishesgoing.restaurant.domain.ov;

import java.time.DayOfWeek;
import java.util.Map;

public record OpeningHours(Map<DayOfWeek, TimeRange> hoursPerDay) {
}

