package kdg.programming6.keepdishesgoing.order.adapter.out.order;

import kdg.programming6.keepdishesgoing.common.vo.Address;
import kdg.programming6.keepdishesgoing.order.domain.Basket;
import kdg.programming6.keepdishesgoing.order.domain.BasketItems;
import kdg.programming6.keepdishesgoing.order.domain.BasketStatus;
import kdg.programming6.keepdishesgoing.order.domain.Customer;
import kdg.programming6.keepdishesgoing.order.domain.Order;
import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjId;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderJpaEntity toJpaEntity(Order order) {
        OrderJpaEntity entity = new OrderJpaEntity();

        entity.setOrderId(order.getOrderId());
        entity.setBasketId(order.getBasket().getBasketId());
        entity.setRestaurantId(order.getBasket().getRestaurantId());
        entity.setMessage(order.getMessage());
        entity.setTotalPrice(order.getBasket().getTotalPrice());
        entity.setStatus("PENDING"); // or map from order status if you have one
        entity.setCreatedAt(OffsetDateTime.now());
        entity.setCustomer(toCustomerEmbeddable(order.getCustomer()));

        // Snapshot basket items as order items
        List<OrderItemJpaEntity> orderItems = order.getBasket().getItems().stream()
                .map(item -> new OrderItemJpaEntity(
                        item.getDishId().menuId(),
                        item.getDishId().dishId(),
                        item.getPriceAtAddition(),
                        item.getQuantity()
                ))
                .collect(Collectors.toList());

        entity.setOrderItems(orderItems);

        return entity;
    }

    public Order toDomain(OrderJpaEntity entity) {
        Customer customer = toCustomer(entity.getCustomer());

        // Reconstruct basket from order items snapshot
        List<BasketItems> basketItems = entity.getOrderItems().stream()
                .map(item -> new BasketItems(
                        new DishProjId(item.getMenuId(), item.getDishId()),
                        item.getPriceAtOrder(),
                        item.getQuantity()
                ))
                .collect(Collectors.toList());

        Basket basket = new Basket(
                entity.getBasketId(),
                entity.getRestaurantId(),
                basketItems,
                entity.getTotalPrice(),
                BasketStatus.ORDERED,
                entity.getMessage()
        );

        return new Order(
                entity.getOrderId(),
                basket,
                customer,
                entity.getMessage()
        );
    }

    private CustomerEmbeddable toCustomerEmbeddable(Customer customer) {
        CustomerEmbeddable embeddable = new CustomerEmbeddable();

        embeddable.setFirstName(customer.getFirstName());
        embeddable.setLastName(customer.getLastName());
        embeddable.setEmail(customer.getEmail());

        // Map address
        Address address = customer.getAddress();
        embeddable.setStreet(address.street());
        embeddable.setNumber(address.number());
        embeddable.setPostalCode(address.postalCode());
        embeddable.setCity(address.city());
        embeddable.setCountry(address.country());

        return embeddable;
    }

    private Customer toCustomer(CustomerEmbeddable embeddable) {
        Address address = new Address(
                embeddable.getStreet(),
                embeddable.getNumber(),
                embeddable.getPostalCode(),
                embeddable.getCity(),
                embeddable.getCountry()
        );

        return new Customer(
                embeddable.getFirstName(),
                embeddable.getLastName(),
                embeddable.getEmail(),
                address
        );
    }
}
