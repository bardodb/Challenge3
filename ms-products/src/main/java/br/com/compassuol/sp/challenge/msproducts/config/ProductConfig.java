package br.com.compassuol.sp.challenge.msproducts.config;

import br.com.compassuol.sp.challenge.msproducts.filter.AuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

@EnableWebSecurity
@Configuration
public class ProductConfig {
    private AuthenticationFilter authenticationFilter;

    public ProductConfig(AuthenticationFilter authenticationFilter){
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public RestTemplate template(){
        return new RestTemplate();
    }

    @Bean
    public DefaultSecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .httpBasic().disable()
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


}
