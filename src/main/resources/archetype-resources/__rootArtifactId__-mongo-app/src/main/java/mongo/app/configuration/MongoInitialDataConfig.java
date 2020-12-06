#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.mongo.app.configuration;

import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import ${package}.mongo.app.model.entity.User;
import ${package}.mongo.app.model.repositories.UserRepository;

import de.flapdoodle.embed.mongo.config.IMongoCmdOptions;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongoCmdOptionsBuilder;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import reactor.core.publisher.Flux;

/**
 * Carga los datos iniciales de usuarios para el profile de 
 * pruebas
 * see https://docs.spring.io/spring-data/mongodb/docs/2.0.9.RELEASE/reference/html/${symbol_pound}core.repository-populators
 * see https://jira.spring.io/browse/DATACMNS-1133
 * see https://stackoverflow.com/questions/47678465/how-can-you-load-initial-data-in-mongodb-through-spring-boot
 * see Jackson2RepositoryPopulatorFactoryBean
 * 
 * EmbeddedMongoAutoConfiguration
 * see https://stackoverflow.com/questions/51499482/configuring-flapdoodle-embedded-mongo-with-mongodb-version-4-and-replica
 * see https://stackoverflow.com/questions/52604062/how-to-disable-flapdoodle-embedded-mongodb-in-certain-tests
 * @author oscar.martinezblanco
 *
 */
@Profile("!dev & !stage & !pro")
@Configuration
public class MongoInitialDataConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoInitialDataConfig.class);
    
    @Bean
    public IMongodConfig prepareMongodConfig() throws IOException {
        IMongoCmdOptions cmdOptions = new MongoCmdOptionsBuilder()
                .useNoPrealloc(false)
                .useSmallFiles(false)
                .master(false)
                .verbose(false)
                .useNoJournal(false)
                .syncDelay(0)
                .build();

        IMongodConfig mongoConfigConfig = new MongodConfigBuilder()
                .version(Version.Main.DEVELOPMENT)
                .net(new Net(12345, Network.localhostIsIPv6()))
                .configServer(false)
                .cmdOptions(cmdOptions)
                .build();
        return mongoConfigConfig;
    }    
    
    @Bean
    public ApplicationRunner loadInitalData(UserRepository userRepository) {
        return applicationRunner -> {
            // Limpiamos si hay usuarios
            userRepository.deleteAll().subscribe();
            
            User user1 = new User(null, "John", "Doe", "john@mail.com", new Date(), "${symbol_dollar}2a${symbol_dollar}10${symbol_dollar}vUE9JNc3ZflWL6u4HFH2kOEHWgNIahyAxoUoaZ1g0rsHJ3y9kzhwy");
            User user2 = new User(null, "Oscar", "Suarez", "oscar@mail.com", new Date(), "${symbol_dollar}2a${symbol_dollar}10${symbol_dollar}vUE9JNc3ZflWL6u4HFH2kOEHWgNIahyAxoUoaZ1g0rsHJ3y9kzhwy");
            User user3 = new User(null, "Maria", "Salgado", "salgado@mail.com", new Date(), "${symbol_dollar}2a${symbol_dollar}10${symbol_dollar}vUE9JNc3ZflWL6u4HFH2kOEHWgNIahyAxoUoaZ1g0rsHJ3y9kzhwy");
            User user4 = new User(null, "Manuel", "Lopez", "manuel@mail.com", new Date(), "${symbol_dollar}2a${symbol_dollar}10${symbol_dollar}vUE9JNc3ZflWL6u4HFH2kOEHWgNIahyAxoUoaZ1g0rsHJ3y9kzhwy");
            
            Flux.just(user1, user2, user3, user4)
                .flatMap(userRepository::save)
                .subscribe(user -> LOGGER.info("Usuario insertado: " + user));

        };
    }
}
