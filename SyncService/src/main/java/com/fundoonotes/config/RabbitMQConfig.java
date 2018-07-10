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

	public static final String TOPIC_EXCHANGE_NAME = "spring-amqp-exchange";

	public static final String USER_QUEUE = "userQueue";
	public static final String ROUTING_KEY_USER = "fundoonotes.user";

	public static final String NOTE_QUEUE = "noteQueue";
	public static final String ROUTING_KEY_NOTE = "fundoonotes.note";

	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange(TOPIC_EXCHANGE_NAME);
	}

	@Bean
	Queue userQueue() {
		return new Queue(USER_QUEUE);
	}

	@Bean
	Queue noteQueue() {
		return new Queue(NOTE_QUEUE);
	}

	@Bean
	Binding bindUserQueue() {
		return BindingBuilder.bind(userQueue()).to(topicExchange()).with(ROUTING_KEY_USER);
	}

	@Bean
	Binding bindNoteQueue() {
		return BindingBuilder.bind(noteQueue()).to(topicExchange()).with(ROUTING_KEY_NOTE);
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
