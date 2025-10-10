package kdg.programming6.keepdishesgoing.restaurant.domain;

import java.time.LocalDateTime;

public class PublishDishesSchedule {
    private final LocalDateTime scheduledAt;

    public PublishDishesSchedule(LocalDateTime scheduledAt) {
        if (scheduledAt.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Cannot schedule changes in the past");
        }
        this.scheduledAt = scheduledAt;
    }

    public LocalDateTime scheduledAt() {
        return scheduledAt;
    }

    public boolean isDue() {
        return LocalDateTime.now().isAfter(scheduledAt);
    }
}
