package t1.gorchanyuk.homework.service;

import t1.gorchanyuk.homework.dto.MethodDto;
import t1.gorchanyuk.homework.dto.Statistics;
import t1.gorchanyuk.homework.entity.Method;

public interface MethodService {

    Method findByNameOrSave(MethodDto dto);

    Statistics getStatisticsByMethodId(long methodId);

    Statistics getStatisticsByAsyncStatus(boolean async);

    Statistics getStatisticsByGroup(String group, boolean async);
}
