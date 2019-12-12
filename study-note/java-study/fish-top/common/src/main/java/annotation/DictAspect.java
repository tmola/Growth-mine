package annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/12
 */
@Aspect
@Component
public class DictAspect {

    @Pointcut("@annotation(annotation.TransDict)")
    void whichMethod() {
    }

    @Around("whichMethod()")
    public Object toTransDict(ProceedingJoinPoint point) throws Throwable {
        System.out.println("toTransDict");
        Object result = point.proceed();
        return result;
    }

}
