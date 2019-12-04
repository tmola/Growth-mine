package com.uniform.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/12
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DictField {
    String dict(); //对应的catalog值
}
