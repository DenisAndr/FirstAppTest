package de.ludetis.firstapp;

import java.util.ArrayList;
import java.util.List;

import de.ludetis.firstapp.data.BankCard;
import de.ludetis.firstapp.room.CardDao;

public final class BankCardsManager implements IBankCardsManager {

    private static BankCardsManager instance;
    private final CardDao cardDao;

    private BankCardsManager(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    public static void init(CardDao cardDao) {
        if (instance == null) {
            instance = new BankCardsManager(cardDao);
        }
    }

    public static BankCardsManager getInstance() {
        return instance;
    }

    @Override
    public BankCard get(int position) {
        return cardDao.loadById(position + 1);
    }

    @Override
    public int size() {
        return cardDao.getAll().size();
    }

    @Override
    public void add(BankCard card) {
        cardDao.insertAll(card);
    }

    @Override
    public void update(BankCard card) {
        cardDao.update(card);
        notifyDataWasChanged();
    }

    private List<OnCardWasChanged> listeners = new ArrayList<>();

    public void addOnCardWasChangedListener(OnCardWasChanged listener) {
        listeners.add(listener);
    }

    private void notifyDataWasChanged() {
        for (OnCardWasChanged listener : listeners) {
            listener.dataWasChanged();
        }
    }

    interface OnCardWasChanged {
        void dataWasChanged();
    }

}
