package br.com.compassuol.sp.challenge.apigateway.filter;

import br.com.compassuol.sp.challenge.apigateway.exception.GatewayException;
import br.com.compassuol.sp.challenge.apigateway.utils.JwtUtil;
import br.com.compassuol.sp.challenge.apigateway.utils.RouteValidator;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private JwtUtil jwtUtil;
    private RouteValidator validator;

    public AuthenticationFilter(JwtUtil jwtUtil, RouteValidator validator) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
        this.validator = validator;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange,chain)->{
            ServerHttpRequest request = exchange.getRequest();
            HttpMethod httpMethod = request.getMethod();
            boolean condition = Objects.equals(HttpMethod.POST, httpMethod) ||
                    Objects.equals(HttpMethod.PUT, httpMethod) || Objects.equals(HttpMethod.DELETE, httpMethod);
            String registerRequest = "/users/register";

            if (validator.isSecured.test(exchange.getRequest()) && (condition) &&
                    Objects.deepEquals(exchange.getRequest().toString(), registerRequest) ){
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new GatewayException(HttpStatus.UNAUTHORIZED, "You are not authorize to use this part of app");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    jwtUtil.validateToken(authHeader);
                    request = exchange.getRequest()
                            .mutate()
                            .build();

                } catch (Exception e) {
                    throw new GatewayException(HttpStatus.UNAUTHORIZED, "You are not authorize to use this part of app");
                }

            }
            return chain.filter(exchange.mutate().request(request).build());
        });
    }

    public static class Config{

    }
}
