#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.mongo.app.web.handlers;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import ${package}.commons.annotation.loggable.Loggable;
import ${package}.commons.security.TokenProvider;
import ${package}.commons.web.dto.LoginRequestDTO;
import ${package}.commons.web.dto.LoginResponseDTO;
import ${package}.commons.web.handler.CommonHandler;
import ${package}.mongo.app.services.UserService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * Handler con el Functional Endpoint para la 
 * autenticación de usuarios.
 * @author oscar.martinezblanco
 *
 */
@Loggable
@AllArgsConstructor
@Component
public class AuthHandler extends CommonHandler {

    private BCryptPasswordEncoder passwordEncoder;

    private TokenProvider tokenProvider;

    private UserService userService;
    
    private Validator validator;
    
    private static final String VALIDATION_MESSAGE = "Validation failure";

    public Mono<ServerResponse> login(ServerRequest request) {
        
        Mono<LoginRequestDTO> loginRequest = request.bodyToMono(LoginRequestDTO.class);
        
        return loginRequest.flatMap(login -> {
            Errors errors = new BeanPropertyBindingResult(login, LoginRequestDTO.class.getName());
            
            validator.validate(login, errors);
            
            if(errors.hasErrors()) {
                return validationErrorsResponse(errors, VALIDATION_MESSAGE);
            } else {
                return userService.findByEmail(login.getEmail()).flatMap(user ->{
                    if (passwordEncoder.matches(login.getPassword(), user.getPassword())) {
                        return ServerResponse.ok()
                                .contentType(APPLICATION_JSON)
                                .body(BodyInserters.fromValue(new LoginResponseDTO(tokenProvider.generateToken(user.getEmail(), null))));
                    } else {
                        return ServerResponse.status(UNAUTHORIZED).build();
                    }
                });
            }
        }).switchIfEmpty(ServerResponse.status(UNAUTHORIZED).build());
    }
}
