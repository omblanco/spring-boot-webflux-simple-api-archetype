#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.mongo.app.model.repositories;

import org.springframework.data.domain.Pageable;

import ${package}.commons.web.dto.UserFilterDTO;
import ${package}.mongo.app.model.entity.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interfaza del respositorio extendido
 * @author oscar.martinezblanco
 *
 */
public interface CustomUserRepository {

    /**
     * Recupera un listado de usuario paginado y filtrado
     * @param filter Filtro
     * @param pageable Paginación
     * @return Lista de usuarios
     */
    Flux<User> findBy(UserFilterDTO filter, Pageable pageable);
    
    /**
     * Recupera el número de usuarios que cumplen las condiciones del filtro
     * @param filter Filtro
     * @return Número de resultados
     */
    Mono<Long> countBy(UserFilterDTO filter);
}
