package com.revature;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String user = request.getParameter("user-value");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        if (user != null) {
            String pwdRetrieved = "";
            if(user.equals("employee")){

                Query query = session.createQuery("from Employee where username=:uname ");
                query.setParameter("uname",request.getParameter("username"));
                List results = query.list();
                Iterator it = results.iterator();

                while(it.hasNext()){
                    Employee empRetrieved = (Employee) it.next();
                    pwdRetrieved = empRetrieved.getPassword();
                    System.out.println(empRetrieved.getPassword());
                }
                if(request.getParameter("password").equals(pwdRetrieved)){
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("uname", request.getParameter("username"));
                    RequestDispatcher rd = request.getRequestDispatcher("EmployeeServlet");
                    rd.forward(request, response);
                } else{
                    out.println("No such account exits!");
                    request.getRequestDispatcher("index.html").include(request, response);
                }

            } else if(user.equals("manager")){

                Query query = session.createQuery("from Manager where username=:uname ");
                query.setParameter("uname",request.getParameter("username"));
                List results = query.list();
                Iterator it = results.iterator();

                while(it.hasNext()){
                    Manager empRetrieved = (Manager) it.next();
                    pwdRetrieved = empRetrieved.getPassword();
                    System.out.println(empRetrieved.getPassword());
                }
                if(request.getParameter("password").equals(pwdRetrieved)){
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("uname", request.getParameter("username"));
                    RequestDispatcher rd = request.getRequestDispatcher("ManagerServlet");
                    rd.forward(request, response);
                } else{
                    out.println("No such account exits!");
                    request.getRequestDispatcher("index.html").include(request, response);
                }
            }
        } else {
            out.println("You must choose either employee or manager");
            request.getRequestDispatcher("index.html").include(request, response);
        }

    }
}
