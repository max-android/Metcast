package com.myexamplemetcast.metcast.model.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.myexamplemetcast.metcast.model.database.entity.DBForecast;

import java.util.List;

import io.reactivex.Single;


@Dao
public interface MetcastDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMetcast(List<DBForecast> forecast);



 @Query("SELECT * FROM metcast WHERE town = :select_town")
 Single<List<DBForecast>> getMetcast(String select_town);



 @Query("DELETE  FROM metcast WHERE town = :select_town")
   void deleteMetcast(String select_town);


}
