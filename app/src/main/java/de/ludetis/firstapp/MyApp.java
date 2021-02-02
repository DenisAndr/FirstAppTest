package de.ludetis.firstapp;

import android.app.Application;

import androidx.room.Room;

import de.ludetis.firstapp.room.AppDatabase;
import de.ludetis.firstapp.room.CardDao;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-cards").allowMainThreadQueries().build();

        CardDao cardDao = db.cardDao();

        BankCardsManager.init(cardDao);

    }
}
