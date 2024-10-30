API RESTful de Gestión de Usuarios
Descripción
Esta es una API RESTful de gestión de usuarios, desarrollada con Java 8 y Spring Boot. La API permite crear nuevos usuarios, validar la autenticación con un token UUID, y obtener la lista de usuarios registrados. La persistencia de datos se realiza en una base de datos en memoria H2. Todas request y response se manejan en formato JSON.

Requisitos
Java 8+
Maven
Spring Boot 2.5.4
H2 Database

Instalación
Clonar repo: git clone https://bitbucket.org/leventis-rsaav/desafiobci/pull-requests/new?source=feature/apirest&t=1

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
  "email": "juan@rodriguez.org",
  "password": "hunter2",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "contrycode": "57"
    }
  ]
}

Formato de Respuesta Exitosa:
{
    "code": 200,
    "id": "0045367f-494e-4efc-8cf2-308ecc74091e",
    "created": "09-09-2024",
    "modified": "09-09-2024",
    "last_login": "09-09-2024",
    "isactive": true,
    "token": "15a2fb31-60fd-42e8-b239-7dafe17118ab"
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
        "id": "408f134b-89de-42ff-ad3c-cee31d62e0c2",
        "name": "Juan Rodriguez",
        "email": "jose60@rodriguez.org",
        "password": "hUnter23",
        "created": "2024-09-09T01:17:22.122866",
        "modified": "2024-09-09T01:17:22.122866",
        "lastLogin": "2024-09-09T01:17:22.122866",
        "token": "54dbf149-24cc-4c50-885e-04de707b2f22",
        "active": true,
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
        ]
    },
    {
        "id": "2ce509a2-1e6e-4113-9359-cd6d448f031b",
        "name": "Juan Rodriguez",
        "email": "jose630@rodriguez.org",
        "password": "hUnter23",
        "created": "2024-09-09T01:17:26.288551",
        "modified": "2024-09-09T01:17:26.288551",
        "lastLogin": "2024-09-09T01:17:26.288551",
        "token": "d2fbe323-e0ef-4f1a-8ce1-a5cbf43e74c1",
        "active": true,
        "phones": [
            {
                "id": 3,
                "number": "222222",
                "cityCode": "1",
                "countryCode": "57",
                "user": null
            },
            {
                "id": 4,
                "number": "111111",
                "cityCode": "1",
                "countryCode": "57",
                "user": null
            }
        ]
    },
    {
        "id": "105e7b2f-eb43-4437-9e83-f3862f94ddf7",
        "name": "Juan Rodriguez",
        "email": "jose6340@rodriguez.org",
        "password": "hUnter23",
        "created": "2024-09-09T01:17:29.056943",
        "modified": "2024-09-09T01:17:29.056943",
        "lastLogin": "2024-09-09T01:17:29.056943",
        "token": "f351f205-1ea0-4c0e-93a1-f6346d19879f",
        "active": true,
        "phones": [
            {
                "id": 5,
                "number": "222222",
                "cityCode": "1",
                "countryCode": "57",
                "user": null
            },
            {
                "id": 6,
                "number": "111111",
                "cityCode": "1",
                "countryCode": "57",
                "user": null
            }
        ]
    }
]


Base de Datos
La base de datos H2 se configura automáticamente cuando se ejecuta la aplicación. Puedes acceder a la consola de H2 para inspeccionar los datos en:

URL: http://localhost:8080/h2
JDBC URL: jdbc:h2:mem:mydb
Usuario: sa
Contraseña: password


Collectio Postman 
test/DesafioBCI.postman_collection.json