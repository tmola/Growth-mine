package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Object工具类
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/11
 */
public class ObjectUtil {

    public static Object javaObject2Entity(Object javaObject, Class entityClass) {
        if (null == javaObject) return null;
        JSONObject jsonObject = (JSONObject) JSON.toJSON(javaObject);
        return JSONObject.parseObject(String.valueOf(jsonObject), entityClass);
    }
}
