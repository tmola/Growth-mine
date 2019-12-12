package com.uniform.common.utils;

import java.lang.reflect.Method;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/5
 */
public class ClassUtil {

    Method getGetMethod(Class clazz, String fieldName) throws NoSuchMethodException {
        return clazz.getDeclaredMethod("get" + StringUtil.upperFirst(fieldName));
    }

}
