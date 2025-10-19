package kdg.programming6.keepdishesgoing.order.adapter.out.menu;

import jakarta.persistence.*;
import kdg.programming6.keepdishesgoing.order.adapter.out.dish.DishProjectionId;
import kdg.programming6.keepdishesgoing.order.adapter.out.mapper.MenuProjectionMapper;
import kdg.programming6.keepdishesgoing.order.domain.projections.MenuProjection;
import kdg.programming6.keepdishesgoing.order.port.out.LoadMenuProjectionPort;
import kdg.programming6.keepdishesgoing.order.port.out.SaveDishProjectionPort;
import kdg.programming6.keepdishesgoing.order.port.out.SaveMenuProjectionPort;
import kdg.programming6.keepdishesgoing.restaurant.domain.Menu;
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
                menuProjection.getRestaurantId(),
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
