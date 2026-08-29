package com.br.seckill.mq.message;

import lombok.Data;

import java.io.Serializable;

/**
 * 秒杀发券消息体，只保存消费者落库需要的最小字段。
 */
@Data
public class SeckillCouponMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息ID，用于RabbitMQ发布确认和日志追踪。
     */
    private String messageId;

    /**
     * 秒杀活动ID。
     */
    private Long activityId;

    /**
     * 抢券用户ID。
     */
    private Long userId;

    /**
     * 消息创建时间戳。
     */
    private Long createTime;
}
