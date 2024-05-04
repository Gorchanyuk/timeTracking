package t1.gorchanyuk.homework.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import t1.gorchanyuk.homework.entity.Method;

import java.util.List;
import java.util.Optional;

@Repository
public interface MethodRepository extends JpaRepository<Method, Long> {

    @EntityGraph(attributePaths = {"executionTimes"})
    Optional<Method> findByNameAndDeclaredType(String name, String declaredType);

    @EntityGraph(attributePaths = {"executionTimes"})
    Optional<Method> findById(long id);

    @EntityGraph(attributePaths = {"executionTimes"})
    List<Method> findAllByGroup(String group);

    @EntityGraph(attributePaths = {"executionTimes"})
    List<Method> findAll();
}