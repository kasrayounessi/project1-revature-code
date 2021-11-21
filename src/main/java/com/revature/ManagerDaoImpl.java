package com.revature;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ManagerDaoImpl implements ManagerDao{

    Session session;

    public ManagerDaoImpl(){
        this.session = SessionFactoryBuilder.getSession();
    }


    @Override
    public void acceptPendingTicket(int id) {

        Transaction t = session.beginTransaction();

        Query query = session.createQuery("update Ticket set status=:sts where id=:idChosen");
        query.setParameter("sts","verified");
        query.setParameter("idChosen", id);
        int result = query.executeUpdate();
        if(result>0){
            System.out.println("Ticket accepted successfully");
        } else{
            System.out.println("Oops, something went wrong");
        }

        t.commit();

    }

    @Override
    public void rejectPendingTicket(int id) {

        Transaction t = session.beginTransaction();

        Query query = session.createQuery("update Ticket set status=:sts where id=:idChosen");
        query.setParameter("sts","rejected");
        query.setParameter("idChosen", id);
        int result = query.executeUpdate();
        if(result>0){
            System.out.println("Ticket rejected successfully");
        } else{
            System.out.println("Oops, something went wrong");
        }

        t.commit();

    }

    @Override
    public List viewPendingTickets() {
        Query query = session.createQuery("from Ticket where status=:sts");
        query.setParameter("sts", "pending");
        return query.list();
    }

    @Override
    public List viewAllTickets() {
        Query query = session.createQuery("from Ticket");
        return query.list();
    }
}
