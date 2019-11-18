package com.code.common.jwt.congifure;

import com.code.common.jwt.interceptor.TakenAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/18
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public TakenAuthInterceptor takenAuthInterceptor() {
        return new TakenAuthInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TakenAuthInterceptor())
                .addPathPatterns("/**");
    }

}
