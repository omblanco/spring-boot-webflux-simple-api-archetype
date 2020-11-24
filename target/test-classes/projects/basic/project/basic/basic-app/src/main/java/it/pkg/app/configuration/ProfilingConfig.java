package it.pkg.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import it.pkg.app.aop.LoggingAspect;
import it.pkg.app.aop.ProfilingAspect;


/**
 * Configuración para el traceo AOP
 */
@Profile("profiling")
@Configuration
public class ProfilingConfig {

    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
    
    @Bean
    public ProfilingAspect profilingAspect() {
        return new ProfilingAspect();
    }
    
}
