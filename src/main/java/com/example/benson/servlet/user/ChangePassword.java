package com.example.benson.servlet.user;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.benson.dao.Account;
import com.example.benson.dao.AccountDao;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/user/changePwd")
public class ChangePassword extends HttpServlet {
    private static final Logger log = LogManager.getLogger(ChangePassword.class);

    @Resource(name = "jdbc/benson")
    private DataSource ds;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();
        String current = request.getParameter("current");
        String password = request.getParameter("password");

        String url = "changePwd.jsp";
        Account user = (Account) request.getSession().getAttribute("user");

        if (user.getPassword().equals(current)) {
            try (AccountDao dao = new AccountDao(ds)) {
                if (dao.updatePasswordById(password, user.getId())) {
                    user.setPassword(password);
                    request.setAttribute("message", "Password correctly changed");
                    url = "/index.jsp";
                } else {
                    request.setAttribute("wrong", "Can't save new password");
                }
            }
        } else {
            request.setAttribute("wrong", "Wrong password");
        }

        request.getRequestDispatcher(url).forward(request, response);
    }
}
