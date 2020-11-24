#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.app.model.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import ${package}.app.model.entity.User;

/**
 * Repositorio de Usuarios
 * @author oscar.martinezblanco
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Búsqueda filtrada, paginada y ordenada
     * @param specification Especificación de búsqueda
     * @param pageable Paginación
     * @return Página de usuarios
     */
    Page<User> findAll(Specification<User> specification, Pageable pageable);
    
    /**
     * Busca un usuario por email
     * @param email email
     * @return Usuario
     */
    Optional<User> findByEmail(String email);
}
