package com.example.benson.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.benson.dao.UserDao;

@SuppressWarnings("serial")
@WebServlet("/admin/user/all")
public class UserAll extends HttpServlet {
    private static final Logger log = LogManager.getLogger(UserAll.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        try (UserDao dao = new UserDao()) {
            request.setAttribute("users", dao.getAll());
        }
        request.getRequestDispatcher("/admin/users.jsp").forward(request, response);
    }
}
