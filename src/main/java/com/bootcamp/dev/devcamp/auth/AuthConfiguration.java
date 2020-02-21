package com.bootcamp.dev.devcamp.auth;

import com.bootcamp.dev.devcamp.repository.AuthRepository;
import com.bootcamp.dev.devcamp.response.ClientError;
import com.bootcamp.dev.devcamp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Configuration
public class AuthConfiguration {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private GlobalSecurityContext globalSecurityContext;


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) -> {
                    return Mono.error(ClientError.unauthorized());
                }).accessDeniedHandler((swe, e) -> {
                    return Mono.error(ClientError.forbidden());
                }).and()
                .csrf().disable()
//                .httpBasic().and()
                .authenticationManager(authenticationManager)
                .securityContextRepository(globalSecurityContext)
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers("/api/profile/create", "/api/profile/login").permitAll()
                .anyExchange().authenticated()
                .and().build();
    }

    @Bean
    public AuthService authService(AuthRepository authRepository) {
        return new AuthService(authRepository);
    }
}
