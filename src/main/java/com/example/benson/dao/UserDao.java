package com.example.benson.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDao implements AutoCloseable {
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Database issue " + e.getMessage());
        }
    }

    private static final Logger log = LogManager.getLogger(UserDao.class);
    private static final String GET_BY_NAME_AND_PASSWORD = """
            SELECT user_id, administrator
            FROM benson.user
            WHERE name = ? AND password =?""";
    private static final String SAVE = "INSERT INTO benson.user (name, password, administrator) VALUES (?, ?, ?)";

    private Connection conn;

    public UserDao() {
        try {
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            this.conn = DriverManager.getConnection(dbUrl);
        } catch (Exception e) {
            throw new IllegalStateException("Database issue " + e.getMessage());
        }
    }

    public Optional<User> getUser(String name, String password) {
        log.traceEntry();

        try (PreparedStatement ps = conn.prepareStatement(GET_BY_NAME_AND_PASSWORD)) {
            ps.setString(1, name);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new User(rs.getInt(1), name, password, rs.getBoolean(2)));
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException se) {
            log.error("Can't get user: " + se.getMessage());
            throw new IllegalStateException("Database issue " + se.getMessage());
        }
    }

    public boolean save(User user) {
        try (PreparedStatement ps = conn.prepareStatement(SAVE)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setBoolean(3, false);
            if (ps.executeUpdate() == 1) {
                log.trace(String.format("User %s saved", user.getName()));
                return true;
            } else {
                log.error("Can't save user " + user.getName());
            }
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
        return false;
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
