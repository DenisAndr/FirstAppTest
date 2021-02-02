package de.ludetis.firstapp;

import de.ludetis.firstapp.data.BankCard;

public interface IBankCardsManager {
    BankCard get(int position);
    int size();
    void add(BankCard vitalik);

    void update(BankCard card);
}
