package t1.gorchanyuk.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import t1.gorchanyuk.homework.entity.ExecutionTime;

import java.util.List;

@Repository
public interface ExecutionTimeRepository extends JpaRepository<ExecutionTime, Long> {

}