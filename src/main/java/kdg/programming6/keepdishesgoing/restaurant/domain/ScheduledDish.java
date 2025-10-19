package kdg.programming6.keepdishesgoing.restaurant.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ScheduledDishChange {

    private final UUID id;
    private final List<UUID> dishIds;
    private DishAction action;
    private final LocalDateTime scheduledAt;

    public ScheduledDishChange(UUID id, List<UUID> dishIds, DishAction action, LocalDateTime scheduledAt) {
        this.id = id;
        this.dishIds = dishIds;
        this.action = action;
        this.scheduledAt = scheduledAt;
    }

    public static ScheduledDishChange schedule(List<UUID> dishIds, DishAction action, LocalDateTime when) {
        return new ScheduledDishChange(UUID.randomUUID(), dishIds, action, when);
    }

    public boolean isDue(LocalDateTime now) {
        return scheduledAt.isBefore(now) || scheduledAt.isEqual(now);
    }

    public UUID getId() { return id; }
    public List<UUID> getDishIds() { return dishIds; }
    public DishAction getAction() { return action; }
    public LocalDateTime getScheduledAt() { return scheduledAt; }
}
