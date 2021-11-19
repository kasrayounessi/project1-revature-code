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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class ViewCheckedTicketsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession httpSession = request.getSession(false);
        String username = (String) httpSession.getAttribute("uname");

        Query query = session.createQuery("from Ticket where username=:uname and status!=:sts");
        query.setParameter("uname", username);
        query.setParameter("sts", "pending");
        List results = query.list();
        Iterator it = results.iterator();

        request.getRequestDispatcher("navbar.html").include(request, response);
        out.println("<br/>");

        out.println("<html>");
        out.println("<head>");
        out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n");
        out.println("<style>");
        out.println("@import url('https://fonts.googleapis.com/css2?family=Oswald:wght@500&display=swap');\n");
        out.println("body{font-family: 'Oswald', sans-serif;}");
        out.println("thead tr{color:black;}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<div class='text-primary' style='text-align:center;'><h1>Past Tickets</h1></div>");
        out.println("<table class='table table-bordered bg-primary text-light'>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>Ticket number</th>");
        out.println("<th>Amount</th>");
        out.println("<th>Reason</th>");
        out.println("<th>Additional Comments</th>");
        out.println("<th>Status</th>");
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
            out.println("<td>" + ticketRetrieved.getStatus() + "</td>");
            out.println("</tr>");
        }
        out.println("</tbody>");
        out.println("</table>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
