package com.example.benson.servlet.admin;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.benson.dao.AccountDao;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/admin/user/all")
public class UserAll extends HttpServlet {
    private static final Logger log = LogManager.getLogger(UserAll.class);

    @Resource(name = "jdbc/benson")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        try (AccountDao dao = new AccountDao(ds)) {
            request.setAttribute("users", dao.getAll());
        }
        request.getRequestDispatcher("/admin/users.jsp").forward(request, response);
    }
}
