package com.br.common.constants;

/**
 * RabbitMQ常量类，统一管理秒杀消息使用的交换机、队列和路由键。
 */
public class RabbitMqConstants {

    /**
     * 秒杀发券主交换机。
     */
    public static final String SECKILL_EXCHANGE = "battery.seckill.exchange";

    /**
     * 秒杀发券主队列。
     */
    public static final String SECKILL_COUPON_QUEUE = "battery.seckill.coupon.queue";

    /**
     * 秒杀发券路由键。
     */
    public static final String SECKILL_COUPON_ROUTING_KEY = "seckill.coupon";

    /**
     * 秒杀发券重试交换机。
     */
    public static final String SECKILL_RETRY_EXCHANGE = "battery.seckill.retry.exchange";

    /**
     * 秒杀发券重试队列。
     */
    public static final String SECKILL_RETRY_QUEUE = "battery.seckill.coupon.retry.queue";

    /**
     * 秒杀发券重试路由键。
     */
    public static final String SECKILL_RETRY_ROUTING_KEY = "seckill.coupon.retry";

    /**
     * 秒杀发券死信交换机。
     */
    public static final String SECKILL_DEAD_EXCHANGE = "battery.seckill.dead.exchange";

    /**
     * 秒杀发券死信队列。
     */
    public static final String SECKILL_DEAD_QUEUE = "battery.seckill.coupon.dead.queue";

    /**
     * 秒杀发券死信路由键。
     */
    public static final String SECKILL_DEAD_ROUTING_KEY = "seckill.coupon.dead";

    /**
     * 重试队列等待时间，消息进入重试队列后延迟5秒再回到主队列。
     */
    public static final Integer SECKILL_RETRY_TTL = 5000;

    /**
     * 最大重试次数，超过后进入死信队列。
     */
    public static final Long SECKILL_MAX_RETRY_COUNT = 3L;
}
