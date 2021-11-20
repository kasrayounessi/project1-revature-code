package com.revature;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AcceptTicketServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Query query = session.createQuery("update Ticket set status=:sts where id=:idChosen");
        query.setParameter("sts","verified");
        query.setParameter("idChosen", Integer.parseInt(request.getParameter("pending-ticket-chosen")));
        int result = query.executeUpdate();
        t.commit();
        session.close();

        request.getRequestDispatcher("DecideTicketServlet").include(request, response);
    }
}
