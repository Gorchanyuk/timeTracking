package t1.gorchanyuk.timetracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import t1.gorchanyuk.timetracking.entity.ExecutionTime;

@Repository
public interface ExecutionTimeRepository extends JpaRepository<ExecutionTime, Long> {

}