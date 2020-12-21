package cn.cashbang.core.modules.mq;


//import ch.qos.logback.core.spi.LifeCycle;

import java.util.concurrent.TimeUnit;

public interface Producer {

	void start();

	void shutdown();
	
	void send(String queue, byte[] message);

	void send(String queue, byte[] message, String id);

//	void delaySend(String queue, byte[] message, String id, long delayTime, TimeUnit timeUnit);


}
