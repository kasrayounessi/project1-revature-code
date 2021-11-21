package com.revature;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterTicketServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession httpSession = request.getSession(false);
        String username = (String) httpSession.getAttribute("uname");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if(username.equals("")){
            request.getRequestDispatcher("index.html").include(request, response);
            out.println("<div class='container'><p>You have to login first!</p></div>");
        } else {
            EmployeeDao employeeDao = EmployeeDaoFactory.getEmployeeDao();
            employeeDao.registerNewTicket(username, request.getParameter("ticket-amount-submitted"),request.getParameter("ticket-reason-submitted"),request.getParameter("ticket-comments-submitted"), "pending");
            request.getRequestDispatcher("ViewPendingTicketsServlet").include(request, response);
        }

    }

}
