package de.ludetis.firstapp;

import java.util.ArrayList;
import java.util.List;

import de.ludetis.firstapp.data.Card;

public final class BankCardsManager implements IBankCardsManager {

    private static BankCardsManager instance;

    private List<Card> bankCardList = new ArrayList<>();

    private BankCardsManager() {

    }

    public static BankCardsManager getInstance() {

        if (instance == null) {
            instance = new BankCardsManager();
        }

        return instance;
    }

    @Override
    public Card get(int position) {
        return bankCardList.get(position);
    }

    @Override
    public int size() {
        return bankCardList.size();
    }

    @Override
    public void add(Card card) {
        bankCardList.add(card);
    }

    private List<OnCardWasChanged> listeners = new ArrayList<>();

    public void addOnCardWasChangedListener(OnCardWasChanged listener) {
        listeners.add(listener);
    }

    public void notifyDataWasChanged() {
        for (OnCardWasChanged listener : listeners) {
            listener.dataWasChanged();
        }
    }

    interface OnCardWasChanged {
        void dataWasChanged();
    }

}
