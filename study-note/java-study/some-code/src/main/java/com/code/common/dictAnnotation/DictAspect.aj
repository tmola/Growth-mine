package com.code.common.dictAnnotation;

import com.code.common.util.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 *
 * <p>
 * @author jintingying
 * @date 2019/11/12
 * @version 1.0
 */
@Slf4j
@Aspect
@Component
public aspect DictAspect {

    @Pointcut("@annotation(DictResult)")
    public void doDictResultPoint() {
    }


    @Around("doDictResultPoint()")
    public Object aroundDictConvert(ProceedingJoinPoint point) throws Throwable {
        log.debug("LogInfo:-----------------------------------");
        long t1 = System.currentTimeMillis();

        Object result = point.proceed();

        long t2 = System.currentTimeMillis();
        log.debug("获取数据耗时：" + (t2-t1) + " ms");

        this.dictConvert(result);

        long t3 = System.currentTimeMillis();
        log.debug("字典转换耗时：" + (t3-t2) + " ms");
        log.debug("LogInfo:-----------------------------------");
        return result;
    }

    private void dictConvert(Object result){
        if(result != null){
            if(result instanceof Result){
                //TODO 
            }
        }

    }

}
