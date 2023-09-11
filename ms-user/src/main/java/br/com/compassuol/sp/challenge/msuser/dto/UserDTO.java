package br.com.compassuol.sp.challenge.msuser.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "Create User Model"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @Schema(
            description = "User Name"
    )
    private String name;
    @Schema(
            description = "User Username"
    )
    private String username;
    @Schema(
            description = "User Email"
    )
    private String email;
    @Schema(
            description = "User Password"
    )
    private String password;
}
