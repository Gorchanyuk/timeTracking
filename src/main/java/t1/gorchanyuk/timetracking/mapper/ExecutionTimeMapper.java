package t1.gorchanyuk.timetracking.mapper;

import org.mapstruct.Mapper;
import t1.gorchanyuk.timetracking.dto.ExecutionTimeDto;
import t1.gorchanyuk.timetracking.entity.ExecutionTime;

@Mapper
public interface ExecutionTimeMapper {
    ExecutionTime dtoToEntity(ExecutionTimeDto dto);
}
