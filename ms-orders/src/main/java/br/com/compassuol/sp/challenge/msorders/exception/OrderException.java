package br.com.compassuol.sp.challenge.msorders.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


public class OrderException extends RuntimeException{
    @Getter
    private HttpStatus status;
    private String message;

    public OrderException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public OrderException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
