package de.ludetis.firstapp.di.modules;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.ludetis.firstapp.BankCardsManager;
import de.ludetis.firstapp.room.AppDatabase;
import de.ludetis.firstapp.room.CardDao;

@Module
public class BankCardManagerModule {

    private Context context;

    public BankCardManagerModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Singleton
    @Provides
    Context provideContext() {
        return context;
    }

    @Singleton
    @Provides
    CardDao provideCardDao() {
        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "database-cards").build();
        return db.cardDao();
    }

    @Singleton
    @Provides
    BankCardsManager provideBankCardsManager() {
        return new BankCardsManager();
    }

}
