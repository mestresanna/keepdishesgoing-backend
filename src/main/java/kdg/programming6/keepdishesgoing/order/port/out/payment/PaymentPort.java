package kdg.programming6.keepdishesgoing.order.port.out.payment;

import com.stripe.model.checkout.Session;
import java.math.BigDecimal;

public interface PaymentPort {
    Session createPayment(BigDecimal amount, String currency, String successUrl, String cancelUrl);
    boolean verifyPayment(String sessionId);
}