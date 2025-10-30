package kdg.programming6.keepdishesgoing.order.adapter.in.response.order;

import kdg.programming6.keepdishesgoing.order.adapter.in.response.CustomerOrderDto;
import kdg.programming6.keepdishesgoing.order.domain.Order;
import kdg.programming6.keepdishesgoing.order.domain.BasketItems;
import kdg.programming6.keepdishesgoing.order.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapperDto {

    public OrdersDto toDto(Order order) {
        List<OrderItemDto> items = order.getBasket().getItems().stream()
                .map(this::toOrderItemDto)
                .collect(Collectors.toList());

        CustomerOrderDto customerDto = toCustomerDto(order.getCustomer());

        return new OrdersDto(
                order.getOrderId(),
                order.getBasket().getRestaurantId(),
                customerDto,
                items,
                order.getTotalPrice(),
                order.getStatus().toString(),
                order.getMessage(),
                order.getRejectionReason()
        );
    }

    private OrderItemDto toOrderItemDto(BasketItems item) {
        return new OrderItemDto(
                item.getDishId().dishId(),
                item.getDishName(),
                item.getQuantity(),
                item.getPriceAtAddition(),
                item.totalPrice()
        );
    }

    private CustomerOrderDto toCustomerDto(Customer customer) {
        return new CustomerOrderDto(
                customer.getName().split(" ")[0],  // firstName
                customer.getName().split(" ").length > 1 
                    ? customer.getName().split(" ")[1] 
                    : "",  // lastName
                customer.getEmail(),
                customer.getAddress().street(),
                String.valueOf(customer.getAddress().number()),
                customer.getAddress().city(),
                customer.getAddress().postalCode(),
                customer.getAddress().country()
        );
    }
}