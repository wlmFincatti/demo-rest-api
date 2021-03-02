package br.com.wlmfincatti.demojpawithkafka.service.exception;

public class DocumentExistException extends RuntimeException{

    public DocumentExistException() {
        super();
    }

    public DocumentExistException(String message) {
        super(message);
    }

    public DocumentExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentExistException(Throwable cause) {
        super(cause);
    }

    protected DocumentExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
