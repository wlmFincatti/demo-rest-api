package br.com.wlmfincatti.demojpawithkafka.repository;

import br.com.wlmfincatti.demojpawithkafka.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByDocument(String document);

}
