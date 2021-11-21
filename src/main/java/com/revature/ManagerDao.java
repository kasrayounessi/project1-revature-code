package com.revature;

import java.util.List;

public interface ManagerDao {
    public void acceptPendingTicket(int id);
    public void rejectPendingTicket(int id);
    public List viewPendingTickets();
    public List viewAllTickets();
}
