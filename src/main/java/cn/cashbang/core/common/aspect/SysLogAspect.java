package cn.cashbang.core.common.aspect;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;

import cn.cashbang.core.common.utils.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.annotation.SysLog;
import cn.cashbang.core.modules.sys.entity.SysLogEntity;
import cn.cashbang.core.modules.sys.entity.SysUserEntity;
import cn.cashbang.core.modules.sys.manager.SysLogManager;


/**
 * 系统日志，切面处理类
 * 
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月28日 上午11:07:35
 */
@Aspect
@Component
public class SysLogAspect {
	
	@Autowired
	private SysLogManager sysLogManager;
	
	@Pointcut("@annotation(cn.cashbang.core.common.annotation.SysLog)")
	public void logPointCut() { 
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		//保存日志
		saveSysLog(point, time);
		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		SysLogEntity sysLog = new SysLogEntity();
		SysLog syslog = method.getAnnotation(SysLog.class);
		if(syslog != null){
			//注解上的描述
			sysLog.setOperation(syslog.value());
		}
		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");
		//请求的参数
		Object[] args = joinPoint.getArgs();
		try{
			if(args[0]!=null) {
				String params = JSONUtils.beanToJson(args[0]);
				sysLog.setParams(params);
			}
		}catch (Exception e){

		}
		//获取request
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		//设置IP地址
		sysLog.setIp(IpUtils.getIpAddr(request));
		//用户名
		SysUserEntity currUser = ShiroUtils.getUserEntity();
		if(CommonUtils.isNullOrEmpty(currUser)) {
			if(!CommonUtils.isNullOrEmpty(sysLog.getParams())) {
				sysLog.setUserId(-1L);
				sysLog.setUsername(sysLog.getParams());
			} else {
				sysLog.setUserId(-1L);
				sysLog.setUsername("获取用户信息为空");
			}
		} else {
			sysLog.setUserId(ShiroUtils.getUserId());
			sysLog.setUsername(ShiroUtils.getUserEntity().getUsername());
		}
		sysLog.setTime(time);
		//保存系统日志
		sysLogManager.saveLog(sysLog);
	}
	
}
