package com.myexamplemetcast.metcast.di;

import com.myexamplemetcast.metcast.ui.screen.DetailMetcastActivity;
import com.myexamplemetcast.metcast.ui.screen.FavoritesDialog;
import com.myexamplemetcast.metcast.ui.screen.MetcastActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {
        ServiceModule.class,
         MetcastPresenterModule.class,
         GlideModule.class,
        MyFavoritesModule.class,
        DatabaseModule.class,
        NetInspectorModule.class
})

 public interface AppComponent {

     void injectMetcastActivity(MetcastActivity metcastActivity);
     void injectDetailMetcastActivity (DetailMetcastActivity detailMetcastActivity);

     void injectFavoritesDialog(FavoritesDialog favoritesDialog);



}
