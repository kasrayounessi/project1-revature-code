package com.revature;

public class ManagerDaoFactory {
    private static ManagerDao managerDao;

    private ManagerDaoFactory(){}

    public static ManagerDao getManagerDao(){
        if(managerDao==null){
            managerDao = new ManagerDaoImpl();
        }
        return managerDao;
    }
}
