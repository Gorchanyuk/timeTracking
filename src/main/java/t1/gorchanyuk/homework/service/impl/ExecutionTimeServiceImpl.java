package t1.gorchanyuk.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import t1.gorchanyuk.homework.dto.ExecutionTimeDto;
import t1.gorchanyuk.homework.dto.Statistics;
import t1.gorchanyuk.homework.entity.ExecutionTime;
import t1.gorchanyuk.homework.entity.Method;
import t1.gorchanyuk.homework.mapper.ExecutionTimeMapper;
import t1.gorchanyuk.homework.repository.ExecutionTimeRepository;
import t1.gorchanyuk.homework.service.ExecutionTimeService;
import t1.gorchanyuk.homework.service.MethodService;
import t1.gorchanyuk.homework.util.CalculateStatistic;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExecutionTimeServiceImpl implements ExecutionTimeService {

    private final MethodService methodService;
    private final ExecutionTimeMapper mapper;
    private final ExecutionTimeRepository repository;

    @Override
    @Async("jobExecutor")
    @Transactional
    public void save(ExecutionTimeDto dto) {

        Method method = methodService.findByNameOrSave(dto.getMethodDto());

        ExecutionTime executionTime = mapper.dtoToEntity(dto);
        executionTime.setMethodId(method);

        repository.save(executionTime);
    }
}
