package org.example.order.web.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(
  description = "buyurtma berish va boshqarish",
  title = "order and manage",
  version = "1.0"
),
  security = @SecurityRequirement(
    name = "BearerAuth"
  )
)
@SecurityScheme(
  name = "BearerAuth",
  description = "JWT-based authentication",
  scheme = "bearer",
  type = SecuritySchemeType.HTTP,
  bearerFormat = "JWT",
  in = SecuritySchemeIn.HEADER
)
@Configuration
public class SwaggerConfig {
}
