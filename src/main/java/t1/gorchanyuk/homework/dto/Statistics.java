package t1.gorchanyuk.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Модель для отображения статиски работы методов")
public class Statistics {


    @Schema(description = "Среднее время выполнения работы методом (методами)")
    private double averageExecutionTime; //Среднее время выполнения

    @Schema(description = "Минимальное время выполнения работы методом (методами)")
    private long minExecutionTime;  //Минимальное время выполнения

    @Schema(description = "Максимальное время выполнения работы методом (методами)")
    private long maxExecutionTime;  //Максимальное время выполнения

    @Schema(description = "Процент завершения работы метода (методов) ошибкой")
    private double errorPercent;       //Процент завершения с ошибкой
}