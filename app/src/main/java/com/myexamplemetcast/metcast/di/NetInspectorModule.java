package com.myexamplemetcast.metcast.di;

import android.content.Context;

import com.myexamplemetcast.metcast.ui.general.NetInspector;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Максим on 03.03.2018.
 */


@Module
@Singleton
public class NetInspectorModule {


    private Context context;

    public NetInspectorModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public NetInspector provideNetInspector(){

        return new NetInspector(context);
    }
}
