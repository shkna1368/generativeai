package com.shabab.aiproject.bank;

import java.util.Date;

public class Transaction {

private     String from;
   private String to;

private     Long amount;
  private   String date;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }





    public Transaction(String from, String to, Long amount) {
        this.from = from;
        this.to = to;

        this.amount = amount;
        this.date = new Date().toString();
    }
}
