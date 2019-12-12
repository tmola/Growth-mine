package components.transdict;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class DictAspect {

    @Pointcut("@annotation(TransDict)")
    void whichMethod() {
    }

    @Around("whichMethod()")
    public Object toTransDict(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        return result;
    }
}
