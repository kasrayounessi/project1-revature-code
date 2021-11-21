package com.revature;

import java.util.List;

public interface EmployeeDao {

        public void uploadEmployeeUsername(String oldUsername, String newUsername);
        public void uploadEmployeePassword(String username, String password);
        public List viewPendingTickets(String username);
        public List viewCheckedTickets(String username);
        public void registerNewTicket(String username, String amount, String reason, String comment, String status);

}
