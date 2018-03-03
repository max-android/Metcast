package com.myexamplemetcast.metcast.di;

import android.content.Context;

import com.myexamplemetcast.metcast.model.favorites.MyFavorites;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Максим on 16.01.2018.
 */


@Module
@Singleton
public class MyFavoritesModule {

    private Context context;

    public MyFavoritesModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public MyFavorites provideMyFavorites(){

        MyFavorites myFavorites = new MyFavorites(context);

        return myFavorites ;
    }

}


