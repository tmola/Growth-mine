package com.hacking.util.result;

import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/8
 */
@Data
public class ServiceResult {
    public enum Type{
        INSERT(0, "新增"),
        UPDATE(1, "修改"),
        SAVE(2, "保存"),
        DELETE(3, "删除"),
        SELECT(4, "查询"),
        SELECT_PAGE(5, "分页查询"),;
        private int key;
        private String value;
        Type(int key, String value){
            this.key = key;
            this.value = value;
        }
    }

    public static Map  result(Type type,int total, int successed, int fieled){
        Map<String, Object> result = new HashMap();
        result.put("type", type.value);
        result.put("total", total);
        result.put("successed", successed);
        result.put("fieled", fieled);
        return result;
    }
    public static <T> Map result(List<T> list){
        Map<String, Object> result = new HashMap();
        result.put("type", Type.SELECT.value);
        result.put("list", list);
        result.put("totalSize", list.size());
        return result;
    }
    public static <T> Map result(List<T> list, Pageable pageable){
        Map<String, Object> result = new HashMap();
        result.put("type", Type.SELECT_PAGE.value);
        result.put("list", list);
        result.put("totalSize", list.size());
        result.put("page", pageable.getPageNumber());
        result.put("pagesize", pageable.getPageSize());
        return result;
    }
}
