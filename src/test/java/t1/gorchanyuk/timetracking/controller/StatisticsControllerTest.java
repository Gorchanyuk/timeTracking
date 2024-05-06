package t1.gorchanyuk.timetracking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import t1.gorchanyuk.timetracking.dto.Statistics;
import t1.gorchanyuk.timetracking.service.MethodService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StatisticsController.class)
@DisplayName("Тестирование REST API StatisticsController")
public class StatisticsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MethodService methodService;

    @Autowired
    private ObjectMapper mapper;

    private Statistics expectedStatistics;

    @SneakyThrows
    @BeforeEach
    private void setUp() {
        expectedStatistics = Statistics.builder()
                .averageExecutionTime(0.13)
                .minExecutionTime(0)
                .maxExecutionTime(1)
                .successfullyPercent(36.36)
                .countWorks(22)
                .build();
    }

    @Test
    @DisplayName("Тестирование получения статистики по id метода")
    public void testGetStatisticsByMethodId() throws Exception {

        long methodId = 1L;
        when(methodService.getStatisticsByMethodId(methodId)).thenReturn(expectedStatistics);

        performAndCheckResponse("/api/statistics/method/" + methodId, expectedStatistics);

        verify(methodService, times(1)).getStatisticsByMethodId(methodId);
    }

    @Test
    @DisplayName("Тестирование получения статистики по типу (синхронное/асинхронное) выполнения методов")
    public void testGetStatisticsByAsyncStatus() throws Exception {

        when(methodService.getStatisticsByAsyncStatus(anyBoolean())).thenReturn(expectedStatistics);

        performAndCheckResponse("/api/statistics", expectedStatistics);

        verify(methodService, times(1)).getStatisticsByAsyncStatus(anyBoolean());
    }

    @Test
    @DisplayName("Тестирование получения статистики по группе и типу (синхронное/асинхронное) выполнения методов")
    public void testGetStatisticsByGroup() throws Exception {

        String group = "testGroup";
        when(methodService.getStatisticsByGroup(eq(group), anyBoolean())).thenReturn(expectedStatistics);

        performAndCheckResponse("/api/statistics/group/" + group, expectedStatistics);

        verify(methodService, times(1)).getStatisticsByGroup(eq(group), anyBoolean());
    }

    private void performAndCheckResponse(String url, Object expectedData) throws Exception {
        // Общий метод для выполнения запроса и проверки ответа
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedData)));
    }
}