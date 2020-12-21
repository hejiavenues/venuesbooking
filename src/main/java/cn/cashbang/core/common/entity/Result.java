package cn.cashbang.core.common.entity;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 页面响应entity
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月8日 上午11:40:42
 */
public class Result extends HashMap<String, Object> {
	
	private static final long serialVersionUID = 1L;
	
	public Result() {
		put("code", 0);
	}
	
	/**
	 * 
	 * 警告
	 * @return
	 */
	public static Result warning() {
		return warning("操作不当警告");
	}
	
	public static Result warning(String msg) {
		Result r = new Result();
		r.put("code", 100);
		r.put("msg", msg);
		return r;
	}
	
	
	/**
	 * error
	 * 
	 * @return
	 */
	public static Result error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static Result error(String msg) {
		return error(500, msg);
	}
	
	public static Result error(int code, String msg) {
		Result r = new Result();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	
	/**
	 * 成功
	 * 
	 * @param msg
	 * @return
	 */
	public static Result ok(String msg) {
		Result r = new Result();
		r.put("msg", msg);
		return r;
	}
	
	/**
	 *
	 * 
	 * @param msg
	 * @return
	 */
	public static Result count(int count) {
		if(count>0) {
			return ok("执行成功");
		}
		return error("执行失败");

	}
	
	
	
	/**
	 * 成功
	 * 
	 * @param msg
	 * @return
	 */
	public static Result ok(String msg,JSONObject json) {
		Result r = new Result();
		r.put("msg", msg);
		r.put("data", json);
		return r;
	}
	
	public static Result ok(Map<String, Object> map) {
		Result r = new Result();
		r.putAll(map);
		return r;
	}
	public static Result ok(JSONObject json) {
		Result r = new Result();
		r.put("msg", json);
		return r;
	}
	public static Result oko(Object object ) {
		Result r = new Result();
		r.put("msg", object);
		return r;
	}
	
	public static Result ok() {
		return new Result();
	}

	@Override
	public Result put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}