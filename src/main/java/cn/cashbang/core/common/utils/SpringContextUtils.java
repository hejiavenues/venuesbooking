package cn.cashbang.core.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.config.ApplicationProperties;

/**
 * Spring Context 工具类
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月20日 下午10:50:24
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
	
	public static ApplicationContext applicationContext; 
	

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextUtils.applicationContext = applicationContext;
	}

	public static final Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	public static final <T> T getBean(String name, Class<T> requiredType) {
		return applicationContext.getBean(name, requiredType);
	}

	public static final boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	public static final boolean isSingleton(String name) {
		return applicationContext.isSingleton(name);
	}

	public static final Class<? extends Object> getType(String name) {
		return applicationContext.getType(name);
	}
	
	/**
	 * 获取自定义应用properties
	 * 
	 * @return
	 */
	public static final ApplicationProperties getApplicationProperties(){
		return getBean("ApplicationProperties", ApplicationProperties.class);
	}

}