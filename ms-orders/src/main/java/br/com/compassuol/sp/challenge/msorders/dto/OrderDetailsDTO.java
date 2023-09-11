package br.com.compassuol.sp.challenge.msorders.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
@Schema(
        description = "Order Details Model"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {
    @Schema(
            description = "Order ID"
    )
    private Long id;
    @Schema(
            description = "Order User ID"
    )
    private Long userId;
    @Schema(
            description = "Order Products"
    )
    private List<ProductDTO> products;
    @Schema(
            description = "Order Delivery Address"
    )
    private AddressDTO deliveryAddress;
    @Schema(
            description = "Order Status"
    )
    private String status;
    @Schema(
            description = "Order Price"
    )
    private BigDecimal totalPrice;
}
