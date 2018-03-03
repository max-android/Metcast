package com.myexamplemetcast.metcast.model.service;

import com.myexamplemetcast.metcast.model.service.pojo.Metcast;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Максим on 03.03.2018.
 */

public interface MetcastService {

    @GET("data/2.5/forecast/daily?&units=metric&APPID=1e5e6e860ca5ae125aff45206b9a0c86")
    Single<Metcast> forecast(@Query("q") String town, @Query("cnt") String countDay);

}
