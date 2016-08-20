package com.xmomen.module.logger.aspect;

import com.xmomen.module.logger.Log;
import com.xmomen.module.logger.service.LoggerService;
import org.apache.shiro.SecurityUtils;
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


    /**
     * 日志逻辑切入点
     */
    @Pointcut("@annotation(com.xmomen.module.logger.Log)")
    public void getLogInfo() { }

//    /**
//     * 管理员添加操作日志(后置通知)
//     * @param joinPoint
//     * @throws Throwable
//     */
//    @AfterReturning(value = "getLogInfo()")
//    public void afterReturning(JoinPoint joinPoint) throws Throwable{
//        //判断参数
//        if(joinPoint.getArgs() == null){//没有参数
//            return;
//        }
//        final Object[] args = joinPoint.getArgs();
//        final String arguments;
//        if (args == null || args.length == 0) {
//            arguments = "";
//        } else {
//            arguments = Arrays.deepToString(args);
//        }
//        //获取方法名
//        String methodName = joinPoint.getSignature().getName();
//        Object target = joinPoint.getTarget();
//        Method method = getMethodByClassAndName(target.getClass(), methodName);    //得到拦截的方法
//        Object[] args2 = joinPoint.getArgs();     //方法的参数
//        Log an = (Log)getAnnotationByMethod(method ,Log.class );
//        if(an == null){
//            return;
//        }
//        String actionName = an.actionName();
//        Parameter[] parameters = method.getParameters();
//        String userId = getUserId();
//        logger.debug("User action record info -> { UserId: {0} , ClientId: {1} , actionName: {2}, actionParameters: {3}, actionResult: {4} ");
//        if(loggerService == null){
//            return;
//        }
//        loggerService.setLogInfo(actionName, userId, getRemoteHost(request), arguments, null);
//    }

    @Around(value = "getLogInfo()")
    public Object traceMethod(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object returnVal = null;
        String returnStr = null;
        String methodName = proceedingJoinPoint.getSignature().getName();
        try {
            final Object[] args = proceedingJoinPoint.getArgs();
            final String arguments;
            Object target = proceedingJoinPoint.getTarget();
            Method method = getMethodByClassAndName(target.getClass(), methodName);    //得到拦截的方法
            Log an = (Log)getAnnotationByMethod(method ,Log.class );
            if(an != null){
                methodName = an.actionName();
            }
            if (args == null || args.length == 0) {
                arguments = "";
            } else {
                arguments = Arrays.deepToString(args);
            }
            Integer userId = getUserId();
            returnVal = proceedingJoinPoint.proceed();
            logger.debug("User action record info -> { UserId: {0} , ClientId: {1} , actionName: {2}, actionParameters: {3}, actionResult: {4} ");
            if(loggerService != null){
                if(returnVal != null){
                    returnStr = returnVal.toString();
                }
                loggerService.setLogInfo(methodName, userId, getRemoteHost(request), arguments, returnStr);
            }
            if (logger.isDebugEnabled()) {
                logger.debug("Entering method [{}] with arguments [{}]", methodName, arguments);
            }
            return returnVal;
        } finally {
            logger.debug("Leaving method [{}] with return value [{}].", methodName, returnStr);
        }
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
    public Integer getUserId(){
        Integer user_id =(Integer) SecurityUtils.getSubject().getSession().getAttribute("user_id");
        return user_id;
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
