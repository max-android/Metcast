package com.myexamplemetcast.metcast.di;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;


public class App extends Application {


    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .serviceModule(new ServiceModule())
                .metcastPresenterModule(new MetcastPresenterModule())
                .glideModule(new GlideModule(this))
                .myFavoritesModule(new MyFavoritesModule(this))
                .databaseModule(new DatabaseModule(this))
                .netInspectorModule(new NetInspectorModule(this))
                .build();
    }



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this);
    }

}
