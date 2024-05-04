package t1.gorchanyuk.homework.util;

import org.springframework.stereotype.Component;
import t1.gorchanyuk.homework.dto.Statistics;
import t1.gorchanyuk.homework.entity.ExecutionTime;

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
        double errorPercent = calculateErrorPercentage(executionTimes);

        return Statistics.builder()
                .averageExecutionTime(average)
                .minExecutionTime(min)
                .maxExecutionTime(max)
                .errorPercent(errorPercent)
                .build();
    }

    private double calculateErrorPercentage(List<ExecutionTime> executionTimes) {
        int successfulExecutions = (int) executionTimes.stream()
                .filter(ExecutionTime::isSuccessfully)
                .count();
        int totalExecutions = executionTimes.size();

        if (totalExecutions == 0) {
            return 0; // Если нет выполнений, коэффициент ошибки равен 0
        }

        return (double) (totalExecutions - successfulExecutions) / totalExecutions * 100;
    }
}
