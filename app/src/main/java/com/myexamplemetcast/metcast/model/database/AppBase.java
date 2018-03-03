package com.myexamplemetcast.metcast.model.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.myexamplemetcast.metcast.model.database.entity.DBForecast;


@Database(entities = {DBForecast.class}, version = 1)
public abstract class AppBase extends RoomDatabase {

    public abstract MetcastDao getMetcastDao();

}
