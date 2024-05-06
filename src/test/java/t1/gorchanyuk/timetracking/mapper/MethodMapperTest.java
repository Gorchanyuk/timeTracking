package t1.gorchanyuk.timetracking.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import t1.gorchanyuk.timetracking.dto.MethodDto;
import t1.gorchanyuk.timetracking.entity.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тестирование маппера MethodMapper")
public class MethodMapperTest {

    private final MethodMapper mapper = Mappers.getMapper(MethodMapper.class);

    @Test
    @DisplayName("Тест преобразования MethodDto в Method")
    public void testDtoToEntity() {

        MethodDto dto = new MethodDto();
        dto.setName("testName");
        dto.setDeclaredType("TestType");
        dto.setGroup("group");

        Method result = mapper.dtoToEntity(dto);


        assertEquals(dto.getName(), result.getName());
        assertEquals(dto.getDeclaredType(), result.getDeclaredType());
        assertEquals(dto.getGroup(), result.getGroup());
    }
}