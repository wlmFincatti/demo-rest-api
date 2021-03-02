package br.com.wlmfincatti.demojpawithkafka.controller.dto;

import br.com.wlmfincatti.demojpawithkafka.model.Address;
import br.com.wlmfincatti.demojpawithkafka.model.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompanyToEntity {

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "document cannot be null")
    @NotBlank(message = "document is mandatory")
    private String document;

    @NotNull
    private Address address;

    public Company toEntity() {
        Company company = new Company();
        company.setName(this.name);
        company.setDocument(this.document);
        company.setAddress(this.address);
        return company;
    }

}
