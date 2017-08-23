package com.xmomen.module;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Sets;
import com.xmomen.framework.web.json.DictionaryAnnotationIntrospector;
import com.xmomen.framework.web.support.DateConverter;
import com.xmomen.module.logger.aspect.LoggerAspect;
import org.jeecgframework.poi.excel.view.JeecgMapExcelView;
import org.jeecgframework.poi.excel.view.JeecgSingleExcelView;
import org.jeecgframework.poi.excel.view.JeecgTemplateExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Set;

/**
 * Created by tanxinzheng on 17/6/24.
 */
@ComponentScan(value = "com.**,org.jeecgframework.poi.excel.view")
@EnableAutoConfiguration(exclude={MultipartAutoConfiguration.class})
@EnableCaching
public class ApplicationStart extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }

    @Autowired
    private Environment env;

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public DictionaryAnnotationIntrospector getDictionaryIntrospector(){
        DictionaryAnnotationIntrospector dictionaryAnnotationIntrospector = new DictionaryAnnotationIntrospector();
        dictionaryAnnotationIntrospector.setApplicationContext(applicationContext);
        return dictionaryAnnotationIntrospector;
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
        builder.timeZone("GMT+8");
        builder.simpleDateFormat("yyyy-MM-dd HH:mm:ss");
        builder.featuresToDisable(
                SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS,
                DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES
//                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
        );
        builder.annotationIntrospector(getDictionaryIntrospector());
        builder.featuresToEnable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        return builder.build();
    }

    @Bean
    public JeecgTemplateExcelView getJeecgTemplateExcelView(){
        return new JeecgTemplateExcelView();
    }
    @Bean
    public JeecgSingleExcelView getJeecgSingleExcelView(){
        return new JeecgSingleExcelView();
    }
    @Bean
    public JeecgMapExcelView getJeecgMapExcelView(){
        return new JeecgMapExcelView();
    }
    @Bean
    public BeanNameViewResolver getBeanNameViewResolver(){
        BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
        beanNameViewResolver.setOrder(0);
        return beanNameViewResolver;
    }

    @Bean
    public LoggerAspect getLoggerAspect(){
        return new LoggerAspect();
    }

    @Bean
    public ConversionService getConversionService() {
        Set set = Sets.newHashSet();
        set.add(new DateConverter());
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        bean.setConverters(set);
        return bean.getObject();
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .defaultContentType(MediaType.APPLICATION_JSON_UTF8)
                .parameterName("format")
                .favorParameter(true)
                .ignoreUnknownPathExtensions(false)
                .ignoreAcceptHeader(false)
                .useJaf(true);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.jsp("/WEB-INF/views/", ".jsp");
        registry.enableContentNegotiation(false, new MappingJackson2JsonView());
        registry.enableContentNegotiation(
                new MappingJackson2JsonView()
//                new XlsxView(),
//                new XlsxStreamingView()
         );
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSizePerFile(Long.valueOf(env.getProperty("spring.servlet.multipart.max-file-size")));
        return resolver;
    }

    @Bean
    @Order(0)
    public MultipartFilter multipartFilter() {
        MultipartFilter multipartFilter = new MultipartFilter();
        multipartFilter.setMultipartResolverBeanName("multipartResolver");
        return multipartFilter;
    }
}
