package kdg.programming6.keepdishesgoing.order.core.payment;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.order.domain.exceptions.OrderNotFoundException;
import kdg.programming6.keepdishesgoing.order.port.in.payment.PaymentUseCase;
import kdg.programming6.keepdishesgoing.order.port.out.order.LoadOrderPort;
import kdg.programming6.keepdishesgoing.order.port.out.payment.PaymentPort;
import kdg.programming6.keepdishesgoing.order.port.out.payment.SavePaymentStatePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
public class PaymentImpl implements PaymentUseCase {

    private final PaymentPort paymentPort;
    private final SavePaymentStatePort savePaymentStatePort;
    private final LoadOrderPort loadOrderPort;

    @Value("${app.payment.success-url}")
    private String successUrl;

    @Value("${app.payment.cancel-url}")
    private String cancelUrl;

    public PaymentImpl(PaymentPort paymentPort,
                       SavePaymentStatePort savePaymentStatePort,
                       LoadOrderPort loadOrderPort) {
        this.paymentPort = paymentPort;
        this.savePaymentStatePort = savePaymentStatePort;
        this.loadOrderPort = loadOrderPort;
    }

    @Override
    public String createPayment(BigDecimal amount, UUID orderId) {
        // Load order to verify it exists
        var order = loadOrderPort.loadOrder(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found: " + orderId));

        // Create payment session via adapter
        var session = paymentPort.createPayment(amount, "EUR", successUrl, cancelUrl);

        // Save payment state (link session to order)
        savePaymentStatePort.savePaymentState(new PaymentState(
                UUID.randomUUID(),
                orderId,
                session.getId(),
                amount,
                "PENDING"
        ));

        return session.getUrl();
    }

    @Override
    public boolean verifyPayment(String sessionId, UUID orderId) {
        boolean isVerified = paymentPort.verifyPayment(sessionId);

        if (isVerified) {
            savePaymentStatePort.updatePaymentState(sessionId, "COMPLETED");
        }

        return isVerified;
    }
}
