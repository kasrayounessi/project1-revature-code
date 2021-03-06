package com.revature;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");

        HttpSession httpSession = request.getSession(false);
        httpSession.setAttribute("uname", "");
        httpSession.setAttribute("pwd", "");

        request.getRequestDispatcher("index.html").include(request, response);
    }
}
