package com.borreguin.cafeteriapointofsale.models;

public class Salary {

    private long salaryId;
    private long employeeId;
    private int payInt;
    private int payCents;
    private int dayOfWeek;
    private int date;
    private int time;

    public Salary(long salaryId, long employeeId, int payInt, int payCents, int dayOfWeek, int day, int month, int year, int time) {
        this.salaryId = salaryId;
        this.employeeId = employeeId;
        this.payInt = payInt;
        this.payCents = payCents;
        this.dayOfWeek = dayOfWeek;
        this.date = year*10000 + month*100 + day;
        this.time = time;
    }

    public long getSalaryId() {
        return salaryId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public int getPayInt() {
        return payInt;
    }

    public int getPayCents() {
        return payCents;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public int getDate() {
        return date;
    }

    public int getDay(){
        return date%100;
    }

    public int getMonth(){
        return (date/100)%100;
    }

    public int getYear(){
        return date/10000;
    }

    public int getTime() {
        return time;
    }
}
