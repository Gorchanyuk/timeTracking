package t1.gorchanyuk.timetracking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import t1.gorchanyuk.timetracking.dto.ExecutionTimeDto;
import t1.gorchanyuk.timetracking.dto.MethodDto;
import t1.gorchanyuk.timetracking.entity.ExecutionTime;

@Mapper
public interface ExecutionTimeMapper {
    ExecutionTime dtoToEntity(ExecutionTimeDto dto);

    @Mapping(target = "executionTime", constant = "0L")
    @Mapping(target = "async", constant = "true")
    @Mapping(target = "successfully", constant = "false")
    ExecutionTimeDto shaperWithExceptionAsyncDto(MethodDto methodDto);

    @Mapping(target = "executionTime", constant = "0L")
    @Mapping(target = "async", constant = "false")
    @Mapping(target = "successfully", constant = "false")
    ExecutionTimeDto shaperWithExceptionDto(MethodDto methodDto);

    @Mapping(target = "async", constant = "true")
    @Mapping(target = "successfully", constant = "true")
    ExecutionTimeDto shaperAsyncDto(MethodDto methodDto, long executionTime);

    @Mapping(target = "async", constant = "false")
    @Mapping(target = "successfully", constant = "true")
    ExecutionTimeDto shaperDto(MethodDto methodDto, long executionTime);

}
