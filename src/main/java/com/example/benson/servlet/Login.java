package com.example.benson.servlet;

import java.io.IOException;
import java.util.Optional;

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
            Optional<User> user = dao.getUser(name, password);
            if (user.isPresent()) {
                request.getSession().setAttribute("user", user.get());
                log.trace("Login accepted");
            } else {
                // ask again
                request.setAttribute("wrong", name);
                url = "login.jsp";
            }
        }
        request.getRequestDispatcher(url).forward(request, response);
    }
}
