package de.ludetis.firstapp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import de.ludetis.firstapp.data.BankCard;

@Dao
public interface CardDao {

    @Query("SELECT * FROM BankCard")
    List<BankCard> getAll();

    @Query("SELECT * FROM BankCard WHERE uid IN (:userIds)")
    List<BankCard> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM BankCard WHERE uid IN (:id)")
    BankCard loadById(int id);

    @Insert
    void insertAll(BankCard... users);

    @Delete
    void delete(BankCard user);

    @Update
    void update(BankCard card);
}
