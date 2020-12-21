package cn.cashbang.core.modules.mq.producer;

import cn.cashbang.core.modules.mq.Producer;
import cn.cashbang.core.modules.mq.config.AmqpConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.rabbit.support.DefaultMessagePropertiesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class RabbitProducer implements Producer{

	protected static Logger logger = LoggerFactory.getLogger(RabbitProducer.class);

	@Autowired
	private AmqpConfig amqpConfig;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	private CachingConnectionFactory connectionFactory;

	@SuppressWarnings("deprecation")
	@PostConstruct
	@Override
	public void start() {
		/*if(!this.amqpConfig.isProducerStartup())return;

		logger.info("ProducerStartup MQ Config: {}",amqpConfig);

		connectionFactory = new CachingConnectionFactory();
		connectionFactory.setAddresses(this.amqpConfig.getUrl()+":5673");
		connectionFactory.setUsername(this.amqpConfig.getUserName());
		connectionFactory.setPassword(this.amqpConfig.getPassword());
		connectionFactory.setVirtualHost(this.amqpConfig.getVirtualHost());
		connectionFactory.setPublisherConfirms(true);
		rabbitTemplate = new RabbitTemplate(connectionFactory);
		DefaultMessagePropertiesConverter messagePropertiesConverter = new DefaultMessagePropertiesConverter();
		messagePropertiesConverter.setCorrelationIdAsString(DefaultMessagePropertiesConverter.CorrelationIdPolicy.STRING);
		rabbitTemplate.setMessagePropertiesConverter(messagePropertiesConverter);*/

	}

	public void send(String queue, byte[] message) {
		CorrelationData correlationId = new CorrelationData(queue + ":" + UUID.randomUUID().toString());
		rabbitTemplate.convertAndSend(amqpConfig.getExchange(), queue, message, correlationId);

	}

	@Override
	public void send(String queue, byte[] message, String id) {
		CorrelationData correlationId = new CorrelationData(queue + ":" + id);
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setCorrelationIdString(queue + ":" + id);
		Message msg = new Message(message, messageProperties);
		rabbitTemplate.send(amqpConfig.getExchange(), queue, msg, correlationId);

	}

	@PreDestroy
	@Override
	public void shutdown() {
		connectionFactory.destroy();
	}


}
