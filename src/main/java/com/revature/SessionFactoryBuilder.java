package com.revature;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryBuilder {
    private static Session session = null;

    private SessionFactoryBuilder(){}

    public static Session getSession(){
        if(session==null){
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            SessionFactory factory = cfg.buildSessionFactory();
            session = factory.openSession();
        }
        return session;
    }
}
