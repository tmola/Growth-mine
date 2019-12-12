package com.uniform.common.annotation;

import javax.persistence.Transient;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/5
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DictTextField {
    String field();
    String dictTextSuffix() default "DictText";
}
