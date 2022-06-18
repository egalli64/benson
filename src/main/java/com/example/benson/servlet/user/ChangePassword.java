package com.example.benson.servlet.user;

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
@WebServlet("/user/changePwd")
public class ChangePassword extends HttpServlet {
    private static final Logger log = LogManager.getLogger(ChangePassword.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();
        String current = request.getParameter("current");
        String password = request.getParameter("password");

        User user = (User) request.getSession().getAttribute("user");

        String url = "changePwd.jsp";
        if (user.getPassword().equals(current)) {
            try (UserDao dao = new UserDao()) {
                if (!dao.updatePasswordById(password, user.getId())) {
                    request.setAttribute("wrong", "Can't save new password");
                } else {
                    url = "/index.jsp";
                }
            }
            user.setPassword(password);
        } else {
            request.setAttribute("wrong", "Wrong password");            
        }

        request.setAttribute("message", "Password correctly changed");
        request.getRequestDispatcher(url).forward(request, response);
    }
}
