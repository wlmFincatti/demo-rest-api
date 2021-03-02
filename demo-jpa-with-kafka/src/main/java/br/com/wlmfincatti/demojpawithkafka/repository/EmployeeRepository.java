package br.com.wlmfincatti.demojpawithkafka.repository;

import br.com.wlmfincatti.demojpawithkafka.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    Optional<Employee> findLikeName(@Param("name") String name);

    Optional<Employee> findByZipCode(Integer name);

    Optional<Employee> findFired();

    @Query(
            value = "select e from Employee e where e.name = :name",
            countQuery = "select count(e) from Employee e where e.name = :name",
            nativeQuery = true)
    Page<Employee> findEmployeesByName(@Param("name") String name, Pageable page);
}
