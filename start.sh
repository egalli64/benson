#!/bin/bash

DB_FILE="/app/h2/benson.mv.db"
SQL_SCRIPT="/app/sql/setup.sql"

mkdir -p /app/h2

if [ ! -f "$DB_FILE" ]; then
    echo "Initializing database..."
    java -cp /usr/local/tomcat/lib/h2.jar org.h2.tools.RunScript \
        -url "jdbc:h2:file:/app/h2/benson" \
        -user sa \
        -script "$SQL_SCRIPT"
else
    echo "Database already initialized."
fi

PORT=${PORT:-8080}
export CATALINA_OPTS="-Dserver.port=${PORT}"

echo "Starting Tomcat on port ${PORT}..."
exec /usr/local/tomcat/bin/catalina.sh run
