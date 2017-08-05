package com.xmomen.module.logger.aspect;

import com.alibaba.fastjson.JSONObject;
import com.xmomen.framework.logger.ActionLog;
import com.xmomen.module.core.model.AccountModel;
import com.xmomen.module.core.service.AccountService;
import com.xmomen.module.logger.LogModel;
import com.xmomen.module.logger.service.LoggerService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Jeng on 16/3/20.
 */
@Aspect
public class LoggerAspect {

    private static Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @Autowired
    HttpServletRequest request;

    @Autowired
    private LoggerService loggerService;

    @Autowired
    private AccountService accountService;


    /**
     * 日志逻辑切入点
     */
    @Pointcut("@annotation(com.xmomen.framework.logger.ActionLog)")
    public void getLogInfo() { }

    @Around(value = "getLogInfo()")
    public Object traceMethod(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object returnVal = null;
        String returnStr = null;
        String methodName = proceedingJoinPoint.getSignature().getName();
        final Object[] args = proceedingJoinPoint.getArgs();
        final String arguments;
        Object target = proceedingJoinPoint.getTarget();
        Method method = getMethodByClassAndName(target.getClass(), methodName);    //得到拦截的方法
        ActionLog an = (ActionLog)getAnnotationByMethod(method ,ActionLog.class );
        if(an != null){
            methodName = an.actionName();
        }
        if (args == null || args.length == 0) {
            arguments = "";
        } else {
            arguments = Arrays.deepToString(args);
        }
        String userId = getUserId();
        returnVal = proceedingJoinPoint.proceed();
        if(loggerService != null){
            if(returnVal != null){
                returnStr = returnVal.toString();
            }
            LogModel logModel = new LogModel();
            logModel.setActionDate(new Date());
            logModel.setTargetClass(target.getClass().getName());
            logModel.setTargetMethod(method.getName());
            logModel.setActionName(methodName);
            logModel.setUserId(userId);
            logModel.setClientIp(getRemoteHost(request));
            logModel.setActionParams(arguments);
            logModel.setActionResult(returnStr);
            logger.debug("User action record info -> {0}", JSONObject.toJSONString(logModel));
            loggerService.setLogInfo(logModel);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Entering method [{}] with arguments [{}]", methodName, arguments);
        }
        return returnVal;
    }


    public Annotation getAnnotationByMethod(Method method , Class annoClass){
        Annotation all[] = method.getAnnotations();
        for (Annotation annotation : all) {
            if (annotation.annotationType() == annoClass) {
                return annotation;
            }
        }
        return null;
    }

    public Method getMethodByClassAndName(Class c , String methodName){
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if(method.getName().equals(methodName)){
                return method ;
            }
        }
        return null;
    }

    /**
     * 获取用户ID
     * @return
     */
    public String getUserId(){
        AccountModel accountModel = accountService.getSessionModel();
        return accountModel.getUserId();
    }

    /**
     * 获用户客户端IP
     * @param request
     * @return
     */
    public String getRemoteHost(HttpServletRequest request){
        if(request == null){
            return null;
        }
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }

}
