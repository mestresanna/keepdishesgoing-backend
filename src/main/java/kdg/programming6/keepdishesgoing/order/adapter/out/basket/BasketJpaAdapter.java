package kdg.programming6.keepdishesgoing.order.adapter.out.basket;

import kdg.programming6.keepdishesgoing.order.adapter.out.mapper.BasketMapper;
import kdg.programming6.keepdishesgoing.order.domain.Basket;
import kdg.programming6.keepdishesgoing.order.port.out.DeleteBasketPort;
import kdg.programming6.keepdishesgoing.order.port.out.LoadBasketPort;
import kdg.programming6.keepdishesgoing.order.port.out.SaveBasketPort;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.RestaurantId;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class BasketJpaAdapter implements SaveBasketPort, LoadBasketPort, DeleteBasketPort {

    private final BasketJpaRepository basketJpaRepository;
    private final BasketMapper basketMapper;

    public BasketJpaAdapter(BasketJpaRepository basketJpaRepository, BasketMapper basketMapper) {
        this.basketJpaRepository = basketJpaRepository;
        this.basketMapper = basketMapper;
    }

    @Override
    public Basket saveBasket(Basket basket) {
        BasketJpaEntity entity = basketMapper.toEntity(basket);
        BasketJpaEntity saved = basketJpaRepository.save(entity);
        return basketMapper.toDomain(saved);
    }

    @Override
    public Optional<Basket> loadBasket(UUID basketId) {
        return basketJpaRepository.findById(basketId).map(basketMapper::toDomain);
    }

    @Override
    public void deleteBasket(Basket basket) {
        BasketJpaEntity entity = basketMapper.toEntity(basket);
        basketJpaRepository.delete(entity);
    }
}
