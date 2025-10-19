package kdg.programming6.keepdishesgoing.restaurant.adapter.out.dish;

public interface JpaScheduledDishChangeRepository extends JpaRepository<ScheduledDishChangeJpaEntity, UUID> {
    @Query("SELECT s FROM ScheduledDishChangeJpaEntity s WHERE s.scheduledAt <= CURRENT_TIMESTAMP")
    List<ScheduledDishChangeJpaEntity> findAllDue();
}
