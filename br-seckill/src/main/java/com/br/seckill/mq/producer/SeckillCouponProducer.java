package com.br.seckill.mq.producer;

import com.br.common.constants.RabbitMqConstants;
import com.br.common.constants.SystemConstants;
import com.br.common.exception.DbException;
import com.br.seckill.mq.message.SeckillCouponMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 秒杀发券消息生产者。
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SeckillCouponProducer {

    private final RabbitTemplate rabbitTemplate;

    /**
     * 发送秒杀发券消息，并等待RabbitMQ发布确认。
     */
    public void sendSeckillCouponMessage(Long activityId, Long userId) {
        SeckillCouponMessage message = buildMessage(activityId, userId);
        CorrelationData correlationData = new CorrelationData(message.getMessageId());

        try {
            rabbitTemplate.convertAndSend(
                    RabbitMqConstants.SECKILL_EXCHANGE,
                    RabbitMqConstants.SECKILL_COUPON_ROUTING_KEY,
                    message,
                    correlationData
            );

            CorrelationData.Confirm confirm = correlationData.getFuture().get(3, TimeUnit.SECONDS);
            if (confirm == null || !confirm.isAck()) {
                log.warn("秒杀消息投递未确认：messageId={}, reason={}", message.getMessageId(),
                        confirm == null ? "confirm timeout" : confirm.getReason());
                throw new DbException(SystemConstants.SECKILL_MQ_SEND_FAILED);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new DbException(SystemConstants.SECKILL_MQ_SEND_FAILED);
        } catch (Exception e) {
            log.error("秒杀消息投递失败：message={}", message, e);
            throw new DbException(SystemConstants.SECKILL_MQ_SEND_FAILED);
        }
    }

    /**
     * 构建秒杀发券消息。
     */
    private SeckillCouponMessage buildMessage(Long activityId, Long userId) {
        SeckillCouponMessage message = new SeckillCouponMessage();
        message.setMessageId(UUID.randomUUID().toString());
        message.setActivityId(activityId);
        message.setUserId(userId);
        message.setCreateTime(System.currentTimeMillis());
        return message;
    }
}
