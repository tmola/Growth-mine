package com.sbot.common.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据库操作结果信息
 * </p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/17 2:57
 */
public class OptRetMapUtil {

    public static <T> Map saveOptResult(int total, int successed, int fieled, List<T> fieldList) {
        Map<String, Object> result = new HashMap();
        result.put("type", Type.SAVE.value);
        result.put("total", total);
        result.put("successed", successed);
        result.put("fieled", fieled);
        if (fieldList != null && !fieldList.isEmpty())
            result.put("fieledList", fieldList);
        return result;
    }

    public static <T> Map deleteOptResult(int total, int successed, int fieled, List<T> fieldList) {
        Map<String, Object> result = new HashMap();
        result.put("type", Type.DELETE.value);
        result.put("total", total);
        result.put("successed", successed);
        result.put("fieled", fieled);
        if (fieldList != null && !fieldList.isEmpty())
            result.put("fieledList", fieldList);
        return result;
    }

    public static <T> Map selectOptResult(List<T> list) {
        Map<String, Object> result = new HashMap();
        result.put("type", Type.SELECT.value);
        result.put("list", list);
        result.put("totalSize", list.size());
        return result;
    }

    public static <T> Map selectOptResult(List<T> list, Pageable pageable) {
        Map<String, Object> result = new HashMap();
        result.put("type", Type.SELECT_PAGE.value);
        result.put("list", list);
        result.put("totalSize", list.size());
        result.put("page", pageable.getPageNumber());
        result.put("pagesize", pageable.getPageSize());
        return result;
    }

    public static Map selectOptResult(Page page) {
        Map<String, Object> result = new HashMap();
        result.put("type", Type.SELECT_PAGE.value);
        result.put("list", page.getContent());
        result.put("totalPages", page.getTotalPages());
        result.put("totalElements", page.getTotalElements());
        result.put("number", page.getNumber());
        result.put("pageable", page.getPageable());
        return result;
    }

    public static <T> Map selectOptResult(T t) {
        Map<String, Object> result = new HashMap();
        result.put("type", Type.SELECT_PAGE.value);
        result.put("data", t);
        return result;
    }

    public static Map optError() {
        Map<String, Object> result = new HashMap();
        result.put("type", Type.ERROR.value);
        return result;
    }

    public static Map optError(String err) {
        Map<String, Object> result = new HashMap();
        result.put("type", Type.ERROR.value);
        result.put("message", err);
        return result;
    }

    public enum Type {
        INSERT(0, "新增"),
        UPDATE(1, "修改"),
        SAVE(2, "保存"),
        DELETE(3, "删除"),
        SELECT(4, "查询"),
        SELECT_PAGE(5, "分页查询"),
        ERROR(6, "操作出错"),
        ILLEGAL(6, "非法操作，请求数据无效");
        private int key;
        private String value;

        Type(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

}
