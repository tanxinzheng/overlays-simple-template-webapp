package com.xmomen.framework.web.rest;

import com.alibaba.fastjson.JSONObject;
import com.xmomen.framework.exception.BusinessException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * Created by Jeng on 2016/1/11.
 */
public class RestExceptionResolver extends SimpleMappingExceptionResolver {

    @Value("#{systemProperties['spring.maxUploadSize']}")
    private static Long maxUploadSize;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response, Object handler, Exception ex) {
        if (!WebCommonUtils.isJSON(request)) {// 不是ajax请求
            return super.doResolveException(request, response, handler, ex);
        }
        ModelAndView mv = new ModelAndView();
        //设置状态码
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        response.setStatus(status);
        //设置ContentType
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        //避免乱码
        response.setCharacterEncoding("UTF-8");
        RestError restError = new RestError(ex, request);
        restError.setStatus(status);
        if(ex instanceof IllegalArgumentException || ex instanceof BusinessException){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            restError.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        if(ex instanceof UnauthenticatedException){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            restError.setStatus(HttpStatus.UNAUTHORIZED.value());
            restError.setMessage("Requires authentication");
        }
        if(ex instanceof DuplicateKeyException){
            response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
            restError.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
            restError.setMessage("保存失败，存在重复关键字段");
        }
        if(ex instanceof MaxUploadSizeExceededException){
            response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
            restError.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
            restError.setMessage(MessageFormat.format("文件上传限制最大不能超过{0}M" , (maxUploadSize/1024)/1024));
        }
        if(ex instanceof UnauthorizedException){
            response.setStatus(HttpStatus.FORBIDDEN.value());
            restError.setStatus(HttpStatus.FORBIDDEN.value());
            restError.setMessage("权限不足");
        }
        try {
            response.getWriter().write(JSONObject.toJSONString(restError));
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.error(ex.getMessage(), ex);
        return mv;
    }
}
