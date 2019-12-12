package config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/12
 */
@Configuration
@EnableConfigurationProperties(ConfigProperties.class)
@PropertySource("classpath:config.properties")
public class ConfigConfigure {
    @Bean
    public Author Author(ConfigProperties configProperties){
        return new Author(configProperties);
    }
}
