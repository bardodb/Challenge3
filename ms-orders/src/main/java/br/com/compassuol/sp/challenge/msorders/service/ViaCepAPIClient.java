package br.com.compassuol.sp.challenge.msorders.service;

import br.com.compassuol.sp.challenge.msorders.dto.AddressDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "viacep.com.br/ws", name = "VIACEP-SERVICE")
public interface ViaCepAPIClient {
    @GetMapping("/{zipCode}/json")
    AddressDTO getAddress(@PathVariable String zipCode);
}
