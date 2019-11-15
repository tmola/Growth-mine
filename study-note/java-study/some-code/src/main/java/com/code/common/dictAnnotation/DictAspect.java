package com.code.common.dictAnnotation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.code.common.util.ObjectUtil;
import com.code.common.util.result.Result;
import com.code.modules.dict.entity.Dict;
import com.code.modules.dict.service.DictService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
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

    final static String DICT_TEXT_SUFFIX = "_dictText";

    @Autowired
    private DictService dictService;

    @Pointcut("@annotation(DictResult)")
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
            if (result instanceof Result) {
                List<JSONObject> items = new ArrayList<>();
                Object ob = ((Result) result).getData();
                if (ob != null) {
                    if (ob instanceof Map) {
                        Object listOb = ((Map<String, Object>) ob).get("list");
                        if (listOb != null) {
                            for (Object record : (List) listOb) {
                                ObjectMapper mapper = new ObjectMapper();
                                String json = "{}";
                                try {
                                    json = mapper.writeValueAsString(record);
                                } catch (JsonProcessingException e) {
                                    log.error("Json解析失败：" + e.getMessage(), e);
                                }
                                JSONObject item = JSON.parseObject(json);
                                for (Field field : ObjectUtil.getAllField(record)) {
                                    if (field.getAnnotation(TranDict.class) != null) {
                                        String dict = field.getAnnotation(TranDict.class).dict();
                                        String code = String.valueOf(item.get(field.getName()));
                                        //转换字典代码
                                        String dictText = dictService.getTextByCode(dict, code);
                                        item.put(field.getName() + DICT_TEXT_SUFFIX, dictText);
                                    }
                                }
                                items.add(item);
                            }
                            ((Result) result).setData(items);
                        }
                    }

                }
            }
        }
    }

//    private String translateDictValue(String code, String key) {
//        return dictService.getTextByCode(code);
//    }

}
