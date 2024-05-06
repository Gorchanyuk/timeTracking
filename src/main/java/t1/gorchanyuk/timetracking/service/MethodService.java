package t1.gorchanyuk.timetracking.service;

import t1.gorchanyuk.timetracking.dto.MethodDto;
import t1.gorchanyuk.timetracking.dto.Statistics;
import t1.gorchanyuk.timetracking.entity.Method;

public interface MethodService {

    Method findByNameOrSave(MethodDto dto);

    Statistics getStatisticsByMethodId(long methodId);

    Statistics getStatisticsByAsyncStatus(boolean async);

    Statistics getStatisticsByGroup(String group, boolean async);
}
