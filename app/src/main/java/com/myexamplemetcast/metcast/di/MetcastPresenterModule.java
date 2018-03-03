package com.myexamplemetcast.metcast.di;

import android.support.annotation.NonNull;

import com.myexamplemetcast.metcast.model.database.AppBase;
import com.myexamplemetcast.metcast.model.service.MetcastService;
import com.myexamplemetcast.metcast.presenter.MetcastPresenter;
import com.myexamplemetcast.metcast.ui.general.NetInspector;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
@Singleton
public class MetcastPresenterModule {


    @Provides
    @Singleton
    public MetcastPresenter provideMetcastPresenter(@NonNull AppBase database, @NonNull MetcastService metcastService, @NonNull NetInspector netInspector){

        return  new MetcastPresenter(database,metcastService,netInspector);

    }


}
