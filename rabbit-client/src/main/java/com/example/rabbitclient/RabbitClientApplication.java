package com.example.rabbitclient;

import com.example.rabbitclient.client.MyData;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.UnknownHostException;

@SpringBootApplication
public class RabbitClientApplication {


	public static final String topicExchangeName = "spring-boot-exchange";

	public static final String queueName = "spring-boot";

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("food");
	}


	public static void main(String[] args) throws UnknownHostException {
		MyData.myInfo();
		SpringApplication.run(RabbitClientApplication.class, args);
	}

}
