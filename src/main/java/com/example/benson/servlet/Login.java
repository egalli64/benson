package com.example.benson.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.benson.bean.User;
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

        try (UserDao dao = new UserDao()) {
            User user = dao.getUser(name, password);
            request.getSession().setAttribute("user", user);
            log.trace("Logged as " + user.getName());
        }
        request.getRequestDispatcher("/").forward(request, response);
    }
}
