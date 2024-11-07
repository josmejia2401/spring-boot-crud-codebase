# CodeBase API Documentation

## Contexto

Esta es una versión de código base para iniciar una aplicación con spring boot 3.x (3.3.5) y base de datos PostgresQL.

## Tabla de Contenidos

- [Características de la API](#características-de-la-api)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Modelo Relacional](#modelo-relacional)
- [Endpoints de la API](#endpoints-de-la-api)
- [Control de Concurrencia](#control-de-concurrencia)
- [Pruebas](#pruebas)
- [Despliegue en la Nube](#despliegue-en-la-nube)
- [Instrucciones para Ejecutar](#instrucciones-para-ejecutar)
- [Repositorio](#repositorio)

## Características de la API

- **CRUD**: Permite realizar un CRUD completo de una funcionalidad.

## Tecnologías Utilizadas

- **Backend**: JAVA con Spring Boot 3.3.5
- **Base de Datos**: PostgreSQL
- **Infraestructura en la Nube**: RENDER
- **Orquestación**: Docker

## Modelo Relacional

La base de datos está estructurada en varias tablas:

- **Usuario**: Información sobre los usuarios (administradores y clientes).

## Endpoints de la API

En los siguientes EndPoints se define la documentación de los recursos web expuestos para resolver la iniciativa o prueba técnica:

- http://localhost:8080/codebase/swagger-ui/index.html
- http://localhost:8080/codebase/api-docs

### 1. Listar Elementos

**GET** `/`

**Parámetros de consulta**:
- `fechaCreacion`
- `role`

### 2. Consultar por ID

**GET** `/{id}`

**Respuesta**:
- ID del elemento
- Fecha de creación

### 3. Crear un elemento

**POST** `/`

**Cuerpo de la solicitud**:
```json
{
    "dni": "12345678",
    "nombre": "Nombre del Espectador"
}
```

**Respuesta**: Información salvada o creada en BD.

## Control de Concurrencia

Si un elemento ya fue creado, la API devolverá un error de conflicto (`HTTP 409`).

## Pruebas

Para asegurar la calidad del código, se recomienda usar:

- **Pruebas Unitarias**: Mockito

## Despliegue en la Nube

La API debe ser desplegada utilizando contenedores Docker en render con una capa gratuita.

## Instrucciones para Ejecutar

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/josmejia2401/spring-boot-crud-codebase.git
   cd spring-boot-meli
   ```

2. Instalar dependencias:
   ```bash
   mvn clean install
   ```

3. Configurar variables de entorno:
- Crear un archivo `.env` basado en el `.env.example`.

4. Iniciar la aplicación con JAR:
   ```bash
   java -jar target/spring-boot-crud-codebase.jar
   ```

5. Iniciar la aplicación con Docker Compose:
   ```bash
   docker-compose -f docker-compose.yml --compatibility up
   ```

6. Iniciar la aplicación con Docker:
   ```bash
   docker build --no-cache -t spring-boot-crud-codebase:latest . 
   docker run -it spring-boot-crud-codebase:latest -p 127.0.0.1:8080:8080
   ```

7. Ejecutar pruebas:
   ```bash
   mvn -Dtest=SeosSdkAutomationApplicationTests test
   mvn exec:java -Dexec.mainClass="com.josmejia2401.Application" -Dexec.classpathScope=test
   ```

## Repositorio

El código fuente y la documentación adicional se pueden encontrar en el siguiente enlace:

[Repositorio](https://github.com/josmejia2401/spring-boot-crud-codebase.git)

---


