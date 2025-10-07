Create a toy web service which will expose 2 http rest endpoints and persist the data in a database.
Todo:

    Create a new springboot kotlin project using graddle as a package manager with the spring initializer.

    Using *spring-boot-starter-web* create a controller and document the endpoints for a Product entity. Actions

        POST /product

        GET /product/ {id}

        DELETE /product/{id}

    Using spring-boot-starter-security secure your endpoint with basic auth. Store the credentials in a properties file.

    Using springfox-boot-starter:3.0.0 generate the API documentation and enable swaggerui visualization.

    Using org.springframework.boot:spring-boot-starter-data-jpa create a Product entity and setup database persistence.

        Use the H2 Database for simplicity.

        All actions should be done in a @Service class.

        The controller should have minum responsability.

        Use a repository to retrieve the entity from the databases.# web_service
