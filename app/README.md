API RESTful de Gestión de Usuarios
Descripción
Esta es una API RESTful de gestión de usuarios, desarrollada con Java 8 y Spring Boot. La API permite crear nuevos usuarios, validar la autenticación con un token UUID, y obtener la lista de usuarios registrados. La persistencia de datos se realiza en una base de datos en memoria H2. Todas request y response se manejan en formato JSON.

Requisitos
Java 8+
Maven
Spring Boot 2.5.4
H2 Database

Instalación
Clonar repo: git clone https://github.com/rsaavedrav/evaluacion-java.git

Compila y ejecuta el proyecto con Maven:
mvn clean install
mvn spring-boot:run


La aplicación estará disponible en http://localhost:8080/api/person

Endpoints
1. Registro de Usuarios
URL:/create

Método: POST

Descripción: Crea un nuevo usuario en el sistema.

Formato de Entrada:
{
    "name": "Juan Rodriguez",
    "email": "jose6340@rodriguez.org",
    "password": "hUnter23",
    "phones": [
        {
            "number": "222222",
            "cityCode": "1",
            "countryCode": "57"
        },
        {
            "number": "111111",
            "cityCode": "1",
            "countryCode": "57"
        }
    ]
}

Formato de Respuesta Exitosa:
{
    "code": 200,
    "id": "5c0ff5a9-77f0-42aa-9a4c-8cf39c86023f",
    "created": "04-11-2024",
    "modified": "04-11-2024",
    "last_login": "04-11-2024",
    "isactive": true,
    "token": "4590730c-966e-4960-bf8d-ec8f46cfc3b6"
}

Errores Comunes:

400 Bad Request: "El correo ya registrado"
400 Bad Request: "Formato de correo inválido"
400 Bad Request: "Formato de contraseña inválido"

2. Obtener Todos los Usuarios
URL: /findAll

Método: GET

Descripción: Obtiene la lista de todos los usuarios registrados en el sistema.

Formato de Respuesta Exitosa:

[
    {
        "id": "5c0ff5a9-77f0-42aa-9a4c-8cf39c86023f",
        "name": "Juan Rodriguez",
        "email": "jose6340@rodriguez.org",
        "password": "hUnter23",
        "created": "2024-11-04T13:02:37.797221",
        "modified": "2024-11-04T13:02:37.797221",
        "lastLogin": "2024-11-04T13:02:37.797221",
        "token": "4590730c-966e-4960-bf8d-ec8f46cfc3b6",
        "active": true,
        "accountNoExpired": false,
        "accountNoLocked": false,
        "credentialNoExpired": false,
        "phones": [
            {
                "id": 1,
                "number": "222222",
                "cityCode": "1",
                "countryCode": "57",
                "user": null
            },
            {
                "id": 2,
                "number": "111111",
                "cityCode": "1",
                "countryCode": "57",
                "user": null
            }
        ],
        "enabled": false
    }
]

Base de Datos
La base de datos H2 se configura automáticamente cuando se ejecuta la aplicación. Puedes acceder a la consola de H2 para inspeccionar los datos en:

URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:mydb
Usuario: sa
Contraseña: 


Collectio Postman 
test/DesafioBCI.postman_collection.json