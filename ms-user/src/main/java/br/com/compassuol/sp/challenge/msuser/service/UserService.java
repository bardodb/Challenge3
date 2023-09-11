package br.com.compassuol.sp.challenge.msuser.service;

import br.com.compassuol.sp.challenge.msuser.dto.LoginDTO;
import br.com.compassuol.sp.challenge.msuser.dto.UserDTO;
import br.com.compassuol.sp.challenge.msuser.entity.User;

import java.util.Optional;

public interface UserService {
    String createUser(UserDTO userDTO);
    String login(LoginDTO loginDTO);

    Optional<User> getUserByUsernameOrEmail(String usernameOrEmail);
}
