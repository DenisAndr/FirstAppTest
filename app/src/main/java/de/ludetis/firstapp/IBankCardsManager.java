package de.ludetis.firstapp;

import de.ludetis.firstapp.data.BankCard;

public interface IBankCardsManager {
    void get(int position, BankCardsManager.GetCartResult resultListener);
    void getAll(BankCardsManager.GetAllCardsResult resultListener);
    void size(BankCardsManager.GetSizeResult resultListener);
    void add(BankCard vitalik);

    void update(BankCard card);
}
