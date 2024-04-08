FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/login-ecommerce-with-spring-0.0.1.jar /app.jar
CMD ["java","-jar","/app.jar"]