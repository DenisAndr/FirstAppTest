package de.ludetis.firstapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import de.ludetis.firstapp.data.BankCard;

@Database(entities = {BankCard.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CardDao cardDao();
}
