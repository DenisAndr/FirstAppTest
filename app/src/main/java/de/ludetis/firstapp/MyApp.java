package de.ludetis.firstapp;

import android.app.Application;

import de.ludetis.firstapp.sqlite.DatabaseHelper;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        BankCardsManager.init(databaseHelper);

    }
}
