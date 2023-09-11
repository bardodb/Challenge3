package br.com.compassuol.sp.challenge.msuser.controller;

import br.com.compassuol.sp.challenge.msuser.dto.UserDTO;
import br.com.compassuol.sp.challenge.msuser.entity.User;
import br.com.compassuol.sp.challenge.msuser.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Tag(
        name = "User Service - UserController",
        description = "UserController exposes REST APIs for User Service"
)
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Operation(
            summary = "Create User",
            description = "Create User is used to save an user into the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO){
        String message = userService.createUser(userDTO);

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    @Operation(
            summary = "Get User",
            description = "Get User is used to get an user by username or email from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{usernameOrEmail}")
    public Optional<User> returnUser(@PathVariable String usernameOrEmail){
        return userService.getUserByUsernameOrEmail(usernameOrEmail);
    }






}
