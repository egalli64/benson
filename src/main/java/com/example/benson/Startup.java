/* 
    Benson - A simple Jakarta EE Web Application
    
    https://github.com/egalli64/benson
 */
package com.example.benson;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.benson.dao.StartupDao;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Listen on web app events
 */
@WebListener
public class Startup implements ServletContextListener {
    private static final Logger log = LogManager.getLogger(Startup.class);

    @Resource(name = "jdbc/benson")
    private DataSource ds;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.traceEntry();

        try (StartupDao dao = new StartupDao(ds)) {
            dao.bensonSetup();
        }
    }
}
