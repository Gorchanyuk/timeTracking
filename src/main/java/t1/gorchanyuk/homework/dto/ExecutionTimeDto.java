package t1.gorchanyuk.homework.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExecutionTimeDto {

    private MethodDto methodDto;        // dto метода

    private Long executionTime;         // Время выполнения работы

    private boolean async;              // Асинхронное или синхронное выполнение

    private boolean successfully;       // Успешное завершение
}
