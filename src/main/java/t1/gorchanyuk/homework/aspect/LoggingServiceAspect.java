package t1.gorchanyuk.homework.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Аспект для логирования работы публичных методов всех классов,
 * расположенных в пакете t1.gorchanyuk.homework.service и всех его подпакетах
 */

@Component
@Aspect
@Slf4j
public class LoggingServiceAspect {

    @Around("within(t1.gorchanyuk.homework.service..*) && " +
            "execution(public * * (..))")
    public Object loggingAroundService(ProceedingJoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        log.info("Выполнение метода {} с аргументами {}", methodName, methodArgs);

        try {
            Object result = joinPoint.proceed();
            log.info("Метод {} выполнился с результатом {}", methodName, result);
            return result;
        } catch (Throwable e) {
            log.error("Во время выполнения метода {} произошла ошибка: {}", methodName, e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
