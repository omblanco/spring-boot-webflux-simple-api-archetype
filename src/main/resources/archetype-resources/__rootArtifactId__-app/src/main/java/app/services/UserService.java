#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ${package}.app.model.entity.User;
import ${package}.app.web.dto.UserDTO;
import ${package}.commons.services.CommonService;
import ${package}.commons.web.dto.UserFilterDTO;

import reactor.core.publisher.Mono;

/**
 * Interfaz del servicio de usuario
 * @author oscar.martinezblanco
 *
 */
public interface UserService extends CommonService<UserDTO, User, Long>{

    /**
     * Recupera usuarios paginados y filtrados
     * @param filter Filtro de búsqueda
     * @param pageable Paginación
     * @return Mono de página de usuarios
     */
    Mono<Page<UserDTO>> findByFilter(UserFilterDTO filter, Pageable pageable);
    
    /**
     * Busca un usuario por email
     * @param email Email
     * @return Usuario
     */
    Mono<UserDTO> findByEmail(String email);
}
