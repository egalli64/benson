package com.example.benson.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.benson.dao.User;
import com.example.benson.dao.UserDao;

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
            User user = new User(name, password);
            try (UserDao dao = new UserDao()) {
                dao.save(user);
            }
        }

        request.getRequestDispatcher("/").forward(request, response);
    }
}
