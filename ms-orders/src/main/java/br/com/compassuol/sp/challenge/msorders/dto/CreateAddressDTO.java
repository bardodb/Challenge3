package br.com.compassuol.sp.challenge.msorders.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Schema(
        description = "Create the Address Model"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressDTO {
    @Schema(
            description = "Address ZipCode"
    )
    private String zipCode;
    @Schema(
            description = "Address Street"
    )
    private String street;
    @Schema(
            description = "Address Complement"
    )
    private String complement;
    @Schema(
            description = "Address District"
    )
    private String district;
}
