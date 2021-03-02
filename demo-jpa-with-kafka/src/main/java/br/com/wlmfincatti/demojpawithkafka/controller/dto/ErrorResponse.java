package br.com.wlmfincatti.demojpawithkafka.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {

    private int statusCode;
    private LocalDate timestamp;
    private String message;
    private String description;
}