package br.com.compassuol.sp.challenge.msorders.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Schema(
        description = "Order Product Model"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @Schema(
            description = "Product ID"
    )
    private Long id;
    @Schema(
            description = "Product Name"
    )
    private String name;
    @Schema(
            description = "Product Description"
    )
    private String description;
    @Schema(
            description = "Product Price"
    )
    private BigDecimal price;
}
