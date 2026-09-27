# Config Package

## Purpose
This package contains all application configuration classes.

## Responsibilities

- Configure Spring Security
- Configure CORS
- Configure Swagger/OpenAPI
- Configure Beans
- Configure Password Encoder
- Configure Authentication Manager
- Configure ObjectMapper if required

## Classes

SecurityConfig.java
- Configure authentication and authorization
- Configure SecurityFilterChain
- Define BCryptPasswordEncoder Bean

CorsConfig.java
- Configure Cross-Origin Resource Sharing

OpenApiConfig.java
- Configure Swagger Documentation

AppConfig.java
- General application beans

## Notes

Only configuration classes should be placed here.
Business logic must never be written in this package.