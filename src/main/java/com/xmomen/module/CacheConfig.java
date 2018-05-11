package com.xmomen.module;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by tanxinzheng on 17/8/21.
 */
@Configuration
@EnableCaching
public class CacheConfig {

    public static final String FAILURE_LOGIN_MAX_NUMBER_KEY = "failure_login_max_number";

    @Bean
    public EhCacheManagerFactoryBean getEhCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        ehCacheManagerFactoryBean.setShared(true);
        return ehCacheManagerFactoryBean;
    }

    @Bean
    public EhCacheCacheManager getEhCacheCacheManager(){
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        ehCacheCacheManager.setCacheManager(getEhCacheManagerFactoryBean().getObject());
        return ehCacheCacheManager;
    }
}
