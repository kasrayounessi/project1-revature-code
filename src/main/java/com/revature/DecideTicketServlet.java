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
import java.util.Iterator;
import java.util.List;

public class DecideTicketServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Query query = session.createQuery("from Ticket where status=:sts");
        query.setParameter("sts", "pending");
        List results = query.list();
        Iterator it = results.iterator();

        request.getRequestDispatcher("manager-navbar.html").include(request, response);
        out.println("<br/>");

        out.println("<html>");
        out.println("<head>");
        out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n");
        out.println("<style>");
        out.println("@import url('https://fonts.googleapis.com/css2?family=Oswald:wght@500&display=swap');\n");
        out.println("body{font-family: 'Oswald', sans-serif;}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container bg-primary\">");
        out.println("<form action=\"AcceptTicketServlet\" method=\"get\">");
        out.println("<div class=\"form-group\">");
        out.println("<label for=\"pendingTicketsSelect\">Select Reason (50 characters)</label>");
        out.println("<select class=\"form-control\" id=\"pendingTicketsSelect\" name=\"pending-ticket-chosen\">");
        while(it.hasNext()){
            Ticket ticketRetrieved = (Ticket) it.next();
            out.println("<option>"+ ticketRetrieved.getId() +"</option>");
        }
        out.println("</select>");
        out.println("</div>");
        out.println("<div>");
        out.println("<input type='submit' value='Accept'");
        out.println("</div>");
        out.println("<div>");
        out.println("<input type='submit' value='Reject' formaction='RejectTicketServlet'");
        out.println("</div>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");


    }
}
