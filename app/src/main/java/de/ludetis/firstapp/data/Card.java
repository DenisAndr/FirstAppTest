package de.ludetis.firstapp.data;

public abstract class Card {

    private String ownerName;
    private String num;
    private float amount;
    private String date;
    private int pin;

    public Card(String ownerName, String num, float amount, String date, int pin) {
        this.ownerName = ownerName;
        this.num = num;
        this.amount = amount;
        this.date = date;
        this.pin = pin;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getNum() {
        return num;
    }

    public float getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public int getPin() {
        return pin;
    }
}
