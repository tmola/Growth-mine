package com.sbot.common.aspect;

import com.alibaba.fastjson.JSONObject;
import com.sbot.common.annotation.TranDict;
import com.sbot.common.utils.ToolUtil;
import com.sbot.modules.system.services.SysDictService;
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

    @Pointcut("@annotation(com.sbot.common.annotation.DictResult)")
    public void doDictResultPoint() {
    }

    @Around("doDictResultPoint()")
    public Object aroundDictConvert(ProceedingJoinPoint point) throws Throwable {
        log.debug("LogInfo:-----------------------------------");
        long t1 = System.currentTimeMillis();

        Object result = point.proceed();

        long t2 = System.currentTimeMillis();
        log.debug("获取数据耗时：" + (t2 - t1) + " ms");

        this.parseDictText(result);

        long t3 = System.currentTimeMillis();
        log.debug("字典转换耗时：" + (t3 - t2) + " ms");
        log.debug("LogInfo:-----------------------------------");
        return result;
    }

    private void parseDictText(Object result) throws Exception {
        if (result != null) {

            List<JSONObject> items = new ArrayList<>();
            Object retData = result;
            if (retData != null) {
                if (retData instanceof Map) {
                    Object listData = ((Map<String, Object>) retData).get("list");
                    if (listData != null && ((List) listData).size() > 0) {
                        List records = (List) listData;
                        Class clazz = records.get(0).getClass();
                        Field[] fields = clazz.getDeclaredFields();
                        Method[] methods = clazz.getDeclaredMethods();
                        for (Object record : (List) listData) {
                            for (Field field : ToolUtil.getAllField(record)) {
                                if (field.getAnnotation(TranDict.class) != null) {
                                    Method method = clazz.getMethod("get" + ToolUtil.upperFirst(field.getName()));
                                    String dict = field.getAnnotation(TranDict.class).dict();
                                    String code = String.valueOf(method.invoke(record));//转换字典代码
                                    String dictText = dictService.getTextByCode(dict, code);
                                    Method methodDict = clazz.getDeclaredMethod("set" + ToolUtil.upperFirst(field.getName()) + DICT_TEXT_SUFFIX, new Class[]{String.class});
                                    methodDict.invoke(record, dictText);
                                }
                            }

//                            ObjectMapper mapper = new ObjectMapper();
//                            String json = "{}";
//                            try {
//                                json = mapper.writeValueAsString(record);
//                            } catch (JsonProcessingException e) {
//                                log.error("Json解析失败：" + e.getMessage(), e);
//                            }
//                            JSONObject item = JSON.parseObject(json);
//                            for (Field field : ObjectUtil.getAllField(record)) {
//                                if (field.getAnnotation(TranDict.class) != null) {
//
//                                    String dict = field.getAnnotation(TranDict.class).dict();
//                                    String code = String.valueOf(item.get(field.getName()));
//                                    //转换字典代码
//                                    String dictText = dictService.getTextByCode(dict, code);
//                                    item.put(field.getName() + DICT_TEXT_SUFFIX, dictText);
//                                }
//                            }//for-field
//                            ;
//                            items.add(item);
                        }//for-record
//                        ((Map) result).put("list", items);
                    }//if-listDta not null
                }// if-Map or Page
            }//if-retData not null
        }
    }

}
