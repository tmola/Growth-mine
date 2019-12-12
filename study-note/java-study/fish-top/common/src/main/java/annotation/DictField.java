package annotation;

import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/12
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Import(TextField.class)
public @interface DictField {
    String dict();
    String textFieldName() default "";
}
