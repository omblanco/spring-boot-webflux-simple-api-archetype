# Spring Boot Webflux Simple Api Archetype

## Descripción
Arquetipo para la generación de una api rest básica basada en la aplicación [Spring Boot Webflux Simple Api 1.5.0](https://github.com/omblanco/spring-boot-webflux-simple-api)


## Ejemplo de uso

``` 
mvn install
mvn archetype:generate -DarchetypeCatalog=local
``` 

``` 
C:\Users\windows.user\Documents>mvn archetype:generate -DarchetypeCatalog=local
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------< org.apache.maven:standalone-pom >-------------------
[INFO] Building Maven Stub Project (No POM) 1
[INFO] --------------------------------[ pom ]---------------------------------
[INFO]
[INFO] >>> maven-archetype-plugin:3.2.0:generate (default-cli) > generate-sources @ standalone-pom >>>
[INFO]
[INFO] <<< maven-archetype-plugin:3.2.0:generate (default-cli) < generate-sources @ standalone-pom <<<
[INFO]
[INFO]
[INFO] --- maven-archetype-plugin:3.2.0:generate (default-cli) @ standalone-pom ---
[INFO] Generating project in Interactive mode
[INFO] No archetype defined. Using maven-archetype-quickstart (org.apache.maven.archetypes:maven-archetype-quickstart:1.0)
Choose archetype:
1: local -> other local archetypes
2: local -> com.omblanco.springboot.webflux.api:spring-boot-webflux-simple-api-archetype (Aplicaci¾n bßsica para una api rest con WebFlux)
Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): : 2
Define value for property 'groupId': com.foo.api
Define value for property 'artifactId': foo-api
Define value for property 'version' 1.0-SNAPSHOT: : 1.0.0-SNAPSHOT
Define value for property 'package' com.foo.api: : com.foo.api
Confirm properties configuration:
groupId: com.foo.api
artifactId: foo-api
version: 1.0.0-SNAPSHOT
package: com.foo.api
 Y: : y
[INFO] ----------------------------------------------------------------------------
[INFO] Using following parameters for creating project from Archetype: spring-boot-webflux-simple-api-archetype:1.4.0
[INFO] ----------------------------------------------------------------------------
[INFO] Parameter: groupId, Value: com.foo.api
[INFO] Parameter: artifactId, Value: foo-api
[INFO] Parameter: version, Value: 1.0.0-SNAPSHOT
[INFO] Parameter: package, Value: com.foo.api
[INFO] Parameter: packageInPathFormat, Value: com/foo/api
[INFO] Parameter: package, Value: com.foo.api
[INFO] Parameter: groupId, Value: com.foo.api
[INFO] Parameter: artifactId, Value: foo-api
[INFO] Parameter: version, Value: 1.0.0-SNAPSHOT
[INFO] Parent element not overwritten in C:\Users\windows.user\Documents\foo-api\foo-api-app\pom.xml
[INFO] Parent element not overwritten in C:\Users\windows.user\Documents\foo-api\foo-api-commons\pom.xml
[INFO] Project created from Archetype in dir: C:\Users\windows.user\Documents\foo-api
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  50.346 s
[INFO] Finished at: 2020-11-24T23:17:42+01:00
[INFO] ------------------------------------------------------------------------
``` 
Ejecución de la aplicación MySQL
``` 
cd foo-api-app
cd target
java -jar foo-api-app-1.0.0-SNAPSHOT.jar
-- Carga de algún profile
java -jar -Dspring.profiles.active=profiling foo-api-app-1.0.0-SNAPSHOT.jar
``` 

Ejecución de la aplicación Mongo
``` 
cd foo-api-mongo-app
cd target
java -jar foo-api-mongo-app-1.0.0-SNAPSHOT.jar
``` 
#### Observaciones
Si al instalar una nueva versión del arquetipo no aparece al ejecutar el comando generate se debe actualizar el .m2 local ejecutando:
``` 
mvn archetype:crawl
``` 
