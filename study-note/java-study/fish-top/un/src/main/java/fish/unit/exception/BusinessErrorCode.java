package fish.unit.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/11
 */
@Getter
public enum  BusinessErrorCode {
    /**
     * 通用业务异常
     */
    BUSINESS_ERROR(800,"业务异常"),

    /**
     * 404 Web 服务器找不到您所请求的文件或脚本。请检查URL 以确保路径正确。
     */
    NOT_FOUND(404,
            String.format("哎呀，无法找到这个资源啦(%s)", HttpStatus.NOT_FOUND.getReasonPhrase())),
    /**
     * 参数错误
     */
    PARAM_ERROR(100, "参数错误"),
    ;

    private Integer code;

    private String message;

    BusinessErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
