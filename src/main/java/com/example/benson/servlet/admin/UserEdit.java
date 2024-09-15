package com.example.benson.servlet.admin;

import java.io.IOException;
import java.util.Optional;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.benson.dao.User;
import com.example.benson.dao.UserDao;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/admin/user/edit")
public class UserEdit extends HttpServlet {
    private static final Logger log = LogManager.getLogger(UserEdit.class);

    @Resource(name = "jdbc/benson")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();
        String id = request.getParameter("id");

        try (UserDao dao = new UserDao(ds)) {
            Optional<User> user = dao.get(Integer.parseInt(id));
            if (user.isPresent()) {
                request.setAttribute("current", user.get());
                request.getRequestDispatcher("/admin/userEdit.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            log.error("Can't get user " + id, e);
        }

        request.setAttribute("message", "Can't get user " + id);
        request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
    }
}
