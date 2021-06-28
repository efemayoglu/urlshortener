FROM openjdk:latest

EXPOSE 8080

ADD target/urlshortener.jar urlshortener.jar

ENTRYPOINT ["java","-jar","urlshortener.jar"]
