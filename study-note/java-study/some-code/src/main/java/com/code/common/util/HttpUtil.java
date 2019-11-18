package com.code.common.util;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/15
 */
public class HttpUtil {
    public static final String APP_FORCE_DOWNLOAD = "application/force-download";
    public static final String APP_OCTET_STREAM = "application/octet-stream";

    public static final String CONTENT_TYPE = "content-Type";
    public static final String CONTENT_DISCRIPTION = "content-Disposition";

    public static final String ATTACHMENT_FILENAME_EQ = "attachment;fileName=";
    public static final String INLINE_FILENAME_EQ = "inline;fileName=";

    public static final String ENCODING_UNICODE = "unicode";
    public static final String ENCODING_UTF_8 = "utf-8";
    public static final String ENCODING_GBK = "gbk";

    /**
     * 获取ServletRequestAttributes对象
     */
    public static ServletRequestAttributes getServletRequest(){
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    /**
     * 获取HttpServletRequest对象
     */
    public static HttpServletRequest getRequest(){
        return getServletRequest().getRequest();
    }

    /**
     * 获取HttpServletResponse对象
     */
    public static HttpServletResponse getResponse(){
        return getServletRequest().getResponse();
    }

    /**
     * 获取请求参数
     */
    public static String getParameter(String param){
        return getRequest().getParameter(param);
    }

    /**
     * 获取请求参数，带默认值
     */
    public static String getParameter(String param, String defaultValue){
        String parameter = getRequest().getParameter(param);
        return StringUtils.isEmpty(parameter) ? defaultValue : parameter;
    }

    /**
     * 获取请求参数转换为int类型
     */
    public static Integer getParameterInt(String param){
        return Integer.valueOf(getRequest().getParameter(param));
    }

    /**
     * 获取请求参数转换为int类型，带默认值
     */
    public static Integer getParameterInt(String param, Integer defaultValue){
        return Integer.valueOf(getParameter(param, String.valueOf(defaultValue)));
    }

}
