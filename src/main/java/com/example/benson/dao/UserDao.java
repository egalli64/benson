package com.example.benson.dao;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.benson.bean.User;

public class UserDao implements AutoCloseable {
    private static final Logger log = LogManager.getLogger(UserDao.class);
    private static final String GET_BY_NAME_AND_PASSWORD = """
            SELECT user_id, administrator
            FROM benson.user
            WHERE name = ? AND password =?""";
    private Connection conn;

    public UserDao() {
        log.trace("called");

        try {
            URI dbUri = new URI(System.getenv("DATABASE_URL"));

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

            DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException | URISyntaxException e) {
            throw new IllegalStateException("Database issue " + e.getMessage());
        }
    }

    public User getUser(String name, String password) {
        log.traceEntry();

        try (PreparedStatement ps = conn.prepareStatement(GET_BY_NAME_AND_PASSWORD)) {
            ps.setString(1, name);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt(1), name, password, rs.getBoolean(2));
                } else {
                    return new User(0, "guest", null, false);
                }
            }
        } catch (SQLException se) {
            log.error("Can't get user: " + se.getMessage());
            throw new IllegalStateException("Database issue " + se.getMessage());
        }
    }

    @Override
    public void close() throws IOException {
        try {
            conn.close();
        } catch (SQLException se) {
            throw new IllegalStateException("Database issue " + se.getMessage());
        }
    }
}
