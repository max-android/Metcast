package com.myexamplemetcast.metcast.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.myexamplemetcast.metcast.model.database.AppBase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
@Singleton
public class DatabaseModule {

    private Context context;


    public DatabaseModule(Context context) {
        this.context = context;
    }


    @Provides
    @Singleton
    public AppBase provideDatabase(){

        AppBase base= Room.databaseBuilder(context,AppBase.class, "metcast").build();

        return base;
    }

//    @Provides
//    @Singleton
//    public DatabaseManager provideDatabaseManager(AppBase base){
//
//        return new DatabaseManager(base);
//    }


}
