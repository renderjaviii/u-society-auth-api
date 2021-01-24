# USociety - Authentication API
OAUTH2 REST API created to the USociety project.

This RESTful microservice has the function of the Authorization server, it’s the authentication manager that you have to consume to authenticate a user into the system and get the token that allows the successful interaction with all the APIs.

#### How to run locally

1. Recreate a local database: `docker-compose -f src/main/resources/docker-db.yml up --build`
2. Compile Spring project and generate jar file: `mvn clean install`
3. Build local docker image: `docker build -t u-society/authentication .`
4. Run image into a docker container: `docker-compose up --build -d`
5. Show container logs: `docker-compose logs -f`
6. Stop container: `docker-compose down -v`

#### Notes
- Base API path: `http://localhost:8075/users`
- Swagger documentation: `http://localhost:8075/manager/swagger-ui.html`.

#### Technologies used
- Spring boot.
- Docker.
- Docker compose.
- RDS (Amazon Relational Database Service).
- Maven manager dependency.
- MySQL.
- OAUTH2.
- Spring Security + JWT.
- Swagger UI.
- Apache tomcat.
- Model mapper.
- Jackson databind.
- Apache commons lang3.


#### Additional considerations
1. If you want to change the general server configurations (as port), you can change the environment variables from this file `src/main/resources/development.env`.
2. When you run the docker db container, the database is going to be populated using the seeders files `src/main/resources/data.sql` and `src/main/resources/import.sql` (If you want to avoid it remove the entry points localized in the .yaml file). 
