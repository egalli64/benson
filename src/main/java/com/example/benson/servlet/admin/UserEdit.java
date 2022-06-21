package com.example.benson.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("serial")
@WebServlet("/admin/user/edit")
public class UserEdit extends HttpServlet {
    private static final Logger log = LogManager.getLogger(UserEdit.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();
        String id = request.getParameter("id");

        request.setAttribute("message", id + " - user editing not yet implemented");
        request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
    }
}
