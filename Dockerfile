FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package

# RUN mkdir -p /app/sql && cp src/main/sql/setup.sql /app/sql/setup.sql

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

ARG TOMCAT_V=11.0.2
ARG H2_V=2.3.232

RUN apt-get update && apt-get install -y wget

RUN wget https://dlcdn.apache.org/tomcat/tomcat-11/v${TOMCAT_V}/bin/apache-tomcat-${TOMCAT_V}.tar.gz && \
    tar xzvf apache-tomcat-${TOMCAT_V}.tar.gz && \
    mv apache-tomcat-${TOMCAT_V} /usr/local/tomcat
RUN wget -O /usr/local/tomcat/lib/h2.jar https://repo1.maven.org/maven2/com/h2database/h2/${H2_V}/h2-${H2_V}.jar
	
RUN rm -rf /usr/local/tomcat/webapps/ROOT /usr/local/tomcat/webapps/docs /usr/local/tomcat/webapps/examples
# RUN sed -i 's/port="8005"/port="-1"/' /usr/local/tomcat/conf/server.xml
RUN sed -i 's/<Connector port="8080" protocol="HTTP\/1.1"/<Connector port="${PORT:-8080}" protocol="HTTP\/1.1" address="0.0.0.0"/' /usr/local/tomcat/conf/server.xml

RUN echo 'export CATALINA_OPTS="-DDB_URL=$DB_URL -DDB_USER=$DB_USER -DDB_PASSWORD=$DB_PASSWORD"' > /usr/local/tomcat/bin/setenv.sh
RUN chmod +x /usr/local/tomcat/bin/setenv.sh

COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war
# COPY --from=build /app/sql /app/sql
COPY src/main/webapp/META-INF/context_.xml /usr/local/tomcat/conf/context.xml

# COPY start.sh /app/start.sh
# RUN chmod +x /app/start.sh

EXPOSE 8080

# CMD ["/app/start.sh"]
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]
