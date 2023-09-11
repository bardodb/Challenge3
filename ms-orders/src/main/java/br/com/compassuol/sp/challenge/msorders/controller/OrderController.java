package br.com.compassuol.sp.challenge.msorders.controller;

import br.com.compassuol.sp.challenge.msorders.dto.OrderCreateDTO;
import br.com.compassuol.sp.challenge.msorders.dto.OrderDTO;
import br.com.compassuol.sp.challenge.msorders.dto.OrderDetailsDTO;
import br.com.compassuol.sp.challenge.msorders.dto.OrderUpdateDTO;
import br.com.compassuol.sp.challenge.msorders.exception.ProductNotFoundException;
import br.com.compassuol.sp.challenge.msorders.publisher.OrderProducer;
import br.com.compassuol.sp.challenge.msorders.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Tag(
        name = "Order Service - OrderController",
        description = "OrderController Exposes REST APIs for Order Service"
)
@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    private OrderProducer orderProducer;

    @Operation(
            summary = "Make an order",
            description = "Make an order is used to save the order into the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<OrderDTO> makeOrder(@RequestBody OrderCreateDTO orderCreateDTO) {
        boolean validation = orderProducer.sendAndReceiveVerification(orderCreateDTO.getProducts());


        if (!validation) {
            throw new ProductNotFoundException(orderCreateDTO.getProducts());
        }

        OrderDTO orderDTO = orderService.makeOrder(orderCreateDTO);
        return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get all orders",
            description = "Get all orders is used to get all orders from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
    @Operation(
            summary = "Get one order",
            description = "Get one orders is used to get one order from the database by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailsDTO> getOrderDetails(@PathVariable Long id){
        OrderDetailsDTO orderDetailsDTO = orderService.getOrderDetails(id);
        return ResponseEntity.ok(orderDetailsDTO);
    }

    @Operation(
            summary = "Update Order",
            description = "Update Order is used to update one order by Id and save it into the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@RequestBody OrderUpdateDTO orderUpdateDTO, @PathVariable Long id){
        OrderDTO orderUpdate = orderService.updateOrder(orderUpdateDTO, id);
        return ResponseEntity.ok(orderUpdate);
    }
}
