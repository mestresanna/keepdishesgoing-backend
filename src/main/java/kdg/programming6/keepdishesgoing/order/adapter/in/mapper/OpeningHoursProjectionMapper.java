package kdg.programming6.keepdishesgoing.order.adapter.in.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kdg.programming6.keepdishesgoing.order.domain.vo.OpeningHoursProjection;
import kdg.programming6.keepdishesgoing.order.domain.vo.TimeRangeProjection;

import java.time.DayOfWeek;
import java.util.Map;

public class OpeningHoursProjectionMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static OpeningHoursProjection fromJson(String json) {
        try {
            // Deserialize into Map<DayOfWeek, TimeRangeProjection>
            Map<DayOfWeek, TimeRangeProjection> hours = objectMapper.readValue(
                    json, new TypeReference<Map<DayOfWeek, TimeRangeProjection>>() {}
            );
            return new OpeningHoursProjection(hours);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse OpeningHours JSON: " + json, e);
        }
    }

    public static String toJson(OpeningHoursProjection projection) {
        try {
            return objectMapper.writeValueAsString(projection.hoursPerDay());
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize OpeningHoursProjection", e);
        }
    }
}