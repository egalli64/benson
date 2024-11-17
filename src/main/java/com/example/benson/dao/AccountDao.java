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

public class AccountDao implements AutoCloseable {
    private static final Logger log = LogManager.getLogger(AccountDao.class);

    private static final String GET_ALL = """
            SELECT account_id, name, password, administrator
            FROM account""";
    private static final String GET_BY_NAME_AND_PASSWORD = """
            SELECT account_id, administrator
            FROM account
            WHERE name = ? AND password =?""";
    private static final String GET_BY_ID = """
            SELECT name, password, administrator
            FROM account
            WHERE account_id = ?""";
    private static final String CREATE = """
            INSERT INTO account (name, password, administrator)
                VALUES (?, ?, ?)""";
    private static final String UPDATE = """
            UPDATE account
            SET name = ?, password = ?, administrator = ?
            WHERE account_id = ?""";
    private static final String UPDATE_PASSWORD_BY_ID = """
            UPDATE account
            SET password = ?
            WHERE account_id = ?""";
    private static final String DELETE_BY_ID = """
            DELETE FROM account
            WHERE account_id = ?""";

    private Connection conn;

    /**
     * Set the connection up
     * 
     * @param ds data source to the required DBMS
     * @throws IllegalStateException wrapping the original {@link SQLException}
     */
    public AccountDao(DataSource ds) {
        log.traceEntry();

        try {
            this.conn = ds.getConnection();
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public Optional<Account> get(String name, String password) {
        log.traceEntry();

        try (PreparedStatement ps = conn.prepareStatement(GET_BY_NAME_AND_PASSWORD)) {
            ps.setString(1, name);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Account(rs.getInt(1), name, password, rs.getBoolean(2)));
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException se) {
            log.error("Can't get account: " + se.getMessage());
            throw new IllegalStateException("Database issue " + se.getMessage());
        }
    }

    public Optional<Account> get(int id) {
        log.traceEntry();

        try (PreparedStatement ps = conn.prepareStatement(GET_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Account(id, rs.getString(1), rs.getString(2), rs.getBoolean(3)));
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException se) {
            log.error("Can't get account: " + se.getMessage());
            throw new IllegalStateException("Database issue " + se.getMessage());
        }
    }

    public boolean create(Account account) {
        try (PreparedStatement ps = conn.prepareStatement(CREATE)) {
            ps.setString(1, account.getName());
            ps.setString(2, account.getPassword());
            ps.setBoolean(3, false);
            if (ps.executeUpdate() == 1) {
                log.trace("Account {} saved", account.getName());
                return true;
            } else {
                log.error("Can't save account " + account.getName());
            }
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
        return false;
    }

    public boolean update(Account account) {
        try (PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            ps.setString(1, account.getName());
            ps.setString(2, account.getPassword());
            ps.setBoolean(3, account.isAdministrator());
            ps.setInt(4, account.getId());
            if (ps.executeUpdate() == 1) {
                log.trace("Account {} updated", account.getId());
                return true;
            } else {
                log.error("Can't update password for account " + account.getId());
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
                log.trace("Account {} password updated", id);
                return true;
            } else {
                log.error("Can't update password for account " + id);
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
                log.trace("Account {} deleted", id);
                return true;
            } else {
                log.error("Can't delete account " + id);
            }
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
        return false;
    }

    public List<Account> getAll() {
        List<Account> result = new ArrayList<Account>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(GET_ALL)) {
            while (rs.next()) {
                result.add(new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4)));
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
