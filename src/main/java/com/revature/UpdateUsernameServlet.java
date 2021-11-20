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

public class UpdateUsernameServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession httpSession = request.getSession(false);
        String password = (String) httpSession.getAttribute("pwd");
        String oldUsername = (String) httpSession.getAttribute("uname");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if(request.getParameter("secure-password").equals(password)){
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            SessionFactory factory = cfg.buildSessionFactory();
            Session session = factory.openSession();
            Transaction t = session.beginTransaction();


            Query query1 = session.createQuery("update Employee set username=:newuname where username=:olduname");
            query1.setParameter("newuname", request.getParameter("new-username"));
            query1.setParameter("olduname", oldUsername);
            int result1 = query1.executeUpdate();
            Query query2 = session.createQuery("update Ticket set username=:newuname where username=:olduname");
            query2.setParameter("newuname", request.getParameter("new-username"));
            query2.setParameter("olduname", oldUsername);
            int result2 = query2.executeUpdate();
            t.commit();
            session.close();

            httpSession.setAttribute("uname", request.getParameter("new-username"));

            request.getRequestDispatcher("EmployeeServlet").include(request, response);
        } else{
            request.getRequestDispatcher("ChangeUsernameServlet").include(request, response);
            out.println("<div class='container'><p>You have entered the wrong password!</p></div>");
        }

    }
}
