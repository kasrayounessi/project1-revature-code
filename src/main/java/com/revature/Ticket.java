package com.revature;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ticket {

    @Id @GeneratedValue
    private int id;
    private String username;
    private String amount;
    private String reason;
    private String comment;
    private String status = "pending";

    public Ticket(){}

    public Ticket(int id, String username, String amount, String reason, String comment) {
        this.id = id;
        this.username = username;
        this.amount = amount;
        this.reason = reason;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus(){
        return status;
    }


    public void verifyTicket(){
        this.status = "verified";
    }

    public void rejectTicket(){
        this.status = "rejected";
    }
}
