package t1.gorchanyuk.timetracking.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import t1.gorchanyuk.timetracking.dto.MethodDto;
import t1.gorchanyuk.timetracking.dto.Statistics;
import t1.gorchanyuk.timetracking.entity.Method;
import t1.gorchanyuk.timetracking.mapper.MethodMapper;
import t1.gorchanyuk.timetracking.repository.MethodRepository;
import t1.gorchanyuk.timetracking.service.impl.MethodServiceImpl;
import t1.gorchanyuk.timetracking.util.CalculateStatistic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MethodServiceImplTest {

    @InjectMocks
    private MethodServiceImpl methodService;

    @Mock
    private MethodMapper mapper;

    @Mock
    private MethodRepository repository;

    @Mock
    private CalculateStatistic calculateStatistic;

    @DisplayName("Тест метода findByNameOrSave с успешным сохранением")
    @Test
    public void testFindByNameOrSave() {

        MethodDto dto = new MethodDto();
        dto.setName("TestName");
        dto.setDeclaredType("TestType");
        Method expectedMethod = new Method();
        when(repository.findByNameAndDeclaredType(anyString(), anyString())).thenReturn(Optional.empty());
        when(mapper.dtoToEntity(any(MethodDto.class))).thenReturn(expectedMethod);
        when(repository.save(any(Method.class))).thenReturn(expectedMethod);

        Method result = methodService.findByNameOrSave(dto);

        verify(repository, times(1)).findByNameAndDeclaredType(anyString(), anyString());
        verify(repository, times(1)).save(any(Method.class));
        assertEquals(expectedMethod, result);
    }

    @Test
    @DisplayName("Тест метода getStatisticsByMethodId с успешным расчетом статистики")
    public void testGetStatisticsByMethodId() {

        long id = 1L;
        Method method = new Method();
        method.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(method));
        when(calculateStatistic.calculate(any())).thenReturn(Statistics.builder().build());

        Statistics result = methodService.getStatisticsByMethodId(id);

        verify(repository, times(1)).findById(anyLong());
        verify(calculateStatistic, times(1)).calculate(any());
        assertNotNull(result);
    }

    @Test
    @DisplayName("Тест метода getStatisticsByAsyncStatus с успешным расчетом статистики")
    public void testGetStatisticsByAsyncStatus() {

        List<Method> methods = new ArrayList<>();
        when(repository.findAll()).thenReturn(methods);
        when(calculateStatistic.calculate(any())).thenReturn(Statistics.builder().build());

        Statistics result = methodService.getStatisticsByAsyncStatus(true);

        verify(repository, times(1)).findAll();
        verify(calculateStatistic, times(1)).calculate(any());
        assertNotNull(result);
    }

    @Test
    @DisplayName("Тест метода getStatisticsByGroup с успешным расчетом статистики")
    public void testGetStatisticsByGroup() {

        String group = "TestGroup";
        List<Method> methods = new ArrayList<>();
        when(repository.findAllByGroup(group)).thenReturn(methods);
        when(calculateStatistic.calculate(any())).thenReturn(Statistics.builder().build());

        Statistics result = methodService.getStatisticsByGroup(group, true);

        verify(repository, times(1)).findAllByGroup(group);
        verify(calculateStatistic, times(1)).calculate(any());
        assertNotNull(result);
    }
}