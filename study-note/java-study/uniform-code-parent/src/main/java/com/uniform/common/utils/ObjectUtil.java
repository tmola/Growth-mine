package com.uniform.common.utils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/4 19:49
 */
public class ObjectUtil {
    public static boolean isNotEmpty(Object object) {
        boolean ret = false;
        if (object != null) {
            if (object instanceof String) {
                ret = ((String) object).length() > 0 ? true : false;
            } else if (object instanceof Map) {
                ret = ((Map) object).size() > 0 ? true : false;
            } else if (object instanceof List) {
                ret = !((List) object).isEmpty() ? true : false;
            }
        }
        return ret;
    }

    public static boolean isEmpty(Object object) {
        return !isNotEmpty(object);
    }
}
