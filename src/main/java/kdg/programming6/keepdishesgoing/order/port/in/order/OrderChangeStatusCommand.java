package kdg.programming6.keepdishesgoing.order.port.in.order;

import java.util.UUID;

public record OrderChangeStatusCommand (UUID orderId, String status, String reason){
}
