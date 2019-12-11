package fish.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * List工具类
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/11
 */
public class ListUtil {

    /**
     * 转换List中的元素类型
     */
    public static <T,S> List<S> convertList(List<T> list, Class sClass) throws Exception {
        boolean flag  = false;
        List<S> newList = new ArrayList<>();
        if (null == list || list.isEmpty()) return newList;
        Field[] sFields = sClass.getDeclaredFields();
        Class<?> tClass = list.get(0).getClass();
        Field[] tFields = tClass.getDeclaredFields();
        for (T data : list) {
            Object object = sClass.newInstance();
            for (int i = 0; i < sFields.length; i++) {
                Field sField = sFields[i];
                for (int j = 0; j < tFields.length; j++) {
                    Field tField = tFields[j];
                    String sfName = sField.getName();
                    String tfName = tField.getName();
                    if (sfName.equals(tfName)) {
                        flag = true;
                        sField.setAccessible(true);
                        tField.setAccessible(true);
                        sField.set(object, tField.get(data));
                        break;
                    }
                }
            }
            newList.add((S)object);
        }
        if(flag)
            return newList;
        else throw new Exception("实体不存在相同的字段属性");
    }



}
