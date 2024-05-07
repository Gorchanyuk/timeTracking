package t1.gorchanyuk.timetracking.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import t1.gorchanyuk.timetracking.dto.ExecutionTimeDto;
import t1.gorchanyuk.timetracking.dto.MethodDto;
import t1.gorchanyuk.timetracking.entity.ExecutionTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@DisplayName("Тестирование маппера ExecutionTimeMapper")
public class ExecutionTimeMapperTest {

    private final ExecutionTimeMapper mapper = Mappers.getMapper(ExecutionTimeMapper.class);

    @Test
    @DisplayName("Тест преобразования ExecutionTimeDto в ExecutionTime")
    public void testDtoToEntity() {

        ExecutionTimeDto dto = ExecutionTimeDto.builder()
                .methodDto(new MethodDto())
                .executionTime(100L)
                .async(true)
                .successfully(true)
                .build();

        ExecutionTime result = mapper.dtoToEntity(dto);

        assertEquals(dto.getExecutionTime(), result.getExecutionTime());
        assertEquals(dto.isAsync(), result.isAsync());
        assertEquals(dto.isSuccessfully(), result.isSuccessfully());
        assertNull(result.getMethodId());
    }

    @Test
    @DisplayName("Тест получения ExecutionTimeDto для асинхронного метода с ошибкой")
    public void testShaperWithExceptionAsyncDto() {

        MethodDto methodDto = mock(MethodDto.class);

        ExecutionTimeDto result = mapper.shaperWithExceptionAsyncDto(methodDto);

        assertEquals(0L, result.getExecutionTime());
        assertTrue(result.isAsync());
        assertFalse(result.isSuccessfully());
        assertEquals(methodDto, result.getMethodDto());

    }

    @Test
    @DisplayName("Тест получения ExecutionTimeDto для синхронного метода с ошибкой")
    public void testShaperWithExceptionDto() {
        MethodDto methodDto = mock(MethodDto.class);

        ExecutionTimeDto result = mapper.shaperWithExceptionDto(methodDto);

        assertEquals(0L, result.getExecutionTime());
        assertFalse(result.isAsync());
        assertFalse(result.isSuccessfully());
        assertEquals(methodDto, result.getMethodDto());
    }

    @Test
    @DisplayName("Тест получения ExecutionTimeDto для асинхронного метода без ошибок")
    public void testShaperAsyncDto() {
        MethodDto methodDto = mock(MethodDto.class);
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
        MethodDto methodDto = mock(MethodDto.class);
        long executionTime = 456L;

        ExecutionTimeDto result = mapper.shaperDto(methodDto, executionTime);

        assertEquals(executionTime, result.getExecutionTime());
        assertFalse(result.isAsync());
        assertTrue(result.isSuccessfully());
        assertEquals(methodDto, result.getMethodDto());
    }
}