package com.bootcamp.dev.devcamp.auth;

import com.bootcamp.dev.devcamp.response.ClientError;
import com.bootcamp.dev.devcamp.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Set;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Autowired
    private AuthService authService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();

        return authService.profileExists(authToken).map(result -> {
            Set<GrantedAuthority> authoritySet = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
            return (Authentication)new UsernamePasswordAuthenticationToken(result.getEmailId(),
                    null, authoritySet);

        }).onErrorMap(error -> {return ClientError.unauthorized();}
        ).switchIfEmpty(Mono.error(ClientError.unauthorized()
        ));
    }
}

