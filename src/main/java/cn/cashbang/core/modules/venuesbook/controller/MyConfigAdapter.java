package cn.cashbang.core.modules.venuesbook.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.cashbang.core.common.utils.SpringContextUtils;

@Configuration
@Lazy
public class MyConfigAdapter extends WebMvcConfigurerAdapter {
	
	public String loaclUrl = SpringContextUtils.getApplicationProperties().getUploadInfo().get("imageurl");
    /**
     * 配置静态访问资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/picture/**").addResourceLocations("file:"+loaclUrl);
        super.addResourceHandlers(registry);
    }
}
