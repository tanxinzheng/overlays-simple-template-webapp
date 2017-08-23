package com.xmomen.module;

import com.xmomen.module.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Created by tanxinzheng on 17/8/18.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtAuthenticationProvider getJwtAuthenticationProvider(){
        return new JwtAuthenticationProvider();
    }

    @Autowired
    Environment environment;

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                // 设置UserDetailsService
                .authenticationProvider(getJwtAuthenticationProvider())
                .userDetailsService(getUserDetailService());
    }
    // 装载BCrypt密码编码器

    @Bean
    public JwtUserDetailService getUserDetailService(){
        return new JwtUserDetailService();
    }


    @Bean
    @Primary
    public JwtTokenService getJwtTokenService(){
        return new JwtTokenServiceImpl();
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 设置你要允许的网站域名，如果全允许则设为 *
        config.addAllowedOrigin("*");
        // 如果要限制 HEADER 或 METHOD 请自行更改
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        // 这个顺序很重要哦，为避免麻烦请设置在最前
        bean.setOrder(0);
        return bean;
    }

    @Bean
    public JwtAuthenticationSuccessHandler getJwtAuthenticationSuccessHandler(){
        return new JwtAuthenticationSuccessHandler();
    }

    @Bean
    public JwtAuthenticationFilter getJwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManagerBean(), getJwtTokenService());
        jwtAuthenticationFilter.setPostOnly(false);
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(getJwtAuthenticationSuccessHandler());
        jwtAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login"));
        return jwtAuthenticationFilter;
    }

    @Bean
    public JwtAuthorizationFilter getJwtAuthorizationFilter(JwtTokenService jwtTokenService) throws Exception {
        return new JwtAuthorizationFilter(authenticationManagerBean(), jwtTokenService);
    }

    @Bean
    public RememberMeServices getRememberMeService(){
        TokenBasedRememberMeServices tokenBasedRememberMeServices = new TokenBasedRememberMeServices(environment.getProperty("security.remember-me.key"), getUserDetailService());
        tokenBasedRememberMeServices.setParameter("rememberMe");
        return tokenBasedRememberMeServices;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .headers()
                // 允许frame（支持iframe下载功能）
                .frameOptions().sameOrigin()
                .and()
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
//                .anonymous().disable()
                // 基于token，所以不需要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 对于获取token的rest api要允许匿名访问
                .antMatchers(
                        "/access/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/v2/**",
                        "/webjars/**",
                        "/**/*.js",
                        "/**/*.css",
                        "/**/*.png",
                        "/favicon.ico")
                .permitAll()
                .anyRequest().authenticated()
                // 除上面外的所有请求全部需要鉴权认证
                .and()
                .addFilterAt(getJwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(getJwtAuthorizationFilter(getJwtTokenService()), BasicAuthenticationFilter.class)
                .rememberMe()
                .rememberMeServices(getRememberMeService())
                .and()
                // 禁用缓存
                .logout().addLogoutHandler(new JwtLogoutHandler(getJwtTokenService()))
                .and()
                .headers().cacheControl();
    }

}
