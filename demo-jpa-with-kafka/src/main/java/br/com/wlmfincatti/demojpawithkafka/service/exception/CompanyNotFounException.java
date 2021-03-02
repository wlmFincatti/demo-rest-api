package br.com.wlmfincatti.demojpawithkafka.service.exception;

public class CompanyNotFounException extends RuntimeException{

    public CompanyNotFounException() {
        super();
    }

    public CompanyNotFounException(String message) {
        super(message);
    }

    public CompanyNotFounException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompanyNotFounException(Throwable cause) {
        super(cause);
    }

    protected CompanyNotFounException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
