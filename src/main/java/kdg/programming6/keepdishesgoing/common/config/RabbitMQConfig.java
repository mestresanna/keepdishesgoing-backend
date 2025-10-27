package be.kdg.keepdishesgoing.common.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import java.util.Map;


@Configuration
public class RabbitMQConfig {

    @Bean
    SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        simpleRabbitListenerContainerFactory.setMessageConverter(jackson2JsonMessageConverter());
        return simpleRabbitListenerContainerFactory;
    }

    @Bean
    MessageConverter jackson2JsonMessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();

        // Map the publisher type to the consumer type
        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages("*"); // or a specific package
        classMapper.setIdClassMapping(Map.of(
                "kdg.deliveryservice.domain.event.DeliveryDeliveredEvent",
                kdg.programming6.keepdishesgoing.common.events.OrderDeliveredEvent.class,
                "kdg.deliveryservice.domain.event.DeliveryJobLocationUpdatedEvent",
                kdg.programming6.keepdishesgoing.common.events.DeliveryJobLocationUpdatedEvent.class
        ));


        converter.setClassMapper(classMapper);
        return converter;
    }

}

