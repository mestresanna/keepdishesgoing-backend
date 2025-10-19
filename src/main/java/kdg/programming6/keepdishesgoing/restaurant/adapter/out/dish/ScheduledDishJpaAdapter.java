package kdg.programming6.keepdishesgoing.restaurant.adapter.out.dish;

import kdg.programming6.keepdishesgoing.restaurant.domain.DishAction;
import kdg.programming6.keepdishesgoing.restaurant.domain.ScheduledDish;
import kdg.programming6.keepdishesgoing.restaurant.port.out.ScheduledDishChangePort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public class ScheduledDishJpaAdapter implements ScheduledDishChangePort {
    private ScheduledDishJpaRepository scheduledDishJpaRepository;
    public ScheduledDishJpaAdapter(ScheduledDishJpaRepository scheduledDishJpaRepository) {
        this.scheduledDishJpaRepository = scheduledDishJpaRepository;
    }


    @Override
    public void save(ScheduledDish schedule) {
        ScheduledDishJpaEntity entity = new ScheduledDishJpaEntity();
        entity.setId(schedule.getId());
        entity.setDishIds(schedule.getDishIds());
        entity.setAction(schedule.getAction().toString());
        entity.setScheduledAt(schedule.getScheduledAt());
        scheduledDishJpaRepository.save(entity);
    }

    @Override
    public List<ScheduledDish> findAllPending() {
        return scheduledDishJpaRepository.findDueChanges(LocalDateTime.now()).stream()
                .map(e -> new ScheduledDish(e.getId(), e.getDishIds(), DishAction.valueOf(e.getAction()), e.getScheduledAt()))
                .toList();
    }

    @Override
    public void markExecuted(UUID id) {
        ScheduledDishJpaEntity entity = scheduledDishJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Schedule not found"));
        entity.setExecuted(true);
        entity.setExecutedAt(LocalDateTime.now());
        scheduledDishJpaRepository.save(entity);
    }
}
