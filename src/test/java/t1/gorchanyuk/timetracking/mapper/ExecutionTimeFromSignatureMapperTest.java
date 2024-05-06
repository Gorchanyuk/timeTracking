package t1.gorchanyuk.timetracking.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import t1.gorchanyuk.timetracking.dto.ExecutionTimeDto;
import t1.gorchanyuk.timetracking.dto.MethodDto;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тестирование маппера ExecutionTimeFromSignatureMapper")
public class ExecutionTimeFromSignatureMapperTest {

    @Mock
    private MethodDto methodDto;

    @InjectMocks
    private ExecutionTimeFromSignatureMapper mapper;

    @Test
    @DisplayName("Тест получения ExecutionTimeDto для асинхронного метода с ошибкой")
    public void testShaperWithExceptionAsyncDto() {

        ExecutionTimeDto result = mapper.shaperWithExceptionAsyncDto(methodDto);

        assertEquals(0L, result.getExecutionTime());
        assertTrue(result.isAsync());
        assertFalse(result.isSuccessfully());
        assertEquals(methodDto, result.getMethodDto());

    }

    @Test
    @DisplayName("Тест получения ExecutionTimeDto для синхронного метода с ошибкой")
    public void testShaperWithExceptionDto() {

        ExecutionTimeDto result = mapper.shaperWithExceptionDto(methodDto);

        assertEquals(0L, result.getExecutionTime());
        assertFalse(result.isAsync());
        assertFalse(result.isSuccessfully());
        assertEquals(methodDto, result.getMethodDto());
    }

    @Test
    @DisplayName("Тест получения ExecutionTimeDto для асинхронного метода без ошибок")
    public void testShaperAsyncDto() {
        long executionTime = 123L;

        ExecutionTimeDto result = mapper.shaperAsyncDto(methodDto, executionTime);

        assertEquals(executionTime, result.getExecutionTime());
        assertTrue(result.isAsync());
        assertTrue(result.isSuccessfully());
        assertEquals(methodDto, result.getMethodDto());
    }

    @Test
    @DisplayName("Тест получения ExecutionTimeDto для синхронного метода без ошибок")
    public void testShaperDto() {
        long executionTime = 456L;

        ExecutionTimeDto result = mapper.shaperDto(methodDto, executionTime);

        assertEquals(executionTime, result.getExecutionTime());
        assertFalse(result.isAsync());
        assertTrue(result.isSuccessfully());
        assertEquals(methodDto, result.getMethodDto());
    }
}