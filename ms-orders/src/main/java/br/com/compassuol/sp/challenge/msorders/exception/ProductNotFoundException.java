package br.com.compassuol.sp.challenge.msorders.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException{
    private List<Long> products;

    public ProductNotFoundException( List<Long> products) {
        super("Products not found with ids: " + products);
        this.products = products;
    }
}
