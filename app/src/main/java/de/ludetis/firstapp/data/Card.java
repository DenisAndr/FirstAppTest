package de.ludetis.firstapp.data;

public abstract class Card {

    private String ownerName;
    private String num;
    private float amount;
    private String date;
    private int pin;
    private int id;

    public Card(String ownerName, String num, float amount, String date, int pin) {
        this.ownerName = ownerName;
        this.num = num;
        this.amount = amount;
        this.date = date;
        this.pin = pin;
    }

    public Card() {

    }

    public int getId() {
        return id;
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

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setId(int id) {
        this.id = id;
    }

}
