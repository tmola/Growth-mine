package components.response.config;


import com.alibaba.fastjson.JSON;
import components.response.vo.ResponseVO;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    private ResponseProperties responseProperties;

    public ResponseAdvice(ResponseProperties responseProperties) {
        this.responseProperties = responseProperties;
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

        if (o instanceof ResponseVO) {
            return o;
        }

        // 若不对String特殊处理 会抛异常：[java.lang.ClassCastException: com.alibaba.fastjson.JSONObject cannot be cast to java.lang.String]
        if (o instanceof String) {
            return JSON.toJSON(ResponseVO.success(o)).toString();
        }

        return JSON.toJSON(ResponseVO.success(o));
    }

    private Boolean filter(MethodParameter methodParameter) {
        Class<?> declaringClass = methodParameter.getDeclaringClass();
        // 过滤包路径
        long count = responseProperties.getFilterPackage().stream()
                .filter(l -> declaringClass.getName().contains(l)).count();
        if (count > 0) {
            return false;
        }
        // 过滤类列表
        if (responseProperties.getFilterClass().contains(declaringClass.getName())) {
            return false;
        }
        // 过滤使用注解的方法
        if (methodParameter.hasMethodAnnotation(IgnoreUnifiedResponse.class)) {
            return false;
        }
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreUnifiedResponse.class)) {
            return false;
        }
        // 过滤使用注解的类
        if (declaringClass.getAnnotation(IgnoreUnifiedResponse.class)!= null) {
            return false;
        }
        // 过滤使用注解的包
        if (declaringClass.getPackage().getAnnotation(IgnoreUnifiedResponse.class)!= null) {
            return false;
        }

        return true;
    }
}
