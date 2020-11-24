package it.pkg.app.web.handler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import it.pkg.app.security.TokenProvider;
import it.pkg.app.services.UserService;
import it.pkg.app.web.dto.LoginRequestDTO;
import it.pkg.app.web.dto.LoginResponseDTO;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * Handler con el Functional Endpoint para la 
 * autenticación de usuarios.
 * @author oscar.martinezblanco
 *
 */
@AllArgsConstructor
@Component
public class AuthHandler {

    private BCryptPasswordEncoder passwordEncoder;

    private TokenProvider tokenProvider;

    private UserService userService;

    public Mono<ServerResponse> login(ServerRequest request) {
        
        Mono<LoginRequestDTO> loginRequest = request.bodyToMono(LoginRequestDTO.class);
        
        return loginRequest.flatMap(login -> userService.findByEmail(login.getEmail())
            .flatMap(user -> {
                if (passwordEncoder.matches(login.getPassword(), user.getPassword())) {
                    return ServerResponse.ok()
                            .contentType(APPLICATION_JSON)
                            .body(BodyInserters.fromValue(new LoginResponseDTO(tokenProvider.generateToken(user))));
                } else {
                    return ServerResponse.status(UNAUTHORIZED).build();
                }
            }).switchIfEmpty(ServerResponse.status(UNAUTHORIZED).build()));
        
    }
}
