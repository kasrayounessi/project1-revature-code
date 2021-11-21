package com.revature;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class EmployeeDaoImpl implements EmployeeDao{

    Session session;
    public EmployeeDaoImpl(){
        this.session = SessionFactoryBuilder.getSession();
    }

    @Override
    public void uploadEmployeeUsername(String oldUsername, String newUsername) {

        Transaction t = session.beginTransaction();

        Query query1 = session.createQuery("update Employee set username=:newuname where username=:olduname");
        query1.setParameter("newuname", newUsername);
        query1.setParameter("olduname", oldUsername);
        int result1 = query1.executeUpdate();
        Query query2 = session.createQuery("update Ticket set username=:newuname where username=:olduname");
        query2.setParameter("newuname", newUsername);
        query2.setParameter("olduname", oldUsername);
        int result2 = query2.executeUpdate();
        if((result1>0)&&(result2>0)){
            System.out.println("Employee username updated successfully");
        } else {
            System.out.println("Oops, something went wrong");
        }

        t.commit();


    }

    @Override
    public void uploadEmployeePassword(String username, String password) {

        Transaction t = session.beginTransaction();

        Query query = session.createQuery("update Employee set password=:pwd where username=:uname");
        query.setParameter("pwd", password);
        query.setParameter("uname", username);
        int result = query.executeUpdate();
        if(result > 0){
            System.out.println("Employee password updated successfully");
        }

        t.commit();
    }

    @Override
    public List viewPendingTickets(String username) {

        session.clear();
        Query query = session.createQuery("from Ticket where username=:uname and status=:sts");
        query.setParameter("uname", username);
        query.setParameter("sts", "pending");
        return query.list();

    }

    @Override
    public List viewCheckedTickets(String username) {
        session.clear();
        Query query = session.createQuery("from Ticket where username=:uname and status!=:sts");
        query.setParameter("uname", username);
        query.setParameter("sts", "pending");
        List results = query.list();
        return results;

    }

    @Override
    public void registerNewTicket(String username, String amount, String reason, String comment, String status) {

        Transaction t = session.beginTransaction();

        Ticket ticket = new Ticket();
        ticket.setUsername(username);
        ticket.setAmount(amount);
        ticket.setReason(reason);
        ticket.setComment(comment);
        ticket.setStatus(status);

        session.save(ticket);
        t.commit();

    }

}
