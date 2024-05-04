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

import java.util.concurrent.CompletableFuture;

/**
 * Аспект для асинхронного отслеживания времени выполнения методов, помеченных аннотацией @TrackAsyncTime
 */
@Component
@Aspect
@Order(10)
@RequiredArgsConstructor
@Slf4j
public class AsyncTimeTrackingAspect {

    private final ExecutionTimeFromSignatureMapper mapper;
    private final ExecutionTimeService executionTimeService;

    @Pointcut("@annotation(t1.gorchanyuk.homework.annotation.TrackAsyncTime)")
    public void getAnnotationTrackAsyncTime() {
    }

    @Around("getAnnotationTrackAsyncTime()")
    public Object aroundTrackAsyncTimeAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        return CompletableFuture.runAsync(() -> {
            try {
                long startTime = System.currentTimeMillis();
                joinPoint.proceed();
                long endTime = System.currentTimeMillis();

                ExecutionTimeDto dto = mapper.shaperAsyncDto(joinPoint.getSignature(), endTime - startTime);
                executionTimeService.save(dto);
            } catch (Throwable e) {
                ExecutionTimeDto dto = mapper.shaperWithExceptionAsyncDto(joinPoint.getSignature());
                executionTimeService.save(dto);

                throw new RuntimeException(e);
            }
        });
    }

}
