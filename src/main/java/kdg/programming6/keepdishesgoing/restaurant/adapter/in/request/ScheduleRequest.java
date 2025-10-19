package kdg.programming6.keepdishesgoing.restaurant.adapter.in.request;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ScheduleRequest(List<UUID> dishIds, String action, LocalDateTime scheduledTime) {}
