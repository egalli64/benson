package com.example.benson.servlet;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.benson.dao.Account;
import com.example.benson.dao.AccountDao;
import com.example.benson.logic.SimpleCrypto;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/register")
public class Register extends HttpServlet {
    private static final Logger log = LogManager.getLogger(Register.class);

    @Resource(name = "jdbc/benson")
    private DataSource ds;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        log.trace(name);

        if (name != null && !name.isBlank() && password != null && !password.isEmpty()) {
            Account user = new Account(name, SimpleCrypto.encrypt(password));
            try (AccountDao dao = new AccountDao(ds)) {
                if (!dao.create(user)) {
                    request.setAttribute("wrong", name);
                    request.getRequestDispatcher("/register.jsp").forward(request, response);
                    return;
                }
            }
        }

        request.getRequestDispatcher("/").forward(request, response);
    }
}
