package com.example.benson.servlet;

import java.io.IOException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.benson.dao.User;
import com.example.benson.dao.UserDao;
import com.example.benson.logic.SimpleCrypto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/login")
public class Login extends HttpServlet {
    private static final Logger log = LogManager.getLogger(Login.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        log.trace("Login request for '" + name + "'");

        String url = "index.jsp";
        try (UserDao dao = new UserDao()) {
            Optional<User> user = dao.get(name, SimpleCrypto.encrypt(password));
            if (user.isPresent()) {
                User logged = user.get();
                if (logged.isAdministrator()) {
                    url = "/admin/";
                }
                request.getSession().setAttribute("user", logged);
                log.trace("Accepted");
            } else {
                // ask again
                request.setAttribute("wrong", name);
                url = "login.jsp";
            }
        }
        request.getRequestDispatcher(url).forward(request, response);
    }
}
