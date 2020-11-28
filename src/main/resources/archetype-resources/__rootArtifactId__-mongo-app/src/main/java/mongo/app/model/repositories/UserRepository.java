#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.mongo.app.model.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import ${package}.mongo.app.model.entity.User;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String>{
    
    /**
     * Busca un usuario por email
     * @param email email
     * @return Usuario
     */
    Mono<User> findByEmail(String email);
}
