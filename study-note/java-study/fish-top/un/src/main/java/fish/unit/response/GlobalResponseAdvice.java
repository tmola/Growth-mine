package fish.unit.response;

import com.alibaba.fastjson.JSON;
import fish.unit.GlobalProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/11
 */
@RestControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {

    private GlobalProperties globalProperties;

    public GlobalResponseAdvice(GlobalProperties globalProperties) {
        this.globalProperties = globalProperties;
    }
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return filter(methodParameter);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o == null) {
            return ResponseVO.success();
        }

        if (o instanceof String) {
            return JSON.toJSON(ResponseVO.success(o)).toString();
        }

        if (o instanceof ResponseVO) {
            return o;
        }

        return ResponseVO.success(o);
    }

    private Boolean filter(MethodParameter methodParameter) {
        Class<?> declaringClass = methodParameter.getDeclaringClass();
        // 检查过滤包路径
        long count = globalProperties.getAdviceFilterPackage().stream()
                .filter(l -> declaringClass.getName().contains(l)).count();
        if (count > 0) {
            return false;
        }
        // 检查<类>过滤列表
        if (globalProperties.getAdviceFilterClass().contains(declaringClass.getName())) {
            return false;
        }
        // TODO 检查注解是否存在
        return true;
    }
}
