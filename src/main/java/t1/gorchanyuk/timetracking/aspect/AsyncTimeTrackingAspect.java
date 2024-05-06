package t1.gorchanyuk.timetracking.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import t1.gorchanyuk.timetracking.dto.ExecutionTimeDto;
import t1.gorchanyuk.timetracking.dto.MethodDto;
import t1.gorchanyuk.timetracking.mapper.ExecutionTimeFromSignatureMapper;
import t1.gorchanyuk.timetracking.mapper.MethodMapper;
import t1.gorchanyuk.timetracking.service.ExecutionTimeService;

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

    private final MethodMapper methodMapper;

    @Pointcut("@annotation(t1.gorchanyuk.timetracking.annotation.TrackAsyncTime)")
    public void getAnnotationTrackAsyncTime() {
    }

    @Around("getAnnotationTrackAsyncTime()")
    public Object aroundTrackAsyncTimeAspect(ProceedingJoinPoint joinPoint) {
        MethodDto methodDto = methodMapper.getMethodDtoFromSignature(joinPoint.getSignature());
        return CompletableFuture.runAsync(() -> {
            try {
                long startTime = System.currentTimeMillis();
                joinPoint.proceed();
                long endTime = System.currentTimeMillis();

                ExecutionTimeDto dto = mapper.shaperAsyncDto(methodDto, endTime - startTime);
                executionTimeService.save(dto);
            } catch (Throwable e) {
                ExecutionTimeDto dto = mapper.shaperWithExceptionAsyncDto(methodDto);
                executionTimeService.save(dto);

                throw new RuntimeException(e);
            }
        });
    }

}
