package kdg.programming6.keepdishesgoing.order.adapter.out.menu;

import kdg.programming6.keepdishesgoing.order.adapter.out.mapper.MenuProjectionMapper;
import kdg.programming6.keepdishesgoing.order.domain.projections.MenuProjection;
import kdg.programming6.keepdishesgoing.order.port.out.menu.LoadMenuProjectionPort;
import kdg.programming6.keepdishesgoing.order.port.out.menu.SaveMenuProjectionPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MenuProjectionJpaAdapter implements SaveMenuProjectionPort, LoadMenuProjectionPort {
    private final MenuProjectionJpaRepository menuProjectionJpaRepository;
    private final MenuProjectionMapper menuProjectionMapper;

    public MenuProjectionJpaAdapter(MenuProjectionJpaRepository menuProjectionJpaRepository, MenuProjectionMapper menuProjectionMapper) {
        this.menuProjectionJpaRepository = menuProjectionJpaRepository;
        this.menuProjectionMapper = menuProjectionMapper;
    }
    @Override
    public MenuProjection save(MenuProjection menuProjection) {
        MenuProjectionJpaEntity menuProjectionJpaEntity = new MenuProjectionJpaEntity(
                menuProjection.getId(),
                menuProjection.getRestaurantId().restaurantId(),
                menuProjection.getAvgPrice()
        );

        this.menuProjectionJpaRepository.save(menuProjectionJpaEntity);
        return new MenuProjection(menuProjection.getId(), menuProjection.getRestaurantId(), menuProjection.getDishes(), menuProjection.getAvgPrice());
    }

    @Override
    public Optional<MenuProjection> loadbyId(UUID menuId) {
        return menuProjectionJpaRepository.findById(menuId).map(menuProjectionMapper::toDomain);
    }

    @Override
    public Optional<List<MenuProjection>> loadAllMenuProjections(UUID restaurantId) {
        List<MenuProjection> menus = menuProjectionJpaRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(menuProjectionMapper::toDomain)
                .toList();

        return menus.isEmpty() ? Optional.empty() : Optional.of(menus);    }
}
