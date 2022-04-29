FROM openjdk:17
EXPOSE 8080
ADD target/springboot-restapi.jar springboot-restapi.jar
ENTRYPOINT ["java","-jar","/springboot-restapi.jar","--spring.profiles.active=prod","--DB=mysql-svc"]