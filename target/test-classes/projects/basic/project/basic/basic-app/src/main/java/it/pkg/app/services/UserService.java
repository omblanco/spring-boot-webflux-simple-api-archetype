package it.pkg.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.pkg.app.model.entity.User;
import it.pkg.app.web.dto.UserDTO;
import it.pkg.app.web.dto.UserFilterDTO;
import it.pkg.commons.services.CommonService;

import reactor.core.publisher.Mono;

/**
 * Interfaz del servicio de usuario
 * @author oscar.martinezblanco
 *
 */
public interface UserService extends CommonService<UserDTO, User>{

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
