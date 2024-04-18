package ie.janix.whitepapers.openapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Add security configuration for swagger-ui
 */
@Configuration
public class OpenAPIConfig {

  // Location of auth server
  // Default localhost:8081 can be overridden in application.yml
  @Value("${openapi.tokenUrl}")
  String tokenUrl = "http://127.0.0.1:8081/auth/realms/Test/protocol/openid-connect/token";
  @Value("${openapi.authUrl}")
  String authorizationUrl = "http://127.0.0.1:8081/auth/realms/Test/protocol/openid-connect/auth";

  @Bean
  public OpenAPI customizeOpenAPI() {
    final String bearerSchemeName = "Bearer_Token";
    final String serviceAccountSchemeName = "Service_Account";
    final String authCodeSchemeName = "User_Login";

    // Define bearer token authentication scheme
    SecurityScheme bearerTokenScheme = new SecurityScheme()
      .name(bearerSchemeName)
      .type(SecurityScheme.Type.HTTP)
      .scheme("bearer")
      .bearerFormat("JWT")
      .description("<big><b>Paste a valid access token below to authenticate</b></big><br><br>");

    // Define service account authentication scheme
    SecurityScheme clientCredentialsFlowScheme = new SecurityScheme()
      .name(serviceAccountSchemeName)
      .type(SecurityScheme.Type.OAUTH2)
      .description("<big><b>Enter the clientid and secret of your service account to retrieve a token</b></big><br><br>")
      .flows(new OAuthFlows()
        .clientCredentials(
          new OAuthFlow()
            .tokenUrl(tokenUrl)
            .scopes(new Scopes().addString("trust", "trust all"))
        )
      );

    // Define user login authentication scheme
    SecurityScheme userLoginFlowScheme = new SecurityScheme()
      .name(authCodeSchemeName)
      .type(SecurityScheme.Type.OAUTH2)
      .description("<big><b>Enter the id of your public client to log in</b></big><br><br>")
      .flows(new OAuthFlows()
        .authorizationCode(
          new OAuthFlow()
            .tokenUrl(tokenUrl)
            .scopes(new Scopes().addString("trust", "trust all"))
          .authorizationUrl(authorizationUrl)
        )
      );

    return new OpenAPI()
      .addSecurityItem(new SecurityRequirement().addList(bearerSchemeName))
      .addSecurityItem(new SecurityRequirement().addList(serviceAccountSchemeName))
      .addSecurityItem(new SecurityRequirement().addList(authCodeSchemeName))
      .components(new Components()
        .addSecuritySchemes(bearerSchemeName, bearerTokenScheme)
        .addSecuritySchemes(serviceAccountSchemeName, clientCredentialsFlowScheme)
        .addSecuritySchemes(authCodeSchemeName, userLoginFlowScheme));
  }
}

