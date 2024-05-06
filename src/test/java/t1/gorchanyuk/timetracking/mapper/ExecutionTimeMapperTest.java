package t1.gorchanyuk.timetracking.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import t1.gorchanyuk.timetracking.dto.ExecutionTimeDto;
import t1.gorchanyuk.timetracking.dto.MethodDto;
import t1.gorchanyuk.timetracking.entity.ExecutionTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
}