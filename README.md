# benson
A sample Java EE web app meant to run on Heroku, in transition to Render

- Java 21
- Tomcat 9
- PostgreSQL 14

Was deployed to Heroku by: git push heroku master

To run it locally ensure JDBC_DATABASE_URL is set to something like
- jdbc:postgresql://localhost:5432/postgres?user=postgres&password=+++
- In Eclipse: Servers - Tomcat 9 General Information - launch configuration - Environment
