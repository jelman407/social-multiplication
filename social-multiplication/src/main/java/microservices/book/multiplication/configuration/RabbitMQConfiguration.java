package microservices.book.multiplication.configuration;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * 이벤트를 사용하기 위한 RabbitMQ 설정
 */
@Configuration
@Slf4j
public class RabbitMQConfiguration {

	@Bean
	public TopicExchange multiplicationExchange(@Value("${multiplication.exchange}") final String exchangeName) {
		return new TopicExchange(exchangeName);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		
		log.info("------------ RabbitMQConfiguration : rabbitTemplate START ------------");
		log.info("------------ RabbitMQConfiguration : rabbitTemplate connectionFactory : " + connectionFactory);
		
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		
		log.info("------------ RabbitMQConfiguration : rabbitTemplate RabbitTemplate : " + rabbitTemplate);
		
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		log.info("------------ RabbitMQConfiguration : producerJackson2MessageConverter START ------------");
		return new Jackson2JsonMessageConverter();
	}

}
