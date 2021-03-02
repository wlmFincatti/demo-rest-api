package br.com.wlmfincatti.demojpawithkafka.controller.dto;

import br.com.wlmfincatti.demojpawithkafka.model.Address;
import br.com.wlmfincatti.demojpawithkafka.model.Company;
import br.com.wlmfincatti.demojpawithkafka.model.Employee;
import br.com.wlmfincatti.demojpawithkafka.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeToEntity {


    private String name;

    @NotNull(message = "document cannot be null")
    @NotBlank(message = "document is mandatory")
    private String document;

    @Past(message = "birth date not valid")
    private LocalDate birthDate;

    @PastOrPresent(message = "admission date is not valid")
    private LocalDate admissionDate;

    @FutureOrPresent(message = "resignation date is not valid")
    private LocalDate resignationDate;
    private Role role;

    @NotNull
    @NotBlank(message = "missing company")
    private Company company;

    @NotNull
    private Address address;

    public Employee toEntity() {
        Employee employee = new Employee();
        employee.setName(this.name);
        employee.setDocument(this.document);
        employee.setBirthDate(this.birthDate);
        employee.setAdmissionDate(this.admissionDate);
        employee.setResignationDate(this.resignationDate);
        employee.setRole(this.role);
        employee.setCompany(this.company);
        employee.setAddress(this.address);
        return employee;
    }

}
