package com.education.publisher;

import lombok.extern.log4j.Log4j2;
import model.dto.ApprovalDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;

import static model.constant.Constant.REST_TO_SERVICE_APPROVAL_QUEUE;

/**
 * Писатель для очередей с ApprovalDto
 */
@Component
@AllArgsConstructor
@Log4j2
public class ApprovalPublisher {

    private final RabbitTemplate rabbitTemplate;

    /**
     * Принимает объект ApprovalDto и помещает его в очередь REST_TO_SERVICE_APPROVAL_QUEUE
     * @param approvalDto - объект ApprovalDto, который будет помещен в очередь
     */
    public void produce(ApprovalDto approvalDto) {
        log.info("Publish to queue \"REST_TO_SERVICE_APPROVAL_QUEUE\" {}", approvalDto);
        rabbitTemplate.convertAndSend(REST_TO_SERVICE_APPROVAL_QUEUE, approvalDto);
        log.info("Added to the queue \"REST_TO_SERVICE_APPROVAL_QUEUE\" {}", approvalDto);
    }
}