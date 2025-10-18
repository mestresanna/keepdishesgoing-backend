package kdg.programming6.keepdishesgoing.order.adapter.out;

import kdg.programming6.keepdishesgoing.order.domain.projections.MenuProjection;
import kdg.programming6.keepdishesgoing.order.port.out.SaveMenuProjectionPort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuProjectionJpaAdapter implements SaveMenuProjectionPort {
    private final MenuProjectionJpaRepository menuProjectionJpaRepository;

    public MenuProjectionJpaAdapter(MenuProjectionJpaRepository menuProjectionJpaRepository) {
        this.menuProjectionJpaRepository = menuProjectionJpaRepository;
    }
    @Override
    public MenuProjection save(MenuProjection menuProjection) {
        MenuProjectionJpaEntity menuProjectionJpaEntity = new MenuProjectionJpaEntity(
                menuProjection.getId(),
                menuProjection.getRestaurantId(),
                menuProjection.getAvgPrice()
        );

        this.menuProjectionJpaRepository.save(menuProjectionJpaEntity);
        return new MenuProjection(menuProjection.getId(), menuProjection.getRestaurantId(), menuProjection.getDishes(), menuProjection.getAvgPrice());
    }
}
