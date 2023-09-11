package br.com.compassuol.sp.challenge.msorders.controller;

import br.com.compassuol.sp.challenge.msorders.dto.AddressDTO;
import br.com.compassuol.sp.challenge.msorders.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Tag(
        name = "Order Service - AddressController",
        description = "AddressController Exposes REST APIs for Order Service"
)
@AllArgsConstructor
@RestController
@RequestMapping("/address")
public class AddressControler {
    private OrderService service;

    @Operation(
            summary = "Get Address",
            description = "Get Address is used to get all addresses from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddress(){
        List<AddressDTO> addressDTOs = service.getAllAddress();
        return ResponseEntity.ok(addressDTOs);
    }
}
