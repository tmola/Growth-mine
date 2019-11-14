package com.code.common.util;

import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Field;
import java.util.*;

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

    public static String randomID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static Field[] getAllField(Object o) {
        Class<?> clazz = o.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }


}
