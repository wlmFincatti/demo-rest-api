package br.com.wlmfincatti.demojpawithkafka.service;

import br.com.wlmfincatti.demojpawithkafka.model.Employee;
import br.com.wlmfincatti.demojpawithkafka.repository.EmployeeRepository;
import br.com.wlmfincatti.demojpawithkafka.service.exception.EmployeeNotFounException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;
    @Mock
    private CompanyService companyService;
    @Mock
    private EmployeeProducer employeeProducer;
    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetEmployeeById() {
        Employee employeeMock = Mockito.mock(Employee.class);
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(employeeMock));

        Employee result = employeeService.getEmployeeById(id);

        assertEquals(employeeMock, result);
    }

    @Test
    void shouldNotGetEmployeeById() {
        Employee employeeMock = Mockito.mock(Employee.class);
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EmployeeNotFounException.class, () -> {
            employeeService.getEmployeeById(id);
        });

        assertEquals(String.format("Employee with id %d not found", id), exception.getMessage());
    }

    @Test
    void shouldGetEmployees() {
        Employee employeeMock = Mockito.mock(Employee.class);
        Long id = 1L;
        Integer from = 1;
        Integer size = 1;
        String filedSorted = "name";
        PageRequest pageRequest = PageRequest.of(from, size, Sort.by(filedSorted));
        PageImpl<Employee> employees = new PageImpl<>(List.of(employeeMock));
        when(repository.findAll(pageRequest)).thenReturn(employees);

        Page<Employee> result = employeeService.getEmployees(from, size, filedSorted);

        verify(repository, times(1)).findAll(pageRequest);
        assertEquals(1, result.getNumberOfElements());
        assertEquals(employeeMock, result.get().findFirst().get());
    }

}