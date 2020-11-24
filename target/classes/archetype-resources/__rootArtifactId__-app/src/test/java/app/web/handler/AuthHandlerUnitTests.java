#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.app.web.handler;


import static org.mockito.Mockito.when;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import ${package}.app.UserRouterFunctionConfig;
import ${package}.app.configuration.ModelMapperConfig;
import ${package}.app.security.AuthenticationManager;
import ${package}.app.security.BCryptPasswordConfig;
import ${package}.app.security.SecurityConfig;
import ${package}.app.security.SecurityContextRepository;
import ${package}.app.security.TokenProvider;
import ${package}.app.services.UserService;
import ${package}.app.services.UserServiceImpl;
import ${package}.app.utils.BaseApiConstants;
import ${package}.app.web.dto.LoginRequestDTO;
import ${package}.app.web.dto.LoginResponseDTO;
import ${package}.app.web.dto.UserDTO;

import reactor.core.publisher.Mono;

/**
 * Tests Unitarios para el Functional Endpoint de autenticación
 * @author oscar.martinezblanco
 *
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserRouterFunctionConfig.class, AuthHandler.class, UserHandler.class})
@Import({UserServiceImpl.class, ModelMapperConfig.class, SecurityConfig.class, AuthenticationManager.class,
    TokenProvider.class, SecurityContextRepository.class, BCryptPasswordConfig.class})
@WebFluxTest
public class AuthHandlerUnitTests {

    @Autowired
    private ApplicationContext context;

    @MockBean
    private BCryptPasswordEncoder passwordEncoder;

    @MockBean
    private TokenProvider tokenProvider;
    
    @MockBean
    private UserService userService;

    private WebTestClient client;
    
    @BeforeEach
    public void setUp() {
        client = WebTestClient.bindToApplicationContext(context).build();
    }
    
    @Test
    public void findAllTest() throws Exception {
        
        //given:
        UserDTO userDto = new UserDTO(1L, "John", "Doe", "john@mail.com", new Date(), "${symbol_dollar}2a${symbol_dollar}10${symbol_dollar}vUE9JNc3ZflWL6u4HFH2kOEHWgNIahyAxoUoaZ1g0rsHJ3y9kzhwy");
        LoginRequestDTO login = new LoginRequestDTO();
        login.setEmail("john@mail.com");
        login.setPassword("1234");
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzY29wZXMiOlsiUk9MRV9BRE1JTiJdLCJzdWIiOiJqb2huQG1haWwuY29tIiwiaWF0IjoxNjA2MDUwMjY5LCJleHAiOjE2MDYwNjgyNjl9._O7IcTF4qleDGW3A-QapwX8keRUayMvm6UecHJoCnOA";
        
        //when:
        when(userService.findByEmail(login.getEmail())).thenReturn(Mono.just(userDto));
        when(passwordEncoder.matches(login.getPassword(), userDto.getPassword())).thenReturn(Boolean.TRUE);
        when(tokenProvider.generateToken(userDto)).thenReturn(token);
        
        //then:
        client.post()
        .uri(BaseApiConstants.AUTH_URL_V1)
        .accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(login), LoginRequestDTO.class)
        .exchange().expectStatus()
        .isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON)
        .expectBody(LoginResponseDTO.class)
        .consumeWith(response -> {
            LoginResponseDTO tokenResponse = response.getResponseBody();
           
            Assertions.assertThat(tokenResponse.getToken()).isNotNull();
            Assertions.assertThat(tokenResponse.getToken()).isNotEmpty();
            Assertions.assertThat(tokenResponse.getToken().length() == 173).isTrue();
            Assertions.assertThat(tokenResponse.getToken()).isEqualTo(token);
        });
    }
}
