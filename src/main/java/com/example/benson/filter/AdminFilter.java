package com.example.benson.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.benson.dao.User;
import com.example.benson.servlet.Login;

@SuppressWarnings("serial")
@WebFilter(urlPatterns = { "/admin/*" })
public class AdminFilter extends HttpFilter implements Filter {
    private static final Logger log = LogManager.getLogger(Login.class);

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest)) {
            // put a denial message in the response and return
            log.warn("Non-web request detected!");
            response.setContentType("text/plain");
            response.setCharacterEncoding("utf-8");

            response.getWriter().println("Only HTTP requests accepted!");
            return;
        }

        HttpSession session = ((HttpServletRequest) request).getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || !user.isAdministrator()) {
            log.warn("Non-admin request detected!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        chain.doFilter(request, response);
    }
}
