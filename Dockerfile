#JDK kurulumunu yapıyoruz
FROM adoptopenjdk:11-jre-hotspot

#Projemizin olduğu yeri gösteriyoruz ve kopyalıyoruz
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} urlshortener-1.0.jar

#Çalışacağı portu belirliyoruz
EXPOSE 9980

#Projemizi calistıracak komutu yazıyoruz.
ENTRYPOINT ["java","-jar","/urlshortener-1.0.jar"]