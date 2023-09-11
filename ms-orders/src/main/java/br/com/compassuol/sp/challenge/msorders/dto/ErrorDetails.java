package br.com.compassuol.sp.challenge.msorders.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Schema(
        description = "The Error Details Model"
)
@Getter
@Setter
@AllArgsConstructor
public class ErrorDetails {
    @Schema(
            description = "Timestamp"
    )
    private Date timestamp;
    @Schema(
        description = "Error Details Message"
)
private String message;
    @Schema(
            description = "More details about error"
    )
    private String details;

}
