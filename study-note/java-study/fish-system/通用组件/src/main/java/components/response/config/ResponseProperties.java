package components.response.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;


@Data
@ConfigurationProperties(ResponseProperties.PREFIX)
public class ResponseProperties {
    public static final String PREFIX = "response";

    /**
     * 统一返回过滤包
     */
    private List<String> filterPackage = new ArrayList<>();

    /**
     * 统一返回过滤类
     */
    private List<String> filterClass = new ArrayList<>();
}
