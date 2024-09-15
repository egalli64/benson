package com.example.benson.servlet.admin;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.benson.dao.UserDao;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/admin/user/delete")
public class UserDelete extends HttpServlet {
    private static final Logger log = LogManager.getLogger(UserDelete.class);

    @Resource(name = "jdbc/benson")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();
        String id = request.getParameter("id");

        String message = "User " + id;
        try (UserDao dao = new UserDao(ds)) {
            if (dao.deleteById(Integer.parseInt(id))) {
                message += " deleted";
            } else {
                message += " deletion didn't work out correctly";
            }
        } catch (Exception e) {
            log.error("Can't delete user " + id, e);
            message += " can't be deleted";
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
    }
}
