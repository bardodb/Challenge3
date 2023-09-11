package br.com.compassuol.sp.challenge.msorders.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Schema(
        description = "Order Create Model"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDTO {
    @Schema(
            description = "Order User ID"
    )
    private Long userId;
    @Schema(
            description = "Order Products"
    )
    private List<Long> products;
    @Schema(
            description = "Order Delivery Address"
    )
    private CreateAddressDTO deliveryAddress;
}
