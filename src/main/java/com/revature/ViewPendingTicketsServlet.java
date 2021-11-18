package com.revature;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class ViewPendingTicketsServlet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Query query = session.createQuery("from Ticket");
        List results = query.list();
        Iterator it = results.iterator();
        out.println("<html>");
        out.println("<body>");
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>Ticket number</th>");
        out.println("<th>Amount</th>");
        out.println("<th>Reason</th>");
        out.println("<th>Additional Comments</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");
        while(it.hasNext()){
            Ticket ticketRetrieved = (Ticket) it.next();
            out.println("<tr>");
            out.println("<td>" + ticketRetrieved.getId() + "</td>");
            out.println("<td>" + ticketRetrieved.getAmount() + "</td>");
            out.println("<td>" + ticketRetrieved.getReason() + "</td>");
            out.println("<td>" + ticketRetrieved.getComment() + "</td>");
            out.println("</tr>");
        }
        out.println("</tbody>");
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");




    }
}
