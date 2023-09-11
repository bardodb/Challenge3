package br.com.compassuol.sp.challenge.msproducts.consumer;

import br.com.compassuol.sp.challenge.msproducts.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderConsumer {
    private Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);
    private ProductRepository productRepository;
    private RabbitTemplate rabbitTemplate;

    public OrderConsumer(ProductRepository productRepository, RabbitTemplate rabbitTemplate) {
        this.productRepository = productRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "order_queue")
    public boolean consumerProductIds(List<Long> productIds){
        boolean verify = true;
        for(Long productId : productIds){
            if(!productRepository.existsById(productId)){
                verify = false;
                break;
            }
        }
        LOGGER.info("Receive from Order "+ productIds);
        return verify;

    }
}
