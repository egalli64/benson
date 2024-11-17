package com.example.benson.servlet;

import java.io.IOException;
import java.util.Optional;

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
@WebServlet("/login")
public class Login extends HttpServlet {
    private static final Logger log = LogManager.getLogger(Login.class);

    @Resource(name = "jdbc/benson")
    private DataSource ds;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        String url = "index.jsp";
        try (AccountDao dao = new AccountDao(ds)) {
            Optional<Account> account = dao.get(name, SimpleCrypto.encrypt(password));
            if (account.isPresent()) {
                log.trace("Login request accepted for '{}'", name);

                Account logged = account.get();
                if (logged.isAdministrator()) {
                    url = "/admin/";
                }
                request.getSession().setAttribute("user", logged);
            } else {
                // ask again
                log.warn("Wrong password for '{}'", name);
                request.setAttribute("wrong", name);
                url = "login.jsp";
            }
        }
        request.getRequestDispatcher(url).forward(request, response);
    }
}
