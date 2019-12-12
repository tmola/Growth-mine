package fish.unit;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/11
 */
@Data
@ConfigurationProperties(GlobalProperties.PREFIX)
public class GlobalProperties {
    public static final String PREFIX = "dispose";

    /**
     * 统一返回过滤包
     */
    private List<String> adviceFilterPackage = new ArrayList<>();

    /**
     * 统一返回过滤类
     */
    private List<String> adviceFilterClass = new ArrayList<>();
}
