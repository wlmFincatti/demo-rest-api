package br.com.wlmfincatti.demojpawithkafka.controller;

import br.com.wlmfincatti.demojpawithkafka.controller.dto.CompanyToEntity;
import br.com.wlmfincatti.demojpawithkafka.model.Company;
import br.com.wlmfincatti.demojpawithkafka.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService service;

    @GetMapping
    public ResponseEntity<List<Company>> getCompanies() {
        List<Company> companies = service.getCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCompanyById(id));
    }

    @PostMapping
    public ResponseEntity<Company> saveCompany(@Valid @RequestBody CompanyToEntity companyToEntity, HttpServletRequest request) {
        Company company = service.createCompany(companyToEntity.toEntity());

        URI uri = ServletUriComponentsBuilder
                .fromRequest(request)
                .replacePath(String.format("/api/company/%d", company.getId()))
                .build().toUri();

        return ResponseEntity.created(uri).body(company);
    }

    @PutMapping
    public ResponseEntity<Company> updateCompany(@Valid @RequestBody CompanyToEntity companyToEntity) {
        Company company = service.updateCompany(companyToEntity.toEntity());
        return ResponseEntity.ok(company);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteCompany(@PathVariable("id") Long id) {
        service.deleteCompany(id);
        return ResponseEntity.ok().build();
    }

}
