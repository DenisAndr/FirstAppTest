package de.ludetis.firstapp;

import de.ludetis.firstapp.data.Card;

public interface IBankCardsManager {
    Card get(int position);
    int size();
    void add(Card vitalik);
}
