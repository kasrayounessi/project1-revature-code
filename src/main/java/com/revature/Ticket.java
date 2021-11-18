package com.revature;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ticket {

    @Id @GeneratedValue
    private int id;
    private String amount;
    private String reason;
    private String comment;
    private boolean checked = false;

    public Ticket(){}

    public Ticket(int id, String amount, String reason, String comment) {
        this.id = id;
        this.amount = amount;
        this.reason = reason;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void verifyTicket(){
        this.checked = true;
    }
}
