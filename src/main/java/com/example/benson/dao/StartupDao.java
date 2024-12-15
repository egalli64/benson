/* 
    Benson - A simple Jakarta EE Web Application
    
    https://github.com/egalli64/benson
 */
package com.example.benson.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StartupDao implements AutoCloseable {
    private static final Logger log = LogManager.getLogger(StartupDao.class);

    private Connection conn;

    /**
     * Set the connection up
     * 
     * @param ds data source to the required DBMS
     * @throws IllegalStateException wrapping the original {@link SQLException}
     */
    public StartupDao(DataSource ds) {
        log.traceEntry();

        try {
            this.conn = ds.getConnection();
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public void bensonSetup() {
        log.traceEntry();

    }

    @Override
    public void close() {
        log.traceEntry();
        try {
            conn.close();
        } catch (SQLException se) {
            throw new IllegalStateException("Database issue " + se.getMessage());
        }
    }
}
