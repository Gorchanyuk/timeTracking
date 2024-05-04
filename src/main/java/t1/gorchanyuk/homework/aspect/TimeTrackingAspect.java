package t1.gorchanyuk.homework.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import t1.gorchanyuk.homework.dto.ExecutionTimeDto;
import t1.gorchanyuk.homework.mapper.ExecutionTimeFromSignatureMapper;
import t1.gorchanyuk.homework.service.ExecutionTimeService;

/**
 * Аспект для синхронного отслеживания времени выполнения методов, помеченных аннотацией @TrackTime
 */
@Component
@Aspect
@Order(10)
@RequiredArgsConstructor
@Slf4j
public class TimeTrackingAspect {

    private final ExecutionTimeFromSignatureMapper mapper;
    private final ExecutionTimeService executionTimeService;

    @Pointcut("@annotation(t1.gorchanyuk.homework.annotation.TrackTime)")
    public void getAnnotationTrackTime() {
    }

    @Around("getAnnotationTrackTime()")
    public Object trackTimeAspect(ProceedingJoinPoint joinPoint) {

        try {
            long startTime = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();

            ExecutionTimeDto dto = mapper.shaperDto(joinPoint.getSignature(),endTime - startTime);
            executionTimeService.save(dto);

            return result;
        } catch (Throwable e) {
            ExecutionTimeDto dto = mapper.shaperWithExceptionDto(joinPoint.getSignature());
            executionTimeService.save(dto);

            throw new RuntimeException(e);
        }
    }
}