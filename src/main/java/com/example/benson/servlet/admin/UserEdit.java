package com.example.benson.servlet.admin;

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
@WebServlet("/admin/user/edit")
public class UserEdit extends HttpServlet {
    private static final Logger log = LogManager.getLogger(UserEdit.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();
        String id = request.getParameter("id");

        try (UserDao dao = new UserDao()) {
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
