package t1.gorchanyuk.homework.mapper;

import org.mapstruct.Mapper;
import t1.gorchanyuk.homework.dto.ExecutionTimeDto;
import t1.gorchanyuk.homework.entity.ExecutionTime;

@Mapper
public interface ExecutionTimeMapper {
    ExecutionTime dtoToEntity(ExecutionTimeDto dto);
}
