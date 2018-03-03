package com.myexamplemetcast.metcast.presenter;

import com.myexamplemetcast.metcast.model.database.AppBase;
import com.myexamplemetcast.metcast.model.service.MetcastService;
import com.myexamplemetcast.metcast.ui.general.ConverterDBData;
import com.myexamplemetcast.metcast.ui.general.NetInspector;
import com.myexamplemetcast.metcast.ui.general.callbacks.GetFunc;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Максим on 03.03.2018.
 */

public class MetcastPresenter implements Presenter {

    @Inject
    AppBase database;

    @Inject
    MetcastService metcastService;

    @Inject
    NetInspector netInspector;

    private TransmitterData data;
    private TransmitterErrors errors;
    private CompositeDisposable subscrition = new CompositeDisposable();
    private ConverterDBData converterDBData=new ConverterDBData();


    public MetcastPresenter(AppBase database, MetcastService metcastService, NetInspector netInspector) {
        this.database = database;
        this.metcastService = metcastService;
        this.netInspector=netInspector;
    }

    public void setData(TransmitterData data) {
        this.data = data;
    }

    public void setErrors(TransmitterErrors errors) {
        this.errors = errors;
    }



    @Override
    public void attach(GetFunc<String> town, GetFunc<String> days) {

            if(netInspector.isOnline()){

                subscrition.add(metcastService.forecast(town.transfer(),days.transfer())
                        .doOnSuccess(metcast -> {

                            database.getMetcastDao().deleteMetcast(town.transfer());
                            database.getMetcastDao().insertMetcast(converterDBData.convertDayForecastToDBForecast(town.transfer(),metcast.getList()));

                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe (metcast -> data.transmitData(metcast.getList())
                                ,error -> errors.transmitError(error.getMessage())));

            }else{

                subscrition.add(database.getMetcastDao().getMetcast(town.transfer())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(list -> data.transmitData(converterDBData.convertDBForecastToDayForecast(list))
                                ,error -> errors.transmitError(error.getMessage())));
            }
    }

    @Override
    public void detach() {
        subscrition.clear();
    }
}

