package t1.gorchanyuk.timetracking.mapper;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.Signature;
import org.springframework.stereotype.Component;
import t1.gorchanyuk.timetracking.dto.ExecutionTimeDto;
import t1.gorchanyuk.timetracking.dto.MethodDto;

@Component
@RequiredArgsConstructor
public class ExecutionTimeFromSignatureMapper {

    public ExecutionTimeDto shaperWithExceptionAsyncDto(MethodDto methodDto) {
        return this.shaper(methodDto, 0, true, false);
    }

    public ExecutionTimeDto shaperWithExceptionDto(MethodDto methodDto) {
        return this.shaper(methodDto, 0, false, false);
    }

    public ExecutionTimeDto shaperAsyncDto(MethodDto methodDto, long executionTime) {
        return this.shaper(methodDto, executionTime, true, true);
    }

    public ExecutionTimeDto shaperDto(MethodDto methodDto, long executionTime) {
        return this.shaper(methodDto, executionTime, false, true);
    }

    private ExecutionTimeDto shaper(MethodDto methodDto, long executionTime, boolean async, boolean isSuccessfully) {

        return ExecutionTimeDto.builder()
                .methodDto(methodDto)
                .executionTime(executionTime)
                .async(async)
                .successfully(isSuccessfully)
                .build();
    }
}

