package kdg.programming6.keepdishesgoing.restaurant.adapter.out.dish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ScheduledDishJpaRepository extends JpaRepository<ScheduledDishJpaEntity, UUID> {
    @Query("SELECT s FROM ScheduledDishJpaEntity s LEFT JOIN FETCH s.dishIds WHERE s.executed = false AND s.scheduledAt <= :now")
    List<ScheduledDishJpaEntity> findDueChanges(@Param("now") LocalDateTime now);

}
