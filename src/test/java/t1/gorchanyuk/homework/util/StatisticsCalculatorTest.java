package t1.gorchanyuk.homework.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import t1.gorchanyuk.homework.dto.Statistics;
import t1.gorchanyuk.homework.entity.ExecutionTime;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatisticsCalculatorTest {

    @Test
    @DisplayName("Проверка работы при пустом списке")
    void testCalculateEmptyList() {
        // Arrange
        List<ExecutionTime> emptyList = new ArrayList<>();

        // Act
        Statistics result = new CalculateStatistic().calculate(emptyList);

        // Assert
        assertEquals(0, result.getAverageExecutionTime());
        assertEquals(0, result.getMinExecutionTime());
        assertEquals(0, result.getMaxExecutionTime());
        assertEquals(0, result.getSuccessfullyPercent());
        assertEquals(0, result.getCountWorks());
    }

    @Test
    @DisplayName("Проверка работы для 3х записей")
    void testCalculateWithExecutionTimes() {
        // Arrange
        List<ExecutionTime> executionTimes = new ArrayList<>();
        executionTimes.add(ExecutionTime.builder().executionTime(2L).async(false).successfully(true).build());
        executionTimes.add(ExecutionTime.builder().executionTime(1L).async(false).successfully(true).build());
        executionTimes.add(ExecutionTime.builder().executionTime(5L).async(true).successfully(false).build());

        // Act
        Statistics result = new CalculateStatistic().calculate(executionTimes);

        // Assert
        assertEquals(2.66, result.getAverageExecutionTime(), 0.01); // Проверяем среднее время с учетом округления
        assertEquals(1, result.getMinExecutionTime());
        assertEquals(5, result.getMaxExecutionTime());
        assertEquals(66.66, result.getSuccessfullyPercent(), 0.01); // 2 из 3 успешно
        assertEquals(3, result.getCountWorks());
    }

}