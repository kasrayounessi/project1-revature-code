package com.revature;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterTicketServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        HttpSession httpSession = request.getSession(false);

        String username = (String) httpSession.getAttribute("uname");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Ticket ticket = new Ticket();

        ticket.setUsername(username);
        ticket.setAmount(request.getParameter("ticket-amount-submitted"));
        ticket.setReason(request.getParameter("ticket-reason-submitted"));
        ticket.setComment(request.getParameter("ticket-comments-submitted"));

        session.save(ticket);
        t.commit();
        session.close();

        request.getRequestDispatcher("ViewPendingTicketsServlet").include(request, response);

    }


}
