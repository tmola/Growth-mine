package config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/12
 */

@Data
@ConfigurationProperties(value = ConfigProperties.PREFIX)
public class ConfigProperties {
    public final static String PREFIX = "config.author";
    private String name;
    private String email;
    private String phone;
}
