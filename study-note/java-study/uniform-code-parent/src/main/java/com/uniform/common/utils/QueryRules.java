package com.uniform.common.utils;

import com.uniform.common.vo.QueryVO;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.jpa.domain.Specification;

import javax.validation.constraints.NotNull;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/5
 */
public class QueryRules {
    /*非获取的字段*/
    List<String> ignoreFields;
    /*in 规则字段与Values映射*/
    Map<String, List> fieldInValuesMap;
    /*between 规则字段与Values映射*/
    Map<String, Long[]> fieldBetwenValuesMap;
    /*字段的规则映射*/
    Map<String, Long> fieldRuleMap;

    public QueryRules() {
        ignoreFields = new ArrayList<>();
        fieldInValuesMap = new LinkedHashMap<>();
        fieldBetwenValuesMap = new LinkedHashMap<>();
        fieldRuleMap = new LinkedHashMap<>();
    }

    public void addIgnoreFields(@NotNull String... ignoreFields) {
        if (Objects.isNull(ignoreFields)) return;
        List<String> ignoreFieldList = Arrays.asList(ignoreFields);
        this.ignoreFields.addAll(ignoreFieldList);
    }

    public List<String> getIgnoreFields() {
        return ignoreFields;
    }

    public void addInRuleField(@NotNull String field, @NotNull List values) {
        fieldInValuesMap.put(field, values);
        fieldRuleMap.put(field, IN);
    }

    public Map<String, List> getInRuleFields() {
        return fieldInValuesMap;
    }

    public List getInRuleValues(String field) {
        return fieldInValuesMap.get(field);
    }

    public static <T> Specification<T> of(T entity, QueryRules queryRules) {
        List IgnoreFields = queryRules.getIgnoreFields();
        Specification<T> specification = (Specification<T>) (root, query, cb) -> {
            /*反射与内省*/
            try {
                //获取entity对象的BeanInfo信息
                //如果不想把父类的属性也列出来的话，那getBeanInfo的第二个参数填写父类的信息
                BeanInfo beanInfo = Introspector.getBeanInfo(entity.getClass());

                //根据BeanInfo信息获取类的属性描述器
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

                //通过这个属性描述器获取某个属性对应的getter/setter方法
                for (final PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    final Object value = propertyDescriptor.getReadMethod().invoke(entity, (Object) null);
                    if(value instanceof Class) continue;
                    //跳过对值为空、为null的字段的处理
                    if (null == value || (value instanceof String && value.equals(""))) continue;
                    //跳过对忽略字段的处理
                    if ( IgnoreFields.contains(propertyDescriptor.getName())) continue;
                    //跳过保存字典翻译字段的处理
                    if ( IgnoreFields.contains(propertyDescriptor.getName())) continue;

                }
            } catch (IntrospectionException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            return null;
        };
        return null;
    }


    /**
     * 查询规则
     */
    /* 精确查询(=) */
    public static final Long EQUAL = 0L;
    /* 模糊查询(*XX*) */
    public static final Long LIKE = 1L;
    /* 左模糊查询(*XX) */
    public static final Long LEFT_LIKE = 2L;
    /* 右模糊查询(XX*) */
    public static final Long RIGHT_LIKE = 3L;
    /* 不等于(!=) */
    public static final Long NOT_EQUAL = 4L;
    /* 大于(>) */
    public static final Long GT = 5L;
    /* 大于等于(>=) */
    public static final Long GE = 6L;
    /* 小于(<) */
    public static final Long LT = 7L;
    /* 小于等于(<=) */
    public static final Long LE = 8L;
    /* 多值(in) */
    public static final Long IN = 9L;
    /* 区间查询(between) */
    public static final Long BETWEEN = 10L;
}
