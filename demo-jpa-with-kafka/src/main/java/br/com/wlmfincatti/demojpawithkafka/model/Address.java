package br.com.wlmfincatti.demojpawithkafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Address {

    private String street;
    private Integer number;
    private String city;
    private String State;
    private Integer zipCode;

}
