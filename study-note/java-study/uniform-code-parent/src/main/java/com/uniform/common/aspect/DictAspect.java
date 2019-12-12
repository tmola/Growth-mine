package com.uniform.common.aspect;

import com.alibaba.fastjson.JSONObject;

import com.uniform.common.annotation.DictField;
import com.uniform.common.annotation.DictTextField;
import com.uniform.common.utils.ObjectUtil;
import com.uniform.common.utils.StringUtil;
import com.uniform.common.utils.ToolUtil;
import com.uniform.modules.system.services.SysDictService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/12
 */
@Slf4j
@Aspect
@Component
public class DictAspect {

    final static String DICT_TEXT_SUFFIX = "DictText";

    @Autowired
    private SysDictService dictService;

    @Pointcut("@annotation(com.uniform.common.annotation.DictResult)")
    public void doDictResultPoint() {
    }

    @Around("doDictResultPoint()")
    public Object aroundDictConvert(ProceedingJoinPoint point) throws Throwable {
        log.info("LogInfo:-----------------------------------");
        long t1 = System.currentTimeMillis();
        Object result = point.proceed();
        long t2 = System.currentTimeMillis();
        log.info("获取数据耗时：" + (t2 - t1) + " ms");
        this.parseDictText(result);
        long t3 = System.currentTimeMillis();
        log.info("字典转换耗时：" + (t3 - t2) + " ms");
        log.info("LogInfo:-----------------------------------");
        return result;
    }

    private void parseDictText(Object result) throws Exception {
        if (result != null) {
            Object retData = result;
            if (retData != null) {
                if (retData instanceof Map) {
                    Object listData = ((Map<String, Object>) retData).get("list");
                    if (listData != null && ((List) listData).size() > 0) {
                        List records = (List) listData;
                        Class clazz = records.get(0).getClass();
                        for (Object record : (List) listData) {
                            for (Field field : ObjectUtil.getAllFields(record)) {
                                if (field.getAnnotation(DictField.class) == null) continue;
                                Method method = clazz.getMethod("get" + ToolUtil.upperFirst(field.getName()));
                                String dict = field.getAnnotation(DictField.class).dict();
                                String code = String.valueOf(method.invoke(record));//转换字典代码
                                String dictText = dictService.getTextByCode(dict, code);
                                Method methodDict = clazz.getDeclaredMethod("set" + ToolUtil.upperFirst(field.getName()) + DICT_TEXT_SUFFIX, new Class[]{String.class});
                                methodDict.invoke(record, dictText);
                            }
                        }
                    }
                }
            }
        }
    }

    private void parseDictText_2(Object result) throws Exception {
        if (Objects.isNull(result)) return;
        Object retData = result;
        if (!(retData instanceof Map)) return;
        Object listData = ((Map<String, Object>) retData).get("list");
        if (Objects.isNull(listData)) return;
        if (!(listData instanceof List)) return;
        List records = (List) listData;
        if (records.isEmpty()) return;
        Class clazz = records.get(0).getClass();
        for (Object record : (List) listData) {
            for (Field field : ObjectUtil.getAllFields(record)) {
                if (field.getAnnotation(DictField.class) == null) continue;
                Method method = clazz.getMethod("get" + StringUtil.upperFirst(field.getName()));
                String dict = field.getAnnotation(DictField.class).dict();
                String code = String.valueOf(method.invoke(record));//转换字典代码
                String dictText = dictService.getTextByCode(dict, code);
                Method methodDict = clazz.getDeclaredMethod("set" + ToolUtil.upperFirst(field.getName()) + DICT_TEXT_SUFFIX, new Class[]{String.class});
                methodDict.invoke(record, dictText);
            }
        }
    }

}
