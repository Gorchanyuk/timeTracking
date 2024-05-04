package t1.gorchanyuk.homework.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t1.gorchanyuk.homework.dto.Statistics;

@RestController
@RequestMapping("/api/statistics")
@Tag(name = "Статистика", description = "Методы для получения статистики по разным параметрам")
public interface StatisticsControllerApi {

    @GetMapping(value = "/method/{methodId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Получение статистики по id метода",
            description = "Позволяет получить статистику для конкретного метода по id не зависимо от типа обработки (синхронно или ассинхронно)")
    ResponseEntity<Statistics> getStatisticsByMethodId(@Parameter(description = "id метода, для которого нужна статистика")
                                                       @PathVariable long methodId);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Получение общей статистики",
            description = "Позволяет получить статистику для методов помеченных аннотацией : \n" +
                    "1)  @TrackTime - если значение параметра async = false\n" +
                    "2)  @TrackAsyncTime - если значение параметра async = true")
    ResponseEntity<Statistics> getStatisticsByAsyncStatus(@Parameter(description = "Параметр для указания типа искомой статистики")
                                                          @RequestParam(required = false, defaultValue = "false")
                                                          boolean async);

    @GetMapping(value = "/group/{group}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Получение статистики конкретной группы",
            description = "Позволяет получить статистику для методов группы помеченных аннотацией: \n" +
                    "1) @TrackTime - если значение параметра async = false\n" +
                    "2) @TrackAsyncTime - если значение параметра async = true")
    ResponseEntity<Statistics> getStatisticsByGroup(@Parameter(description = "Параметр для указания типа искомой статистики")
                                                    @RequestParam(required = false, defaultValue = "false")
                                                    boolean async,
                                                    @Parameter(description = "Параметр для указания группы искомой статистики")
                                                    @PathVariable
                                                    String group);
}