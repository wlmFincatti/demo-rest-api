package br.com.wlmfincatti.demojpawithkafka.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(name = "name", length = 200)
    private String name;

    private String document;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<Employee> employees = new ArrayList<>();

    @Embedded
    private Address address;

    public void addEmployee(Employee employee) {
        this.employees = Optional.ofNullable(employees)
                .orElseGet(ArrayList::new);

        this.employees.add(employee);
        employee.setCompany(this);
    }
}
