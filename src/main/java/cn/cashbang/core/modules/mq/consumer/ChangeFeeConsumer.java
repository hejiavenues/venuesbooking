package cn.cashbang.core.modules.mq.consumer;


import cn.cashbang.core.common.constant.csMsg;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.common.utils.DateUtil;
import cn.cashbang.core.modules.mq.Consumer;
import cn.cashbang.core.modules.mq.QueueEnum;
import cn.cashbang.core.modules.mq.config.AmqpConfig;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 根据交易流水联动更新欠费明细表
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月14日 下午8:41:29
 */
@Component
public class ChangeFeeConsumer implements Consumer{

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AmqpConfig amqpConfig;
	@Autowired
	//private RedisTemplate<String, Object> redisTemplate;
	// @Autowired
	// private RabbitTemplate rabbitTemplate;
	private CachingConnectionFactory connectionFactory;
	private SimpleMessageListenerContainer container;
	
	@PostConstruct
	@Override
	public void start() {
		
		/*if(!this.amqpConfig.isConsumerStartup())return;

		logger.info("ConsumerStartup MQ Config: {}",amqpConfig);

		connectionFactory = new CachingConnectionFactory();
		connectionFactory.setAddresses(this.amqpConfig.getUrl()+":5673");
		connectionFactory.setUsername(this.amqpConfig.getUserName());
		connectionFactory.setPassword(this.amqpConfig.getPassword());
		connectionFactory.setVirtualHost(this.amqpConfig.getVirtualHost());
		connectionFactory.setPublisherConfirms(true);

		container = new SimpleMessageListenerContainer(connectionFactory);

		container.setExposeListenerChannel(true);
		container.setConcurrentConsumers(this.amqpConfig.getConcurrentConsumers());
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 设置确认模式手工确认
		
		container.addQueueNames(QueueEnum.TradeDetail.name());

		container.setMessageListener(new ChannelAwareMessageListener() {

			@Override
			public void onMessage(Message message, Channel channel) throws Exception {

				if (message != null) {
					String trade_id = new String(message.getBody());
					
				}
			}
		});
		container.start();*/
	}

	@PreDestroy
	@Override
	public void shutdown() {
		this.container.stop();
		this.connectionFactory.destroy();
	}	
	
}
