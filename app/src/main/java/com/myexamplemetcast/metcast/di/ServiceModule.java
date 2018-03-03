package com.myexamplemetcast.metcast.di;

import android.support.annotation.NonNull;

import com.myexamplemetcast.metcast.model.service.MetcastService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Максим on 01.03.2018.
 */

@Module
@Singleton
public class ServiceModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit(){


        return new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }



    @Provides
    @Singleton
    public MetcastService provideMetcastService(@NonNull Retrofit retrofit){

        return retrofit.create(MetcastService.class);

    }
}
