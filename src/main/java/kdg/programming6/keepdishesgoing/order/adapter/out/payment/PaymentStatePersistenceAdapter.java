package kdg.programming6.keepdishesgoing.order.adapter.out.payment;

import kdg.programming6.keepdishesgoing.order.core.payment.PaymentState;
import kdg.programming6.keepdishesgoing.order.port.out.payment.SavePaymentStatePort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PaymentStatePersistenceAdapter implements SavePaymentStatePort {

    private final PaymentStateRepository repository;

    public PaymentStatePersistenceAdapter(PaymentStateRepository repository) {
        this.repository = repository;
    }

    @Override
    public void savePaymentState(PaymentState paymentState) {
        PaymentStateJpaEntity entity = new PaymentStateJpaEntity();
        entity.setPaymentId(paymentState.paymentId());
        entity.setOrderId(paymentState.orderId());
        entity.setStripeSessionId(paymentState.stripeSessionId());
        entity.setAmount(paymentState.amount());
        entity.setStatus(paymentState.status());
        repository.save(entity);
    }

    @Override
    public void updatePaymentState(String stripeSessionId, String status) {
        var entity = repository.findByStripeSessionId(stripeSessionId)
                .orElseThrow(() -> new RuntimeException("Payment state not found: " + stripeSessionId));
        entity.setStatus(status);
        repository.save(entity);
    }

    @Override
    public Optional<PaymentState> findByStripeSessionId(String sessionId) {
        return repository.findByStripeSessionId(sessionId)
                .map(entity -> new PaymentState(
                        entity.getPaymentId(),
                        entity.getOrderId(),
                        entity.getStripeSessionId(),
                        entity.getAmount(),
                        entity.getStatus()
                ));
    }
}