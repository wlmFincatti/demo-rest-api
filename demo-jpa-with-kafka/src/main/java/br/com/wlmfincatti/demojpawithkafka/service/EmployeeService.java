package br.com.wlmfincatti.demojpawithkafka.service;

import br.com.wlmfincatti.demojpawithkafka.model.EmployeeMessage;
import br.com.wlmfincatti.demojpawithkafka.model.Employee;
import br.com.wlmfincatti.demojpawithkafka.repository.EmployeeRepository;
import br.com.wlmfincatti.demojpawithkafka.repository.EmployeeSpecification;
import br.com.wlmfincatti.demojpawithkafka.service.exception.DocumentExistException;
import br.com.wlmfincatti.demojpawithkafka.service.exception.EmployeeNotFounException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeeProducer employeeProducer;

    //    @Cacheable(cacheNames = "EmployeeByid", key = "#id")
    public Employee getEmployeeById(Long id) {
        return getEmployee(id);
    }

    //    @Cacheable(cacheNames = "Employee", key = "#root.method.name")
    public Page<Employee> getEmployees(Integer from, Integer size, String fieldSorted) {
        PageRequest pageRequest = PageRequest.of(from, size, Sort.by(fieldSorted));
        return repository.findAll(pageRequest);
    }

    //    @CacheEvict(cacheNames = "Employee", key = "#id")
    public void deleteEmployeeById(Long id) {
        getEmployee(id);

        log.info("Deleting employee with id {}", id);
        repository.deleteById(id);
    }

    //    @CacheEvict(cacheNames = "Employee", allEntries = true)
    public Employee createEmployee(Employee employee) {
        Employee employeeExistWithDocument = new Employee();
        employeeExistWithDocument.setDocument(employee.getDocument());
        repository.findOne(new EmployeeSpecification(employeeExistWithDocument))
                .ifPresent(e -> {
                    log.info("Employee with document {} exists", e.getDocument());
                    throw new DocumentExistException(String.format("Employee with document %s exists", e.getDocument()));
                });

        Long idCompany = employee.getCompany().getId();

        companyService.getCompanyById(idCompany);

        employeeProducer.sendMessage(createEmployeeMessage(employee));

        return repository.save(employee);
    }

    private EmployeeMessage createEmployeeMessage(Employee employee) {
        return EmployeeMessage.newBuilder()
                .setDocument(employee.getDocument())
                .setName(employee.getName())
                .build();
    }

    //    @CachePut(cacheNames = "Employee", key = "#employee.id")
    public Employee updateEmployee(Employee employee) {
        Employee employeeToUpdated = getEmployee(employee.getId());

        employeeToUpdated.setAddress(employee.getAddress());
        employeeToUpdated.setRole(employee.getRole());
        employeeToUpdated.setDocument(employee.getDocument());
        employeeToUpdated.setName(employee.getName());
        employeeToUpdated.setResignationDate(employee.getResignationDate());
        employeeToUpdated.setAdmissionDate(employee.getAdmissionDate());
        employeeToUpdated.setBirthDate(employee.getBirthDate());
        employeeToUpdated.setCompany(employee.getCompany());

        log.info("Updating employee with id {} - employee: [{}]", employeeToUpdated.getId(), employeeToUpdated);
        return repository.save(employeeToUpdated);
    }

    private Employee getEmployee(Long id) {
        log.info("Searching employee with id {}", id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.info("Employee with id {} not found", id);
                    return new EmployeeNotFounException(String.format("Employee with id %s not found", id));
                });
    }

}
