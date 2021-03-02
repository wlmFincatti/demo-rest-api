package br.com.wlmfincatti.demojpawithkafka.service;

import br.com.wlmfincatti.demojpawithkafka.model.Company;
import br.com.wlmfincatti.demojpawithkafka.repository.CompanyRepository;
import br.com.wlmfincatti.demojpawithkafka.service.exception.CompanyNotFounException;
import br.com.wlmfincatti.demojpawithkafka.service.exception.DocumentExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;

    //    @Cacheable(cacheNames = "Company", key = "#root.method.name")
    public List<Company> getCompanies() {
        return repository.findAll();
    }

    //    @Cacheable(cacheNames = "CompanyByid", key = "#id")
    public Company getCompanyById(Long id) {
        return getCompany(id);
    }

    //    @CacheEvict(cacheNames = "Company", allEntries = true)
    public Company createCompany(Company company) {
        repository.findByDocument(company.getDocument())
                .ifPresent(c -> {
                    log.info("Company with document {} exists", company.getDocument());
                    throw new DocumentExistException(String.format("Company document %s exists", company.getDocument()));
                });

        return repository.save(company);
    }

    //    @CacheEvict(cacheNames = "Company", key = "#id")
    public void deleteCompany(Long id) {
        getCompany(id);

        log.info("deleting company with id {}", id);
        repository.deleteById(id);
    }

    //    @CachePut(cacheNames = "Company", key = "#company.id")
    public Company updateCompany(Company company) {
        Company companyToUpdated = getCompany(company.getId());

        companyToUpdated.setAddress(company.getAddress());
        companyToUpdated.setName(company.getName());
        companyToUpdated.setEmployees(company.getEmployees());
        companyToUpdated.setDocument(company.getDocument());

        log.info("Updating company with id {} - company: [{}]", company.getId(), company);
        return repository.save(companyToUpdated);
    }

    private Company getCompany(Long id) {
        log.info("Searching company with id {}", id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.info("Company with id {} not found", id);
                    return new CompanyNotFounException(String.format("Company with id %s not found", id));
                });
    }
}
