package br.com.wlmfincatti.demojpawithkafka.service;

import br.com.wlmfincatti.demojpawithkafka.model.Company;
import br.com.wlmfincatti.demojpawithkafka.repository.CompanyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class CompanyServiceTest {

    @Mock
    private CompanyRepository repository;

    @InjectMocks
    private CompanyService companyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCompanies() {
        Company companyMock = mock(Company.class);
        List<Company> companiesMock = List.of(companyMock);
        when(repository.findAll()).thenReturn(companiesMock);

        List<Company> result = companyService.getCompanies();

        verify(repository, times(1)).findAll();
        Assertions.assertEquals(companiesMock, result);
    }

    @Test
    void getCompanyById() {
        Long id = 1L;
        Company companyMock = mock(Company.class);
        when(repository.findById(id)).thenReturn(Optional.of(companyMock));

        Company result = companyService.getCompanyById(id);

        verify(repository, times(1)).findById(id);
        Assertions.assertEquals(companyMock, result);
    }
    
}