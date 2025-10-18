package kdg.programming6.keepdishesgoing.order.port.out;

import kdg.programming6.keepdishesgoing.order.domain.projections.MenuProjection;

public interface SaveMenuProjectionPort {
    MenuProjection save(MenuProjection menuProjection);
}
