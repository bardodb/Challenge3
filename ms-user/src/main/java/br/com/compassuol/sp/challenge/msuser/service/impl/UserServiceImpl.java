package br.com.compassuol.sp.challenge.msuser.service.impl;

import br.com.compassuol.sp.challenge.msuser.dto.LoginDTO;
import br.com.compassuol.sp.challenge.msuser.dto.UserDTO;
import br.com.compassuol.sp.challenge.msuser.entity.Role;
import br.com.compassuol.sp.challenge.msuser.entity.User;
import br.com.compassuol.sp.challenge.msuser.exception.UserAPIException;
import br.com.compassuol.sp.challenge.msuser.repository.RoleRepository;
import br.com.compassuol.sp.challenge.msuser.repository.UserRepository;
import br.com.compassuol.sp.challenge.msuser.security.JwtTokenProvider;
import br.com.compassuol.sp.challenge.msuser.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String createUser(UserDTO userDTO) {
        if(userRepository.existsByUsername(userDTO.getUsername())){
            throw new UserAPIException(HttpStatus.BAD_REQUEST, "Username is already exists");
        }

        if(userRepository.existsByEmail(userDTO.getEmail())){
            throw new UserAPIException(HttpStatus.BAD_REQUEST, "Email is already exists");
        }
        User user = new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return "User registered";
    }



    @Override
    public String login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(),
                loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }

    @Override
    public Optional<User> getUserByUsernameOrEmail(String usernameOrEmail) {
        return userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail);
    }


}
