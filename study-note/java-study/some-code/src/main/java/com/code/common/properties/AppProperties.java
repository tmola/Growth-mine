package com.code.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/18
 */
@Data
@Component
@Validated
    @ConfigurationProperties(prefix = "app.const", ignoreInvalidFields = true)
public class AppProperties {
    @NotNull
    private String version = "2.0";
    private String information;

}
