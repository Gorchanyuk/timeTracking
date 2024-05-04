package t1.gorchanyuk.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import t1.gorchanyuk.homework.controller.api.StatisticsControllerApi;
import t1.gorchanyuk.homework.dto.Statistics;
import t1.gorchanyuk.homework.service.MethodService;

@RestController
@RequiredArgsConstructor
public class StatisticsController implements StatisticsControllerApi {

    private final MethodService methodService;

    @Override
    public ResponseEntity<Statistics> getStatisticsByMethodId(long methodId) {
        Statistics statistics = methodService.getStatisticsByMethodId(methodId);
        return ResponseEntity.ok(statistics);
    }

    @Override
    public ResponseEntity<Statistics> getStatisticsByAsyncStatus(boolean async) {
        Statistics statistics = methodService.getStatisticsByAsyncStatus(async);
        return ResponseEntity.ok(statistics);
    }

    @Override
    public ResponseEntity<Statistics> getStatisticsByGroup(boolean async,
                                                           String group) {
        Statistics statistics = methodService.getStatisticsByGroup(group, async);
        return ResponseEntity.ok(statistics);
    }
}