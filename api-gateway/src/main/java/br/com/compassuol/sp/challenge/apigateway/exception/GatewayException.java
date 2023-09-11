package br.com.compassuol.sp.challenge.apigateway.exception;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Getter
public class GatewayException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public GatewayException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public GatewayException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
