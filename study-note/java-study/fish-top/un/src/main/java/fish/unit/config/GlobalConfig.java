package fish.unit.config;

import fish.unit.GlobalProperties;
import fish.unit.exception.handler.GlobalExceptionHandler;
import fish.unit.response.GlobalResponseAdvice;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/11
 */
@Configuration
@EnableConfigurationProperties(GlobalProperties.class)
@PropertySource(value = "classpath:dispose.properties", encoding = "UTF-8")
public class GlobalConfig {
    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Bean
    public GlobalResponseAdvice globalResponseAdvice(GlobalProperties globalProperties) {
        return new GlobalResponseAdvice(globalProperties);
    }


}
