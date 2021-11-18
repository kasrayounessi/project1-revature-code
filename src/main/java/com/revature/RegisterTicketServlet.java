package com.revature;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterTicketServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        String amount = request.getParameter("ticket-amount-submitted");
        String reason = request.getParameter("ticket-reason-submitted");
        String comment = request.getParameter("ticket-comments-submitted");

        Ticket ticket = new Ticket();

        ticket.setAmount(request.getParameter("ticket-amount-submitted"));
        ticket.setReason(request.getParameter("ticket-reason-submitted"));
        ticket.setComment(request.getParameter("ticket-comments-submitted"));

        session.save(ticket);
        t.commit();
        session.close();



    }
}
