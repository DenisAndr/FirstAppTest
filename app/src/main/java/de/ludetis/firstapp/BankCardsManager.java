package de.ludetis.firstapp;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import de.ludetis.firstapp.data.BankCard;
import de.ludetis.firstapp.room.CardDao;

public final class BankCardsManager implements IBankCardsManager {

    @Inject
    public CardDao cardDao;

    ExecutorService threadPool = Executors.newFixedThreadPool(1);

    public BankCardsManager() {
        MyApp.getBankCardManagerComponent().inject(this);
    }

    @Override
    public void get(int position, GetCartResult resultListener) {
        threadPool.execute(() -> {
            BankCard bankCard = cardDao.loadById(position + 1);
            new Handler(Looper.getMainLooper()).post(() -> resultListener.result(bankCard));
        });
    }

    @Override
    public void getAll(GetAllCardsResult resultListener) {
        threadPool.execute(() -> {
            List<BankCard> all = cardDao.getAll();
            new Handler(Looper.getMainLooper()).post(() -> resultListener.result(all));
        });
    }

    public interface GetAllCardsResult {
        void result(List<BankCard> card);
    }

    public interface GetCartResult {
        void result(BankCard card);
    }

    @Override
    public void size(GetSizeResult resultListener) {
        threadPool.execute(() -> {
            int size = cardDao.getAll().size();
            new Handler(Looper.getMainLooper()).post(() -> resultListener.result(size));
        });
    }

    public interface GetSizeResult {
        void result(int size);
    }

    @Override
    public void add(BankCard card) {
        threadPool.execute(() -> cardDao.insertAll(card));
    }

    @Override
    public void update(BankCard card) {
        threadPool.execute(() -> cardDao.update(card));
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
