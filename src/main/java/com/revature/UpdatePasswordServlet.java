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

public class UpdatePasswordServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession httpSession = request.getSession(false);
        String password = (String) httpSession.getAttribute("pwd");
        String username = (String) httpSession.getAttribute("uname");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        System.out.println("Old password: "+request.getParameter("old-password-inputted"));
        System.out.println("saved password: "+ password);

        if(request.getParameter("old-password-inputted").equals(password)){
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            SessionFactory factory = cfg.buildSessionFactory();
            Session session = factory.openSession();
            Transaction t = session.beginTransaction();


            Query query1 = session.createQuery("update Employee set password=:pwd where username=:uname");
            query1.setParameter("pwd", request.getParameter("new-password-inputted"));
            query1.setParameter("uname", username);
            int result1 = query1.executeUpdate();
            t.commit();
            session.close();

            httpSession.setAttribute("pwd", request.getParameter("new-password-inputted"));

            request.getRequestDispatcher("EmployeeServlet").include(request, response);
            out.println("<div class='container'><p>Your password was changed successfully</p></div>");
        } else{
            request.getRequestDispatcher("ChangePasswordServlet").include(request, response);
            out.println("<div class='container'><p>You have entered the wrong password!</p></div>");
        }
    }
}
