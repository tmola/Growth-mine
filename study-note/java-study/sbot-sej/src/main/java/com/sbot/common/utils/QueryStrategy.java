package com.sbot.common.utils;

import com.sbot.common.vo.QueryVO;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
 * @date 2019/11/26
 */
public class QueryStrategy {

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

    private String[] ignoredFieldsDefault = {"createTime", "createUser", "modifyTime", "modifyTser", "delFlag"};

    private static final String DEL_FLAG = "delFlag";

    private String[] ignoredFields;

    private Map<String, List<Object>> inValues;

    private Map<String, Long[]> betweenValues;

    private Map<String, Long> fieldRules;

    public QueryStrategy() {
        inValues = new HashMap<>();
        betweenValues = new HashMap<>();
        fieldRules = new HashMap<>();
    }

    /**
     * 添加多条相同的匹配规则
     *
     * @param regulation 查询规则
     * @param fields     需要验证的字段名称
     */
    public QueryStrategy withMatcher(Long regulation, String... fields) {
        List<String> fieldList = Arrays.asList(fields);
        for (String field : fieldList) {
            fieldRules.put(field, regulation);
        }
        return this;
    }

    /**
     * 添加一条IN匹配规则
     *
     * @param field       需要验证的字段名称
     * @param inValueList IN数据列表
     */
    public QueryStrategy withMatcherIn(String field, List<Object> inValueList) {
        fieldRules.put(field, IN);
        inValues.put(field, inValueList);
        return this;
    }

    /**
     * 添加一条BETWEEN匹配规则
     *
     * @param field 需要验证的字段名称
     * @param x     第一个值
     * @param y     第二个值
     */
    public QueryStrategy withMatcherBetween(String field, Long x, Long y) {
        fieldRules.put(field, BETWEEN);
        betweenValues.put(field, new Long[]{x, y});
        return this;
    }

    public void setIgnoredFields(String... fields) {
        ignoredFields = fields;
    }

    public void setIgnoredFieldsDefault() {
        ignoredFields = ignoredFieldsDefault;
    }

    public List<String> getIgnoredFields() {
        return Arrays.asList(ignoredFields);
    }

    public List<String> getDefaultIgnoredFields() {
        return Arrays.asList(ignoredFieldsDefault);
    }

    //存在的问题：对于存放字典代码的字段无法成功匹配
    // 再查询前将条件中的码表数据转换？？
    public static <T> Specification<T> ofAllLikeMatch(QueryVO searchVo, QueryStrategy strategy) {
        List<String> ignoredFields = new ArrayList<>();
        ignoredFields.addAll(strategy.getIgnoredFields());
        ignoredFields.addAll(strategy.getDefaultIgnoredFields());
        Specification<T> specification = (Specification<T>) (root, query, cb) -> {
            List<Predicate> preList = new ArrayList<>();
            try {
                Object prod = searchVo.getTerms();
                final BeanInfo beanInfo = Introspector.getBeanInfo(prod.getClass());
                for (final PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
                    final Object value = pd.getReadMethod().invoke(prod, (Object[]) null);
                    if (!(value instanceof Class) && !ignoredFields.contains(pd.getName())) {
                        if (searchVo.isObject()) {
                            if (value != null && !value.equals("")) {
                                preList.add(cb.like(root.get(pd.getName()).as(String.class), String.valueOf(value)));
                            }
                        } else {
                            if (searchVo.getSearchWord() != null && !searchVo.getSearchWord().equals("")) {
                                preList.add(cb.like(root.get(pd.getName()).as(String.class), searchVo.getSearchWord()));
                            }
                        }
                    }
                }
                preList.add(cb.equal(root.get(DEL_FLAG).as(String.class), String.valueOf(0)));
            } catch (IntrospectionException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            Predicate[] pres = new Predicate[preList.size()];
            if (!searchVo.isObject() && preList.size() > 0)
                return query.where(cb.or(preList.toArray(pres))).getRestriction();
            return query.where(preList.toArray(pres)).getRestriction();
        };
        return specification;
    }
}
