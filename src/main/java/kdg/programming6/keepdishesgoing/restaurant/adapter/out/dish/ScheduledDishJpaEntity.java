package kdg.programming6.keepdishesgoing.restaurant.adapter.out.dish;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "scheduled_dish_changes", schema = "restaurant")
public class ScheduledDishJpaEntity {

    @Id
    private UUID id;

    @ElementCollection
    @CollectionTable(name = "scheduled_dish_change_dishes", joinColumns = @JoinColumn(name = "schedule_id"), schema = "restaurant")
    @Column(name = "dish_id")
    private List<UUID> dishIds;
    private String action;
    private LocalDateTime scheduledAt;
    private boolean executed;
    private LocalDateTime executedAt;

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    public LocalDateTime getExecutedAt() {
        return executedAt;
    }

    public void setExecutedAt(LocalDateTime executedAt) {
        this.executedAt = executedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<UUID> getDishIds() {
        return dishIds;
    }

    public void setDishIds(List<UUID> dishIds) {
        this.dishIds = dishIds;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }
}
