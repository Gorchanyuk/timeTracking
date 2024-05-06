package t1.gorchanyuk.timetracking.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import t1.gorchanyuk.timetracking.dto.MethodDto;
import t1.gorchanyuk.timetracking.dto.Statistics;
import t1.gorchanyuk.timetracking.entity.ExecutionTime;
import t1.gorchanyuk.timetracking.entity.Method;
import t1.gorchanyuk.timetracking.mapper.MethodMapper;
import t1.gorchanyuk.timetracking.repository.MethodRepository;
import t1.gorchanyuk.timetracking.service.MethodService;
import t1.gorchanyuk.timetracking.util.CalculateStatistic;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MethodServiceImpl implements MethodService {

    private final MethodMapper mapper;
    private final MethodRepository repository;
    private final CalculateStatistic calculateStatistic;

    @Override
    public Method findByNameOrSave(MethodDto dto) {
        // Поиск или создание метода
        return repository.findByNameAndDeclaredType(dto.getName(), dto.getDeclaredType())
                .orElseGet(() -> save(dto));
    }

    private Method save(MethodDto dto) {

        Method method = mapper.dtoToEntity(dto);
        return repository.save(method);
    }

    @Override
    public Statistics getStatisticsByMethodId(long id) {

        Method method = repository.findById(id).orElseGet(Method::new);
        List<ExecutionTime> executionTimes = method.getExecutionTimes();
        // Расчет статистики
        return calculateStatistic.calculate(executionTimes);
    }

    @Override
    public Statistics getStatisticsByAsyncStatus(boolean async) {

        List<Method> list = repository.findAll();
        Stream<ExecutionTime> executionTimes = list.stream()
                .flatMap(method -> method
                        .getExecutionTimes()
                        .stream());
        if (async) {
            executionTimes = executionTimes.filter(ExecutionTime::isAsync);
        }
        // Расчет статистики
        return calculateStatistic.calculate(executionTimes.toList());
    }

    @Override
    public Statistics getStatisticsByGroup(String group, boolean async) {

        List<Method> list = repository.findAllByGroup(group);
        List<ExecutionTime> executionTimes = list.stream()
                .flatMap(method -> method
                        .getExecutionTimes()
                        .stream())
                .filter(executionTime -> executionTime.isAsync() == async)
                .toList();

        return calculateStatistic.calculate(executionTimes);
    }
}
