package components.response.config;


import components.response.exception.ResponseExceptionHandler;
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
@PropertySource(value = ResponseConfigure.propertySource, encoding = "UTF-8", ignoreResourceNotFound = true)
@EnableConfigurationProperties(ResponseProperties.class)
public class ResponseConfigure {

    public final static String propertySource = "classpath:response.properties";

    @Bean
    public ResponseExceptionHandler globalExceptionHandler() {
        return new ResponseExceptionHandler();
    }

    @Bean
    public ResponseAdvice globalResponseAdvice(ResponseProperties responseProperties) {
        return new ResponseAdvice(responseProperties);
    }


}
