package br.com.compassuol.sp.challenge.msproducts.filter;

import br.com.compassuol.sp.challenge.msproducts.exception.ProductException;
import br.com.compassuol.sp.challenge.msproducts.utils.JwtUtil;
import br.com.compassuol.sp.challenge.msproducts.utils.RouteValidator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    private RouteValidator validator;

    public AuthenticationFilter(JwtUtil jwtUtil, RouteValidator validator) {
        this.jwtUtil = jwtUtil;
        this.validator = validator;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        boolean condition =
                Objects.equals(HttpMethod.POST.toString(), request.getMethod()) ||
                        Objects.equals(HttpMethod.PUT.toString(), request.getMethod()) ||
                        Objects.equals(HttpMethod.DELETE.toString(), request.getMethod());

        if (validator.isSecured.test(request) && (condition)) {
            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new ProductException(HttpStatus.UNAUTHORIZED, "You are not authorized to use this part of the app");
            }

            String token = authHeader.substring(7);

            try {
                jwtUtil.validateToken(token);
            } catch (Exception e) {
                throw new ProductException(HttpStatus.UNAUTHORIZED, "You are not authorized to use this part of the app");
            }
        }

        chain.doFilter(request, response);
    }


}
