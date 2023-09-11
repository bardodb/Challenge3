package br.com.compassuol.sp.challenge.msorders.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Schema(
        description = "Order Update Status Model"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateDTO {
    @Schema(
            description = "Order Status"
    )
    private String status;
}
