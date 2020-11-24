#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.app.web.controllers;

import static ${package}.app.utils.BaseApiConstants.USER_BASE_URL_V1;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ${package}.app.model.entity.User;
import ${package}.app.services.UserService;
import ${package}.app.web.dto.UserDTO;
import ${package}.app.web.dto.UserFilterDTO;
import ${package}.commons.web.controllers.CommonController;

import lombok.Builder;
import reactor.core.CorePublisher;
import reactor.core.publisher.Mono;

/**
 * Controlador para los usuarios
 * 
 * @author oscar.martinezblanco
 *
 */
@Controller
@RequestMapping(USER_BASE_URL_V1)
public class UserController extends CommonController<UserDTO, User, UserService> {
    
    @Builder
    public UserController(UserService service) {
        super(service);
    }
    
    /**
     * Método que recupera Usuarios paginados
     * @param filter Filtro de búsqueda
     * @param pageable Paginación y ordenación
     * @return Página de usuarios
     */
    @GetMapping
    @ResponseBody
    public Mono<ResponseEntity<CorePublisher<?>>> findByFilter(UserFilterDTO filter,
            @SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Long size, Long page) {
        
        if (size == null & page == null) {
            return super.findAll();
        }
        
        return Mono.just(ResponseEntity.ok().contentType(APPLICATION_JSON).body(service.findByFilter(filter, pageable)));
    }

    @Override
    protected String getBaseUrl() {
        return USER_BASE_URL_V1;
    }

    @Override
    protected void updateDtoToSave(UserDTO requestDto, UserDTO dbDto) {
        dbDto.setBirthdate(requestDto.getBirthdate());
        dbDto.setEmail(requestDto.getEmail());
        dbDto.setName(requestDto.getName());
        dbDto.setSurname(requestDto.getSurname());
    }
}
