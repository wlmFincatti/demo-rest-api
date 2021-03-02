package br.com.wlmfincatti.demojpawithkafka.service.exception;

public class EmployeeNotFounException extends RuntimeException{

    public EmployeeNotFounException() {
        super();
    }

    public EmployeeNotFounException(String message) {
        super(message);
    }

    public EmployeeNotFounException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeNotFounException(Throwable cause) {
        super(cause);
    }

    protected EmployeeNotFounException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
