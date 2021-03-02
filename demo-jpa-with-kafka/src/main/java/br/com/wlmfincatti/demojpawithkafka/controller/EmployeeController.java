package br.com.wlmfincatti.demojpawithkafka.controller;

import br.com.wlmfincatti.demojpawithkafka.controller.dto.EmployeeToEntity;
import br.com.wlmfincatti.demojpawithkafka.model.Employee;
import br.com.wlmfincatti.demojpawithkafka.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeeToEntity employeeToEntity, HttpServletRequest request) {
        Employee employee = service.createEmployee(employeeToEntity.toEntity());

        URI uri = ServletUriComponentsBuilder
                .fromRequest(request)
                .replacePath(String.format("/api/company/%d", employee.getId()))
                .build().toUri();

        return ResponseEntity.created(uri).body(employee);
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> getEmployeesWithPagination(
            @RequestParam("from") Integer from,
            @RequestParam("size") Integer size,
            @RequestParam("fieldSorted") String fieldSorted
    ) {
        return ResponseEntity.ok(service.getEmployees(from, size, fieldSorted));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getEmployeeById(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteEmployeeById(@PathVariable("id") Long id) {
        service.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }
}
