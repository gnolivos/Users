
Este proyecto esta diseñado en Java 11 con Springboot.
Gradle 7.4.2 y como banco de datos en memoria H2 Database

También está implementado la herramienta Swagger para especificar la lista de métodos permitidos en el REST API

El script de la creación de las tablas se encuentra en la dirección: users/src/main/resources/data.sql

### Clonar proyecto
 > `Crear una carpeta, abrir la terminal y ejecutar: git clone https://github.com/gnolivos/Users.git`

### Ejecutar Gradle
 > `Abrir una terminal en la nueva carpeta (users) y ejecutar: gradle clean build`  

### ENDPOINTS

### GET: 	`http://localhost:8080/users`
 > `Servicio para obtener todos los usuarios creados
 
### POST: 	`http://localhost:8080/users`
 > `Servicio para crear un nuevo usuario
 
     {
        "name": "Juan Rodriguez",
        "email": "juan@rodriguez.org",
        "password": "Chanchito11@",
        "phones": [
            {
                "number": "1234567",
                "citycode": "1",
                "countrycode": "57"
            }
        ]   
    }
    
### PUT: 	`http://localhost:8080/users`
 > `Servicio para actualizar un usuario
 	 
   {
        "id": "2d08be78-3285-4abc-b378-3ac4940b137b",
        "name": "Juan Rodriguez",
        "email": "juan@rodriguez.org",
        "password": "Chanchito11@",
        "phones": [
            {
                "id": "35c9a523-d5e7-45c9-b4a9-cbf1823a739a",
                "number": "1234567",
                "citycode": "1",
                "countrycode": "57"
            }
        ]   
    }
    
### DELETE: 	`http://localhost:8080/users/{id}`
 > `Servicio para eliminar un usuario

### SWAGGER
 > `Para abrir la herramienta swagger nos dirigimos a la página: http://localhost:8080/swagger-ui.html`

### H2-CONSOLE
 > `Para abrir la pantalla de administración de la base de datos en memoria se ejecuta : http://localhost:8080/h2-console`
 > `Se coloca la contraseña: password, para poder acceder a las tablas creadas mediante un script`
