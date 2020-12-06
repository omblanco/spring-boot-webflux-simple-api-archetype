#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.mongo.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ${package}.commons.services.CommonService;
import ${package}.commons.web.dto.UserFilterDTO;
import ${package}.mongo.app.model.entity.User;
import ${package}.mongo.app.web.dtos.UserDTO;

import reactor.core.publisher.Mono;

public interface UserService extends CommonService<UserDTO, User, String>{

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
