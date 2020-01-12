package dev.jozefowicz.springsecurity.jwt.config;

public interface  SecurityConstants {

    String AUTH_LOGIN_URL = "/login";
    String JWT_SECRET = "asd7a89s7d8a7sd987a98sd7a8s7d987as98d7a98s7d89a7sd8asdjhabshdbasjhdbjahsbdhjabsdhjabsdh";

    String TOKEN_HEADER = "Authorization";
    String TOKEN_PREFIX = "Bearer ";
    String TOKEN_TYPE = "JWT";
    String TOKEN_ISSUER = "secured-api"; //
    String TOKEN_AUDIENCE = "secured-app"; // resource server that should accept the token
}
