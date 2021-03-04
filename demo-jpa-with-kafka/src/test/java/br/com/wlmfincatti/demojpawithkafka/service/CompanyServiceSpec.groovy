package br.com.wlmfincatti.demojpawithkafka.service

import br.com.wlmfincatti.demojpawithkafka.model.Company
import br.com.wlmfincatti.demojpawithkafka.repository.CompanyRepository
import spock.lang.Specification

class CompanyServiceSpec extends Specification {

    private CompanyRepository companyRepository

    private CompanyService companyService

    def setup() {
        companyRepository = Mock()
        companyService = new CompanyService(repository: companyRepository)
    }

    def "should get companies"() {
        given: "mock my entity"
            def companyMock = Mock(Company.class)
            def companiesMock = Arrays.asList(companyMock)
        when: "call method to get companies"
            def result = companyService.getCompanies()
        then: "i verify the method calls one time"
            1 * companyRepository.findAll() >> companiesMock
        and: "valid the size of list and return"
            result.size() == 1
            result.get(0) == companyMock
    }
}
