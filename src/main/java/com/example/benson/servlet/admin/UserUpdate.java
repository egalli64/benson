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
import com.example.benson.logic.SimpleCrypto;

@SuppressWarnings("serial")
@WebServlet("/admin/user/update")
public class UserUpdate extends HttpServlet {
    private static final Logger log = LogManager.getLogger(UserUpdate.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String administrator = request.getParameter("administrator");

        String message = "User " + id;
        try (UserDao dao = new UserDao()) {
            Optional<User> opt = dao.get(Integer.parseInt(id));
            if (opt.isPresent()) {
                User user = opt.get();
                if (!name.isEmpty()) {
                    user.setName(name);
                }
                if (password != null && !password.isBlank()) {
                    user.setPassword(SimpleCrypto.encrypt(password));
                }
                user.setAdministrator(administrator != null && !administrator.isEmpty());

                if (dao.update(user)) {
                    message += " updated";
                } else {
                    message += " can't be updated";
                }
            } else {
                message += " can't be found";
            }
        } catch (Exception e) {
            log.error("Can't update user " + id, e);
            message += " can't be updated";
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
    }
}
