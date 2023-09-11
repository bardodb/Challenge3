package br.com.compassuol.sp.challenge.msorders.service;

import  br.com.compassuol.sp.challenge.msorders.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8000", name = "PRODUCT-SERVICE")
public interface APIClient {
    @GetMapping("/products/{id}")
    public ProductDTO getProductById(@PathVariable Long id);
}
