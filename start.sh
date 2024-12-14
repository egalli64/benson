#!/bin/bash

DB_FILE="/app/h2/benson.mv.db"
SQL_SCRIPT="/app/sql/setup.sql"

mkdir -p /app/h2

if [ ! -f "$DB_FILE" ]; then
    echo "Initializing database..."
    java -cp /usr/local/tomcat/lib/h2.jar org.h2.tools.RunScript \
        -url "jdbc:h2:file:/app/h2/benson;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE" \
        -user sa \
        -script "$SQL_SCRIPT"
else
    echo "Database already initialized."
fi

echo "Starting Tomcat..."
exec /usr/local/tomcat/bin/catalina.sh run
