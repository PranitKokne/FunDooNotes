package com.fundoonotes.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.fundoonotes")
public class RabbitMQConfig {

	public static final String topicExchangeName = "spring-amqp-exchange";

	public static final String queueNameOne = "saveUpdateQueue";
	public static final String queueNameThree = "deleteQueue";

	public static final String routingKeyOne = "rabbit.key.saveupdate";
	public static final String routingKeyThree = "rabbit.key.delete";

	@Bean
	Queue queueOne() {
		return new Queue(queueNameOne, false);
	}

	@Bean
	Queue queueThree() {
		return new Queue(queueNameThree, false);
	}

	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange(topicExchangeName);
	}

	@Bean
	public Binding bindingOne() {
		return BindingBuilder.bind(queueOne()).to(topicExchange()).with(routingKeyOne);
	}

	@Bean
	public Binding bindingThree() {
		return BindingBuilder.bind(queueThree()).to(topicExchange()).with(routingKeyThree);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public SimpleRabbitListenerContainerFactory containerFactory(ConnectionFactory connectionFactory,
			SimpleRabbitListenerContainerFactoryConfigurer configurer) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		factory.setMessageConverter(jsonMessageConverter());
		return factory;
	}
}
