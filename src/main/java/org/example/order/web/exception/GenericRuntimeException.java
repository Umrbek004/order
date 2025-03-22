package org.example.order.web.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericRuntimeException extends RuntimeException{

    private String key;

    public GenericRuntimeException(String message){
        super(message);
    }

    public GenericRuntimeException(String message, String key){
        super(message);
        this.key = key;
    }

    public GenericRuntimeException(String message, Throwable cause){
        super(message, cause);
    }

    public GenericRuntimeException(String message, Throwable cause, String key){
        super(message, cause);
        this.key = key;
    }
}
