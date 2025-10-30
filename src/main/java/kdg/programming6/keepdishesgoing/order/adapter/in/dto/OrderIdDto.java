package kdg.programming6.keepdishesgoing.order.adapter.in.dto;

import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.UUID;

public record OrderIdDto (UUID orderId) {
}
