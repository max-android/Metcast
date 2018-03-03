package com.myexamplemetcast.metcast.presenter;

import com.myexamplemetcast.metcast.model.service.pojo.DayForecast;

import java.util.List;


public interface TransmitterData {


     public void transmitData(List<DayForecast> metcasts);


}
