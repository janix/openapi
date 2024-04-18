# _OpenAPI demo Spring Boot project_ 

A simple Spring Boot project that demonstrates the use of OpenAPI annotations to document API endpoints.

## Configuration
To configure authentication, add the url of your authentication server to `application.yml`.  It should be of the format:

    https://<servername>/auth/realms/<realmname>/protocol/openid-connect

## Running the application

To start the application:

    mvn spring-boot:run

To view the generated swagger UI visit 

    http://localhost:8080/swagger-ui/index.html

## Authenticating

To authenticate, click the 'Authorize' button and choose on of the authentication schemas:

### Bearer_Token
If you wish to directly provide an access token generated in the configured realm, you can paste it here

### Service_Account
If you wish to authenticate with a confidential client using client credentials (client_id and secret), enter them here

### User_Login
If you wish to enter user credentials in a login prompt to authenticate, add the client_id of a public client here. A secret is not required.
Note that your public client must be configured to allow [http://localhost:8080/*](http://localhost:8080) as a valid redirect uri

## Testing
Once authenticated, click the _Try it out_ button next to any API definition, provide any required parameters and click _Execute_ to see the results of your call

## Client SDK

### Generation
To generate a client SDK project for the API, run

    mvn verify

This will generate an openapi json spec in the folder `doc/openapi` and a client SDK project in the `client` folder

### Deployment

In order to use the SDK for development, it can be deployed to a configured maven repository.
The project generated in the `client` folder is a complete maven project - simply run its `deploy` target:
    
    mvn deploy

