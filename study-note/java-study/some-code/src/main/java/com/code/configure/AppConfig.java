package com.code.configure;

import com.code.common.interceptor.MyAsyncInterceptor;
import com.code.common.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/12
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**") /*添加拦截路径*/
                .excludePathPatterns("/test/**")/*排除拦截路径*/
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");/*排除Swagger*/

        registry.addInterceptor(new MyAsyncInterceptor())
                .addPathPatterns("/test/asyncRun");
    }
}
