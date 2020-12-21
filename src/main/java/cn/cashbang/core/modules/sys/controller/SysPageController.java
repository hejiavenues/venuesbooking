package cn.cashbang.core.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统页面视图
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月9日 下午3:33:00
 */
@Controller
public class SysPageController {

	/**
	 * 页面跳转
	 * @param module
	 * @param function
	 * @param url
	 * @return
	 */
	@RequestMapping("{module}/{function}/{url}.html")
	public String page(@PathVariable("module") String module, @PathVariable("function") String function,
			@PathVariable("url") String url) {
		return module + "/" + function + "/" + url;
	}
	
	/**
	 * 页面跳转
	 * @param module
	 * @param url
	 * @return
	 */
	@RequestMapping("{module}/{url}.html")
	public String page(@PathVariable("module") String module, @PathVariable("url") String url) {
		return module + "/" + url;
	}

}
