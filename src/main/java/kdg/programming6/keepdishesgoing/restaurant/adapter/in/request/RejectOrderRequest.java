package kdg.programming6.keepdishesgoing.restaurant.adapter.in.request;

import jakarta.validation.constraints.NotBlank;

public record RejectOrderRequest(
    @NotBlank(message = "Rejection reason is required")
    String reason
) {}
