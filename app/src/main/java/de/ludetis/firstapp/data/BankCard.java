package de.ludetis.firstapp.data;

public class BankCard extends Card {

    public BankCard(String ownerName, String num, float amount, String date, int pin){
        super(ownerName, num, amount, date, pin);
    }

    public BankCard() {
        super();
    }
}
