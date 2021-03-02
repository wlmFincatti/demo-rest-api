package br.com.wlmfincatti.demojpawithkafka.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Employee")
@NamedQueries({
        @NamedQuery(name = "Employee.findFired", query = "select e from Employee e where e.resignationDate is not null"),
        @NamedQuery(name = "Employee.findLikeName", query = "select e from Employee e where e.name like :name"),
        @NamedQuery(name = "Employee.findByZipCode", query = "select e from Employee e where e.address.zipCode = ?1")
})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(name = "name", length = 200)
    private String name;

    private String document;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "admission_date", nullable = false)
    private LocalDate admissionDate;

    @Column(name = "resignation_date")
    private LocalDate resignationDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false, referencedColumnName = "id")
    private Company company;

    @Embedded
    private Address address;

}
