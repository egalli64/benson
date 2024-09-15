FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package

FROM eclipse-temurin:21-jre-jammy

ENV DB_URL=jdbc:postgresql://dpg-crin63bv2p9s738l0ae0-a/benson_ldjp
ENV DB_USER=benson
ENV DB_PASSWORD=YRCNo7fk7BClcD1UWRKS8IcRxOjxoaB9

WORKDIR /app

RUN apt-get update && apt-get install -y wget && \
    wget https://dlcdn.apache.org/tomcat/tomcat-10/v10.1.29/bin/apache-tomcat-10.1.29.tar.gz && \
    tar xzvf apache-tomcat-10.1.29.tar.gz && \
    mv apache-tomcat-10.1.29 /usr/local/tomcat

RUN apt-get update && apt-get install -y curl && \
    curl -o /usr/local/tomcat/lib/postgresql.jar \
    https://jdbc.postgresql.org/download/postgresql-42.7.4.jar
	
RUN rm -rf /usr/local/tomcat/webapps/ROOT /usr/local/tomcat/webapps/docs /usr/local/tomcat/webapps/examples
RUN sed -i 's/port="8005"/port="-1"/' /usr/local/tomcat/conf/server.xml

COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war
COPY src/main/webapp/META-INF/context_.xml /usr/local/tomcat/conf/context.xml

EXPOSE 8080

CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]
