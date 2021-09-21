# Configuration service

This is the Configuration service for provinding external configuration Using a Git Repository.

This Implementation uses "Spring Cloud Config" and uses git as properties repository.

All the microservice that uses this configuration, needs to point this url and the active profile(s). With the next Configuration:

```yaml
spring:
  application:
    name: auth-service
  profiles:
    active: local
  cloud:
    config:
      uri: http://localhost:8888
```
