package dev.jozefowicz.springsecurity.jwt.config;

import java.util.UUID;

public interface  SecurityConstants {

    String AUTH_LOGIN_URL = "/login";
    String JWT_SECRET = UUID.randomUUID().toString() + UUID.randomUUID().toString() + UUID.randomUUID().toString() + UUID.randomUUID().toString(); // TODO this should be maintained and configured e.g. by DevOps

    String TOKEN_HEADER = "Authorization";
    String TOKEN_PREFIX = "Bearer ";
    String TOKEN_TYPE = "JWT";
    String TOKEN_ISSUER = "secured-api"; //
    String TOKEN_AUDIENCE = "secured-app"; // resource server that should accept the token
}
