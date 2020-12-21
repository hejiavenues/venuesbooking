package cn.cashbang.core.modules.mq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

//	public static final String EXCHANGE = "cashbang";
	@Value("${spring.rabbitmq.exchange}")
	private String exchange;
	@Value("${spring.rabbitmq.host}")
	private String url;
	@Value("${spring.rabbitmq.port}")
	private int port;
	@Value("${spring.rabbitmq.username}")
	private String userName;
	@Value("${spring.rabbitmq.password}")
	private String password;
	@Value("${spring.rabbitmq.virtual-host}")
	private String virtualHost;
	@Value("${spring.rabbitmq.concurrentConsumers}")
    private int concurrentConsumers;
	@Value("${spring.rabbitmq.consumerStartup}")
	private boolean consumerStartup;
	@Value("${spring.rabbitmq.producerStartup}")
	private boolean producerStartup;

	public boolean isProducerStartup(){
		return producerStartup;
	}
	public boolean isConsumerStartup() {
		return consumerStartup;
	}

	public void setProducerStartup(boolean producerStartup) {
		this.producerStartup = producerStartup;
	}

	public void setConsumerStartup(boolean consumerStartup) {
		this.consumerStartup = consumerStartup;
	}

	public int getConcurrentConsumers() {
		return concurrentConsumers;
	}

	public void setConcurrentConsumers(int concurrentConsumers) {
		this.concurrentConsumers = concurrentConsumers;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVirtualHost() {
		return virtualHost;
	}

	public void setVirtualHost(String virtualHost) {
		this.virtualHost = virtualHost;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	@Override
	public String toString() {
		return "AmqpConfig{" +
				"exchange='" + exchange + '\'' +
				", url='" + url + '\'' +
				", port=" + port +
				", userName='" + userName + '\'' +
				", virtualHost='" + virtualHost + '\'' +
				", concurrentConsumers=" + concurrentConsumers +
				", consumerStartup=" + consumerStartup +
				", producerStartup=" + producerStartup +
				'}';
	}
}