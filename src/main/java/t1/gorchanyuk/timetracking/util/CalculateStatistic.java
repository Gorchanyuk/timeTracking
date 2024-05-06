package t1.gorchanyuk.timetracking.util;

import org.springframework.stereotype.Component;
import t1.gorchanyuk.timetracking.dto.Statistics;
import t1.gorchanyuk.timetracking.entity.ExecutionTime;

import java.util.List;

@Component
public class CalculateStatistic {

    public Statistics calculate(List<ExecutionTime> executionTimes) {
        if (executionTimes.isEmpty()) {
            return Statistics.builder().build();
        }

        long sum = executionTimes.stream().mapToLong(ExecutionTime::getExecutionTime).sum();
        long min = executionTimes.stream().mapToLong(ExecutionTime::getExecutionTime).min().orElse(0);
        long max = executionTimes.stream().mapToLong(ExecutionTime::getExecutionTime).max().orElse(0);
        double average = (double) sum / executionTimes.size();
        double errorPercent = calculateSuccessfullyPercentage(executionTimes);
        long countWorks = executionTimes.size();

        return Statistics.builder()
                .averageExecutionTime(average)
                .minExecutionTime(min)
                .maxExecutionTime(max)
                .successfullyPercent(errorPercent)
                .countWorks(countWorks)
                .build();
    }

    private double calculateSuccessfullyPercentage(List<ExecutionTime> executionTimes) {
        int successfullyExecutions = (int) executionTimes.stream()
                .filter(ExecutionTime::isSuccessfully)
                .count();
        int totalExecutions = executionTimes.size();

        if (totalExecutions == 0) {
            return 0; // Если нет выполнений, коэффициент ошибки равен 0
        }

        return (double) successfullyExecutions / totalExecutions * 100;
    }
}
