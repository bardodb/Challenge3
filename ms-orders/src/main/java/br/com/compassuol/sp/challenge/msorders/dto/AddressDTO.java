package br.com.compassuol.sp.challenge.msorders.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "Address Model Information"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    @Schema(
            description = "Address ZipCode"
    )
    @JsonProperty("cep")
    private String zipCode;
    @Schema(
            description = "Address Street"
    )
    @JsonProperty("logradouro")
    private String street;
    @Schema(
            description = "Address Complement"
    )
    @JsonProperty("complemento")
    private String complement;
    @Schema(
            description = "Address District"
    )
    @JsonProperty("bairro")
    private String district;
    @Schema(
            description = "Address City"
    )
    @JsonProperty("localidade")
    private String city;
    @Schema(
            description = "Address State"
    )
    @JsonProperty("uf")
    private String state;
}
