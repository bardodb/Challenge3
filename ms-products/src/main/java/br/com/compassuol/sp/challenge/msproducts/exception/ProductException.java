package br.com.compassuol.sp.challenge.msproducts.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class ProductException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public ProductException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ProductException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
