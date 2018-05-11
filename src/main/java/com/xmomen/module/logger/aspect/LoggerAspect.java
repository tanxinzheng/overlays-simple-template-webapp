package com.xmomen.module.logger.aspect;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.xmomen.framework.logger.ActionLog;
import com.xmomen.module.core.model.AccountModel;
import com.xmomen.module.core.service.AccountService;
import com.xmomen.module.logger.LogModel;
import com.xmomen.module.logger.service.LoggerService;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

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

    @Autowired
    ObjectMapper objectMapper;


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
            LogModel logModel = new LogModel();
            logModel.setActionDate(new Date());
            logModel.setTargetClass(target.getClass().getName());
            logModel.setTargetMethod(method.getName());
            logModel.setActionName(format(methodName, getAnnotationParamsByMethod(method, args)));
            logModel.setUserId(userId);
            logModel.setClientIp(getRemoteHost(request));
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

    public Map<String, Object> getAnnotationParamsByMethod(Method method , Object[] args){
        Map<String, Object> params = Maps.newHashMap();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] parameterAnnotation = parameterAnnotations[i];
            for (int j = 0; j < parameterAnnotation.length; j++) {
                Annotation annotation = parameterAnnotation[j];
                if(annotation instanceof RequestParam){
                    String val = ((RequestParam) annotation).value();
                    params.put(val, args[i]);
                }
                if(annotation instanceof RequestBody){
                    try {
                        String str = objectMapper.writeValueAsString(args[i]);
                        Map data = JSONObject.parseObject(str, Map.class);
                        params.put(args[i].getClass().getSimpleName(), data);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return params;
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
        AccountModel accountModel = accountService.getCurrentAccount();
        if(accountModel == null){
            return null;
        }
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

    /**
     * 格式化描述信息
     * @param string
     * @param data
     * @return
     */
    public String format(String string, Map<String, Object> data){
        Configuration configuration = new Configuration(new Version(2,3,23));
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate("ActionLogParamsTemplate", string);
        configuration.setTemplateLoader(stringTemplateLoader);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassicCompatible(true);
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        try {
            Template template = configuration.getTemplate("ActionLogParamsTemplate", "UTF-8");
            StringWriter stringWriter = new StringWriter();
            template.process(data, stringWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        } catch (TemplateException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return string;
    }

}
