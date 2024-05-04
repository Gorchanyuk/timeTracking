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
        return this.shaper(signature, 0, true, false);
    }

    public ExecutionTimeDto shaperWithExceptionDto(Signature signature) {
        return this.shaper(signature, 0, false, false);
    }

    public ExecutionTimeDto shaperAsyncDto(Signature signature, long executionTime) {
        return this.shaper(signature, executionTime, true, true);
    }

    public ExecutionTimeDto shaperDto(Signature signature, long executionTime) {
        return this.shaper(signature, executionTime, false, true);
    }

    private ExecutionTimeDto shaper(Signature signature, long executionTime, boolean async, boolean isSuccessfully) {

        return ExecutionTimeDto.builder()
                .methodDto(methodMapper.getMethodDtoFromSignature(signature))
                .executionTime(executionTime)
                .async(async)
                .successfully(isSuccessfully)
                .build();
    }
}

