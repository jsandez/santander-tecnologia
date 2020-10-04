### SANTANDER TECNOLOGÍA

Challenge requerido por Santander Tecnología acerca de una api que controla, modifica y genera meetups relacionada con la cantidad de cervezas que habría que comprar basándose en la temperatura de dicho día.

#### Tecnologias usadas

Para construir la API Rest usamos Spring Boot y java 8.

Para la seguridad de la aplicación utilizamos el módulo de Spring Security.

Se uso JWT token para manejar la sesión de cada request.

La base de datos que se uso es H2, la cual tambien es controlada por la esta aplicación. Cuando se inicia la misma, esta deploya un módulo h2-console donde nos podremos conectar a través del browser para verificar las tablas y su contenido.

Para consultar el clima usamos la api https://rapidapi.com/community/api/open-weather-map.

#### Datos de la aplicación

Algunas cosas a tener en cuenta:

* Para iniciar la aplicación debemos correr los siguientes dos comandos:
  * Para compilar:
 
  ```sh
  mvn clean install
  ```
   * Para iniciar la aplicación:
   ```sh
     mvn spring-boot:run
     ```
* Nos podremos conectar a la base de datos en http://localhost:8080/h2-console. El usuario admin de la base de datos es *santander* y la contraseña es *tecnologia*
* Tenemos que usar el endpoint de /authenticate para obtener el jwt token. Luego debemos pasarlo a cada request en un header Authorization. El usuario que ya esta creado y es el admin principal de la aplicacion es *santander-admin* y la contraseña es *tecnologia*.
El header debe ser pasado e la siguienter forma

`Bearer <jwt token devuelto por el endpoint>`

#### Algunas cosas a considerar
Dado que la API de clima es paga para obtener el clima de 16 o 30 días, usamos el endpoint de solo 5 dias para este challenge. Si armarmos una meetup mayor a hoy + 5 dias en adelante, nos devolvera temperatura 0 por default.





