package kdg.programming6.keepdishesgoing.order.port.out.menu;

import kdg.programming6.keepdishesgoing.order.domain.projections.MenuProjection;

public interface SaveMenuProjectionPort {
    MenuProjection save(MenuProjection menuProjection);
}
