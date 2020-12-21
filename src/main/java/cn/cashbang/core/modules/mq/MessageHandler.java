package cn.cashbang.core.modules.mq;

public interface MessageHandler {
	boolean hand(byte[] message);

}
