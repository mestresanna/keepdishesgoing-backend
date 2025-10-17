package kdg.programming6.keepdishesgoing.order.domain.vo;

import java.time.DayOfWeek;
import java.util.Map;

/**
 * Read-only projection of OpeningHours, kept as a simple map for querying.
 */
public record OpeningHoursProjection(Map<DayOfWeek, TimeRangeProjection> hoursPerDay) {
    public TimeRangeProjection getForDay(DayOfWeek day) {
        return hoursPerDay.get(day);
    }
}
