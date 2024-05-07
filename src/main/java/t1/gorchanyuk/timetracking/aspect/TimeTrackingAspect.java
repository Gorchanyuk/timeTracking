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
import t1.gorchanyuk.timetracking.mapper.ExecutionTimeMapper;
import t1.gorchanyuk.timetracking.mapper.MethodMapper;
import t1.gorchanyuk.timetracking.service.ExecutionTimeService;

/**
 * Аспект для синхронного отслеживания времени выполнения методов, помеченных аннотацией @TrackTime
 */
@Component
@Aspect
@Order(10)
@RequiredArgsConstructor
@Slf4j
public class TimeTrackingAspect {

    private final ExecutionTimeMapper mapper;
    private final ExecutionTimeService executionTimeService;
    private final MethodMapper methodMapper;

    @Pointcut("@annotation(t1.gorchanyuk.timetracking.annotation.TrackTime)")
    public void getAnnotationTrackTime() {
    }

    @Around("getAnnotationTrackTime()")
    public Object trackTimeAspect(ProceedingJoinPoint joinPoint) {
        MethodDto methodDto = methodMapper.getMethodDtoFromSignature(joinPoint.getSignature());
        try {
            long startTime = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();

            ExecutionTimeDto dto = mapper.shaperDto(methodDto,endTime - startTime);
            executionTimeService.save(dto);

            return result;
        } catch (Throwable e) {
            ExecutionTimeDto dto = mapper.shaperWithExceptionDto(methodDto);
            executionTimeService.save(dto);

            throw new RuntimeException(e);
        }
    }
}