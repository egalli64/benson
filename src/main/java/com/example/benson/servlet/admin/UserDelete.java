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
@WebServlet("/admin/user/delete")
public class UserDelete extends HttpServlet {
    private static final Logger log = LogManager.getLogger(UserDelete.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();
        String id = request.getParameter("id");

        String message = "User " + id;
        try (UserDao dao = new UserDao()) {
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
