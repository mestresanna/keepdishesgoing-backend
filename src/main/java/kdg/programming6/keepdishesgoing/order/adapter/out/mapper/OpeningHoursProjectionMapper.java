package kdg.programming6.keepdishesgoing.order.adapter.out.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kdg.programming6.keepdishesgoing.common.vo.TimeRange;
import kdg.programming6.keepdishesgoing.order.domain.vo.OpeningHoursProjection;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.Map;

@Component
public class OpeningHoursProjectionMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // optional: serialize as "HH:mm:ss"
    }

    public static OpeningHoursProjection fromJson(String json) {
        try {
            Map<DayOfWeek, TimeRange> hours = objectMapper.readValue(
                    json, new com.fasterxml.jackson.core.type.TypeReference<Map<DayOfWeek, TimeRange>>() {}
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
    }}