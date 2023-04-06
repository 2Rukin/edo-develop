package com.education.listener;

import com.education.service.approval.ApprovalService;
import lombok.extern.log4j.Log4j2;
import model.dto.ApprovalDto;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static model.constant.Constant.REST_TO_SERVICE_APPROVAL_QUEUE;

/**
 * Слушатель для очередей с ApprovalDto
 */
@Component
@AllArgsConstructor
@Log4j2
public class ApprovalListener {
    private final ApprovalService approvalService;

    /**
     * Подписывается на очередь REST_TO_SERVICE_APPROVAL_QUEUE, получает из нее объект ApprovalDto и передает в {@link ApprovalService#save(ApprovalDto)}
     * @param approvalDto - объект ApprovalDto, полученный из очереди
     */
    @RabbitListener(queues = REST_TO_SERVICE_APPROVAL_QUEUE)
    public void receive(ApprovalDto approvalDto) {
        log.info("Received from the queue \"REST_TO_SERVICE_APPROVAL_QUEUE\" {}", approvalDto);
        approvalService.save(approvalDto);
    }
}
