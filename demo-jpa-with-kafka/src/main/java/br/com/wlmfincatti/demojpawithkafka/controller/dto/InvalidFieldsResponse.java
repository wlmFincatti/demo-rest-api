package br.com.wlmfincatti.demojpawithkafka.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvalidFieldsResponse {

    private int statusCode;
    private LocalDate timestamp;
    private List<String> errors;
}
