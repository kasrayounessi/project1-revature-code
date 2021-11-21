package com.revature;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateUsernameServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession(false);
        String password = (String) httpSession.getAttribute("pwd");
        String oldUsername = (String) httpSession.getAttribute("uname");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if(oldUsername.equals("")){
            request.getRequestDispatcher("index.html").include(request, response);
            out.println("<div class='container'><p>You have to login first!</p></div>");

        } else{
            if(request.getParameter("secure-password").equals(password)){
                EmployeeDao employeeDao = EmployeeDaoFactory.getEmployeeDao();
                employeeDao.uploadEmployeeUsername(oldUsername, request.getParameter("new-username"));
                httpSession.setAttribute("uname", request.getParameter("new-username"));
                request.getRequestDispatcher("EmployeeServlet").include(request, response);
            } else{
                request.getRequestDispatcher("ChangeUsernameServlet").include(request, response);
                out.println("<div class='container'><p>You have entered the wrong password!</p></div>");
            }
        }
    }
}
