package kdg.programming6.keepdishesgoing.restaurant.adapter.out.restaurant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kdg.programming6.keepdishesgoing.common.vo.TimeRange;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.OpeningHours;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OpeningHoursMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
    }

    public String serializeOpeningHours(OpeningHours openingHours) {
        try {
            if (openingHours == null) return null;
            return objectMapper.writeValueAsString(openingHours.hoursPerDay());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing OpeningHours", e);
        }
    }
    public OpeningHours deserializeOpeningHours(String json) {
        if (json == null || json.isEmpty()) return null;
        try {
            Map<String, TimeRange> stringMap = objectMapper.readValue(
                    json, new TypeReference<Map<String, TimeRange>>() {}
            );
            Map<DayOfWeek, TimeRange> map = stringMap.entrySet().stream()
                    .collect(Collectors.toMap(
                            e -> DayOfWeek.valueOf(e.getKey()),
                            Map.Entry::getValue
                    ));
            return new OpeningHours(map);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing OpeningHours", e);
        }
    }

}
