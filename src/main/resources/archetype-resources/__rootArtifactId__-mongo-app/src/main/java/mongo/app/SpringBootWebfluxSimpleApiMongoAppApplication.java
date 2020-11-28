#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.mongo.app;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ${package}.mongo.app.model.entity.User;
import ${package}.mongo.app.model.repositories.UserRepository;

/**
 * Clase principal de configuración de Spring Boot
 * @author oscar.martinezblanco
 *
 */
@SpringBootApplication
public class SpringBootWebfluxSimpleApiMongoAppApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    
	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluxSimpleApiMongoAppApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
//        User user1 = new User(null, "John", "Doe", "john@mail.com", new Date(), "${symbol_dollar}2a${symbol_dollar}10${symbol_dollar}vUE9JNc3ZflWL6u4HFH2kOEHWgNIahyAxoUoaZ1g0rsHJ3y9kzhwy");
//        User user2 = new User(null, "Oscar", "Suarez", "oscar@mail.com", new Date(), "${symbol_dollar}2a${symbol_dollar}10${symbol_dollar}vUE9JNc3ZflWL6u4HFH2kOEHWgNIahyAxoUoaZ1g0rsHJ3y9kzhwy");
//        User user3 = new User(null, "Maria", "Salgado", "salgado@mail.com", new Date(), "${symbol_dollar}2a${symbol_dollar}10${symbol_dollar}vUE9JNc3ZflWL6u4HFH2kOEHWgNIahyAxoUoaZ1g0rsHJ3y9kzhwy");
//        User user4 = new User(null, "Manuel", "Lopez", "manuel@mail.com", new Date(), "${symbol_dollar}2a${symbol_dollar}10${symbol_dollar}vUE9JNc3ZflWL6u4HFH2kOEHWgNIahyAxoUoaZ1g0rsHJ3y9kzhwy");
//        
//        userRepository.save(user1).subscribe(user -> {
//            System.out.println("Se ha instado el usuario:" + user);
//        });
//        userRepository.save(user2);
//        userRepository.save(user3);
//        userRepository.save(user4);
        
    }

}
