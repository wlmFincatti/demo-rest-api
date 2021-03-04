package br.com.wlmfincatti.demojpawithkafka.controller.exceptionhandler;

import br.com.wlmfincatti.demojpawithkafka.controller.dto.ErrorResponse;
import br.com.wlmfincatti.demojpawithkafka.controller.dto.InvalidFieldsResponse;
import br.com.wlmfincatti.demojpawithkafka.service.exception.CompanyNotFounException;
import br.com.wlmfincatti.demojpawithkafka.service.exception.DocumentExistException;
import br.com.wlmfincatti.demojpawithkafka.service.exception.EmployeeNotFounException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerCustomHandler {

    @ExceptionHandler({CompanyNotFounException.class, EmployeeNotFounException.class})
    public ResponseEntity<ErrorResponse> responseNotFound(RuntimeException ex, WebRequest request) {
        ErrorResponse message = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                LocalDate.now(),
                ex.getMessage(),
                HttpStatus.NOT_FOUND.getReasonPhrase());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DocumentExistException.class)
    public ResponseEntity<ErrorResponse> responseDocumentExists(RuntimeException ex, WebRequest request) {
        ErrorResponse message = new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                LocalDate.now(),
                ex.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
        return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<InvalidFieldsResponse> responseInvalidFields(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        InvalidFieldsResponse message = new InvalidFieldsResponse(
                HttpStatus.BAD_REQUEST.value(),
                LocalDate.now(),
                errors
        );
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
