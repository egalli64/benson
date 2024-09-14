package com.example.benson.servlet;

import java.io.IOException;

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
@WebServlet("/register")
public class Register extends HttpServlet {
    private static final Logger log = LogManager.getLogger(Register.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        log.trace(name);

        if (name != null && !name.isBlank() && password != null && !password.isEmpty()) {
            User user = new User(name, SimpleCrypto.encrypt(password));
            try (UserDao dao = new UserDao()) {
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
