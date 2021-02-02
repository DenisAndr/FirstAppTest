package de.ludetis.firstapp;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import de.ludetis.firstapp.data.BankCard;
import de.ludetis.firstapp.data.Card;
import de.ludetis.firstapp.sqlite.DatabaseHelper;

public final class BankCardsManager implements IBankCardsManager {

    private static BankCardsManager instance;
    private final DatabaseHelper database;

    private BankCardsManager(DatabaseHelper databaseHelper) {
        this.database = databaseHelper;
    }

    public static BankCardsManager init(DatabaseHelper databaseHelper) {

        if (instance == null) {
            instance = new BankCardsManager(databaseHelper);
        }

        return getInstance();
    }

    public static BankCardsManager getInstance() {
        return instance;
    }

    @Override
    public Card get(int position) {
        Cursor cursor = database.setect(position + 1);
        cursor.moveToFirst();
        Card card = cardFromCursor(cursor);
        return card;
    }

    private Card cardFromCursor(Cursor cursor) {
        Card card = new BankCard();

        String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CARD_OWNER_NAME));
        String pin = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CARD_PIN));
        String id = cursor.getString(cursor.getColumnIndex(DatabaseHelper._ID));

        card.setOwnerName(name);
        card.setPin(Integer.parseInt(pin));
        card.setId(Integer.parseInt(id));

        return card;
    }

    @Override
    public int size() {
        return loadFromDb().size();
    }

    private List<Card> loadFromDb() {
        List<Card> bankCardList = new ArrayList<>();
        Cursor mCursor = database.fetch();
        for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
            // The Cursor is now set to the right position
            bankCardList.add(cardFromCursor(mCursor));
        }
        return bankCardList;
    }

    @Override
    public void add(Card card) {
        database.insert(card.getOwnerName(), String.valueOf(card.getPin()));
    }

    public void update(Card card) {
        database.update(card.getId(), card.getOwnerName(), String.valueOf(card.getPin()));
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
