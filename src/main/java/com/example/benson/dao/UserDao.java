package com.example.benson.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDao implements AutoCloseable {
    private static final Logger log = LogManager.getLogger(UserDao.class);

    private static final String GET_ALL = """
            SELECT user_id, name, password, administrator
            FROM benson.user""";
    private static final String GET_BY_NAME_AND_PASSWORD = """
            SELECT user_id, administrator
            FROM benson.user
            WHERE name = ? AND password =?""";
    private static final String GET_BY_ID = """
            SELECT name, password, administrator
            FROM benson.user
            WHERE user_id = ?""";
    private static final String CREATE = """
            INSERT INTO benson.user (name, password, administrator)
                VALUES (?, ?, ?)""";
    private static final String UPDATE = """
            UPDATE benson.user
            SET name = ?, password = ?, administrator = ?
            WHERE user_id = ?""";
    private static final String UPDATE_PASSWORD_BY_ID = """
            UPDATE benson.user
            SET password = ?
            WHERE user_id = ?""";
    private static final String DELETE_BY_ID = """
            DELETE FROM benson.user
            WHERE user_id = ?""";

    private Connection conn;

    /**
     * Set the connection up
     * 
     * @param ds data source to the required DBMS
     * @throws IllegalStateException wrapping the original {@link SQLException}
     */
    public UserDao(DataSource ds) {
        log.traceEntry();

        try {
            this.conn = ds.getConnection();
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public Optional<User> get(String name, String password) {
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

    public Optional<User> get(int id) {
        log.traceEntry();

        try (PreparedStatement ps = conn.prepareStatement(GET_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new User(id, rs.getString(1), rs.getString(2), rs.getBoolean(3)));
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException se) {
            log.error("Can't get user: " + se.getMessage());
            throw new IllegalStateException("Database issue " + se.getMessage());
        }
    }

    public boolean create(User user) {
        try (PreparedStatement ps = conn.prepareStatement(CREATE)) {
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

    public boolean update(User user) {
        try (PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setBoolean(3, user.isAdministrator());
            ps.setInt(4, user.getId());
            if (ps.executeUpdate() == 1) {
                log.trace(String.format("User %d updated", user.getId()));
                return true;
            } else {
                log.error("Can't update password for user " + user.getId());
            }
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
        return false;
    }

    public boolean updatePasswordById(String password, int id) {
        try (PreparedStatement ps = conn.prepareStatement(UPDATE_PASSWORD_BY_ID)) {
            ps.setString(1, password);
            ps.setInt(2, id);
            if (ps.executeUpdate() == 1) {
                log.trace(String.format("User %d password updated", id));
                return true;
            } else {
                log.error("Can't update password for user " + id);
            }
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
        return false;
    }

    public boolean deleteById(int id) {
        try (PreparedStatement ps = conn.prepareStatement(DELETE_BY_ID)) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 1) {
                log.trace(String.format("User %d deleted", id));
                return true;
            } else {
                log.error("Can't delete user " + id);
            }
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
        return false;
    }

    public List<User> getAll() {
        List<User> result = new ArrayList<User>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(GET_ALL)) {
            while (rs.next()) {
                result.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4)));
            }
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
        return result;
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
