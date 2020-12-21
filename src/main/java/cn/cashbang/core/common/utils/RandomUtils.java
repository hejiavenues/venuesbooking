package cn.cashbang.core.common.utils;

public final class RandomUtils {

	
	public static final String UUID(){
		return java.util.UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	private RandomUtils(){}
}
