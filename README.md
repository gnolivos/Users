
### INFORMACIÓN GENERAL

Este proyecto esta diseñado como proyecto maven en Java 11 con Springboot 2.7.3.

Utiliza como banco de datos en memoria H2 Database.

Está implementado la herramienta Swagger para especificar la lista de métodos permitidos en el REST API

El script de la creación de las tablas se encuentra en la dirección: users/src/main/resources/data.sql

Se utilizar JWT como token de usuario y UUID como identificador único de usuario.

### CLONAR PROYECTO
 > `Crear una carpeta, abrir la terminal y ejecutar: git clone https://github.com/gnolivos/Users.git` 
 
### EJECUTAR PROYECTO
 > `Crear una carpeta workspace.` 

 > `Abrir un IDE y apuntar a la carpeta workspace.`

 > `Importar el proyeto maven recién clonado.`

 > `Click derecho en el proyecto, seleccionar Maven y seleccionar Upadate Project.`
 
 > `Ejecutar el proyecto como Spring Boot App`
 
 
### SWAGGER
 > `Para utilizar los servicios, podemos acceder a la url: http://localhost:8080/swagger-ui.html`

### H2 DATABASE
 > `Para abrir la pantalla de administración de la base de datos en memoria se ejecuta : http://localhost:8080/h2-console`
 > `Se coloca la contraseña: password, para poder acceder a las tablas creadas mediante el script mencionado anteriormente`

### ENDPOINTS

### GET: 	`http://localhost:8080/users`
 > `Servicio para obtener todos los usuarios creados`
 
### POST: 	`http://localhost:8080/users`
 > `Servicio para crear un nuevo usuario`
 
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
 > `Servicio para actualizar un usuario`
 
     {
	    "id":  "890f9114-f1bf-49b8-bcba-da4e3c1d0e39",
        "name": "Juan Rodriguez",
        "email": "juan@rodriguez.org",
        "password": "Chanchito11@",
        "phones": [
            {
		        "id":  "35c9a523-d5e7-45c9-b4a9-cbf1823a739a",
                "number": "1234567",
                "citycode": "1",
                "countrycode": "57"
            }
        ]   
    }
    
### DELETE: 	`http://localhost:8080/users/{id}`
 > `Servicio para eliminar un usuario`

