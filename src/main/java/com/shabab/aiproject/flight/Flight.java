package com.shabab.aiproject.flight;

public class Flight {

    private String from;
    private String to;
    private String date;
        private String passengerName;
        private String passport;
        private String time;

    public Flight(String from, String to, String date, String passengerName, String passport,String time) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.passengerName = passengerName;
        this.passport = passport;
        this.time = time;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
