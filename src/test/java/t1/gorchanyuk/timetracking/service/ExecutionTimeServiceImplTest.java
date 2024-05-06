package t1.gorchanyuk.timetracking.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import t1.gorchanyuk.timetracking.dto.ExecutionTimeDto;
import t1.gorchanyuk.timetracking.dto.MethodDto;
import t1.gorchanyuk.timetracking.entity.ExecutionTime;
import t1.gorchanyuk.timetracking.entity.Method;
import t1.gorchanyuk.timetracking.mapper.ExecutionTimeMapper;
import t1.gorchanyuk.timetracking.repository.ExecutionTimeRepository;
import t1.gorchanyuk.timetracking.service.impl.ExecutionTimeServiceImpl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тестирование класса ExecutionTimeServiceImpl")
public class ExecutionTimeServiceImplTest {

    @InjectMocks
    private ExecutionTimeServiceImpl executionTimeService;

    @Mock
    private MethodService methodService;

    @Mock
    private ExecutionTimeMapper mapper;

    @Mock
    private ExecutionTimeRepository repository;

    @DisplayName("Тест сохранения ExecutionTime с успешным выполнением")
    @Test
    public void testSaveExecutionTimeSuccess() {
        // Arrange
        ExecutionTimeDto dto = ExecutionTimeDto.builder()
                .methodDto(new MethodDto())
                .build();
        ExecutionTime executionTime = new ExecutionTime();
        Method method = new Method();
        method.setName("TestMethod");
        when(methodService.findByNameOrSave(any(MethodDto.class))).thenReturn(method);
        when(mapper.dtoToEntity(any(ExecutionTimeDto.class))).thenReturn(executionTime);

        executionTimeService.save(dto);

        verify(repository, times(1)).save(any(ExecutionTime.class));
    }

    @DisplayName("Тест сохранения ExecutionTime с ошибкой")
    @Test
    public void testSaveExecutionTimeError() {

        ExecutionTimeDto dto = ExecutionTimeDto.builder()
                .methodDto(new MethodDto())
                .build();
        when(methodService.findByNameOrSave(any(MethodDto.class))).thenThrow(new RuntimeException("Error"));

        assertThrows(RuntimeException.class, () -> executionTimeService.save(dto));
    }
}