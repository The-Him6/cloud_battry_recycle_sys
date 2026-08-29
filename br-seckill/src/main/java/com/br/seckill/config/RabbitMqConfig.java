package com.br.seckill.config;

import com.br.common.constants.RabbitMqConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置类，声明秒杀发券需要的主队列、重试队列和死信队列。
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 主交换机，正常秒杀发券消息先投递到这里。
     */
    @Bean
    public DirectExchange seckillExchange() {
        return ExchangeBuilder.directExchange(RabbitMqConstants.SECKILL_EXCHANGE).durable(true).build();
    }

    /**
     * 主队列，消费失败后会把消息转发到重试交换机。
     */
    @Bean
    public Queue seckillCouponQueue() {
        return QueueBuilder.durable(RabbitMqConstants.SECKILL_COUPON_QUEUE)
                .deadLetterExchange(RabbitMqConstants.SECKILL_RETRY_EXCHANGE)
                .deadLetterRoutingKey(RabbitMqConstants.SECKILL_RETRY_ROUTING_KEY)
                .build();
    }

    /**
     * 绑定主交换机和主队列。
     */
    @Bean
    public Binding seckillCouponBinding() {
        return BindingBuilder.bind(seckillCouponQueue())
                .to(seckillExchange())
                .with(RabbitMqConstants.SECKILL_COUPON_ROUTING_KEY);
    }

    /**
     * 重试交换机，承接主队列消费失败的消息。
     */
    @Bean
    public DirectExchange seckillRetryExchange() {
        return ExchangeBuilder.directExchange(RabbitMqConstants.SECKILL_RETRY_EXCHANGE).durable(true).build();
    }

    /**
     * 重试队列，消息等待一段时间后重新回到主交换机。
     */
    @Bean
    public Queue seckillRetryQueue() {
        return QueueBuilder.durable(RabbitMqConstants.SECKILL_RETRY_QUEUE)
                .ttl(RabbitMqConstants.SECKILL_RETRY_TTL)
                .deadLetterExchange(RabbitMqConstants.SECKILL_EXCHANGE)
                .deadLetterRoutingKey(RabbitMqConstants.SECKILL_COUPON_ROUTING_KEY)
                .build();
    }

    /**
     * 绑定重试交换机和重试队列。
     */
    @Bean
    public Binding seckillRetryBinding() {
        return BindingBuilder.bind(seckillRetryQueue())
                .to(seckillRetryExchange())
                .with(RabbitMqConstants.SECKILL_RETRY_ROUTING_KEY);
    }

    /**
     * 死信交换机，承接超过重试上限仍失败的消息。
     */
    @Bean
    public DirectExchange seckillDeadExchange() {
        return ExchangeBuilder.directExchange(RabbitMqConstants.SECKILL_DEAD_EXCHANGE).durable(true).build();
    }

    /**
     * 死信队列，后续可以给管理员做异常消息查看和人工补偿。
     */
    @Bean
    public Queue seckillDeadQueue() {
        return QueueBuilder.durable(RabbitMqConstants.SECKILL_DEAD_QUEUE).build();
    }

    /**
     * 绑定死信交换机和死信队列。
     */
    @Bean
    public Binding seckillDeadBinding() {
        return BindingBuilder.bind(seckillDeadQueue())
                .to(seckillDeadExchange())
                .with(RabbitMqConstants.SECKILL_DEAD_ROUTING_KEY);
    }

    /**
     * 使用JSON序列化消息，避免Java原生序列化带来的可读性和兼容性问题。
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * RabbitTemplate使用JSON消息转换器。
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }

    /**
     * 监听容器使用JSON消息转换器，并保持手动ACK。
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        factory.setAcknowledgeMode(org.springframework.amqp.core.AcknowledgeMode.MANUAL);
        factory.setPrefetchCount(1);
        return factory;
    }
}
