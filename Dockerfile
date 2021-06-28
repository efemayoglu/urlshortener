FROM openjdk:latest

EXPOSE 8080

ADD target/urlshortener-1.0.jar urlshortener-1.0.jar

ENTRYPOINT ["java","-jar","urlshortener-1.0.jar"]
