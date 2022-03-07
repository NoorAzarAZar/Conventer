package com.example.conventer;

public class LengthConventer {
    private int id;
    private double amount;
    private String from;
    private String to;
    private double reslut;

    public LengthConventer(int id, double amount, String from, String to, double reslut) {
        this.id = id;
        this.amount = amount;
        this.from = from;
        this.to = to;
        this.reslut = reslut;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getReslut() {
        return reslut;
    }

    public void setReslut(double reslut) {
        this.reslut = reslut;
    }
}

