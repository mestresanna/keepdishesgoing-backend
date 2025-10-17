package kdg.programming6.keepdishesgoing.order.domain.vo;

import kdg.programming6.keepdishesgoing.common.vo.TimeRange;

import java.time.DayOfWeek;
import java.util.Map;

/**
 * Read-only projection of OpeningHours, kept as a simple map for querying.
 */
public record OpeningHoursProjection(Map<DayOfWeek, TimeRange> hoursPerDay) {
    public TimeRange getForDay(DayOfWeek day) {
        return hoursPerDay.get(day);
    }
}
