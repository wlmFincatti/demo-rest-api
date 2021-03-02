package br.com.wlmfincatti.demojpawithkafka.repository;

import br.com.wlmfincatti.demojpawithkafka.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class EmployeeSpecification implements Specification<Employee> {

    private Employee employee;

    @Override
    public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        Optional.ofNullable(employee.getName())
                .ifPresent(n -> predicates.add(criteriaBuilder.equal(root.get("name"), n)));

        Optional.ofNullable(employee.getAdmissionDate())
                .ifPresent(admDt -> predicates.add(criteriaBuilder.equal(root.get("admissionDate"), admDt)));

        Optional.ofNullable(employee.getDocument())
                .ifPresent(admDt -> predicates.add(criteriaBuilder.equal(root.get("document"), admDt)));

        return criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0]))).getRestriction();
    }
}
