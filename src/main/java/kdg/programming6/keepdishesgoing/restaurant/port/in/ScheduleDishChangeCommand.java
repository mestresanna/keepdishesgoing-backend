package kdg.programming6.keepdishesgoing.restaurant.port.in;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ScheduleDishChangeCommand(
        List<UUID> dishIds,
        String action,
        LocalDateTime scheduledAt
) {}
