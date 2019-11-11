package com.code.common.util;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/11
 */
public class ObjectUtil {

    public static boolean notNull(Object object) {
        return object != null;
    }

    public static boolean isNull(Object object) {
        return !notNull(object);
    }

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
