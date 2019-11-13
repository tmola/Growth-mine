package com.code.common.fileUpload;

import com.code.common.util.ToolUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/13
 */
@Data
@Component
@ConfigurationProperties(prefix = "project.upload")
public class FileUploadProperties {
    private String filePath;

    private String staticPath = "/upload/**";

    /** 获取文件路径 */
    public String getFilePath() {
        if(filePath == null){
            return ToolUtil.getProjectPath() + "/upload/";
        }
        return filePath;
    }
}
