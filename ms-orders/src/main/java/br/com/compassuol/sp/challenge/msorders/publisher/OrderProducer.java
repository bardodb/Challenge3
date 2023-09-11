package br.com.compassuol.sp.challenge.msorders.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProducer {
    private Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);
    private RabbitTemplate rabbitTemplate;

    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public boolean sendAndReceiveVerification(List<Long> productIds){
        LOGGER.info("Send to Product "+ productIds);
        Boolean verification = (Boolean) rabbitTemplate
                .convertSendAndReceive("order_exchange","order_routing_key", productIds);
        return verification != null ? verification : false;
    }


}
