package t1.gorchanyuk.homework.mapper;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.Signature;
import org.springframework.stereotype.Component;
import t1.gorchanyuk.homework.dto.ExecutionTimeDto;

@Component
@RequiredArgsConstructor
public class ExecutionTimeFromSignatureMapper {

    private final MethodMapper methodMapper;

    public ExecutionTimeDto shaperWithExceptionAsyncDto(Signature signature) {
        return this.shaper(signature, 0, true, true);
    }

    public ExecutionTimeDto shaperWithExceptionDto(Signature signature) {
        return this.shaper(signature, 0, false, true);
    }

    public ExecutionTimeDto shaperAsyncDto(Signature signature, long executionTime) {
        return this.shaper(signature, executionTime, true, false);
    }

    public ExecutionTimeDto shaperDto(Signature signature, long executionTime) {
        return this.shaper(signature, executionTime, false, false);
    }

    private ExecutionTimeDto shaper(Signature signature, long executionTime, boolean async, boolean exceptionOccurred) {

        return ExecutionTimeDto.builder()
                .methodDto(methodMapper.getMethodDtoFromSignature(signature))
                .executionTime(executionTime)
                .async(async)
                .successfully(exceptionOccurred)
                .build();
    }
}

