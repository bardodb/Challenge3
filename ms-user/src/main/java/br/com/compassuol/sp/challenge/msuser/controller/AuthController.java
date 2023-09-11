package br.com.compassuol.sp.challenge.msuser.controller;

import br.com.compassuol.sp.challenge.msuser.dto.JwtAuthResponse;
import br.com.compassuol.sp.challenge.msuser.dto.LoginDTO;
import br.com.compassuol.sp.challenge.msuser.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(
        name = "User Service - AuthController",
        description = "AuthController exposes REST APIs for User Service"
)
@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class AuthController {
    private UserService userService;

    @Operation(
            summary = "Login User",
            description = "Login User is used to make login into the app and make some request " +
                    "which requires authorization"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @PostMapping
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDTO loginDTO){
        String token = userService.login(loginDTO);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }
}
