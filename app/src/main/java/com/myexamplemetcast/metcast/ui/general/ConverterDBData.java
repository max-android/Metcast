package com.myexamplemetcast.metcast.ui.general;

import com.myexamplemetcast.metcast.model.database.entity.DBForecast;
import com.myexamplemetcast.metcast.model.service.pojo.DayForecast;
import com.myexamplemetcast.metcast.model.service.pojo.Temp;
import com.myexamplemetcast.metcast.model.service.pojo.Weather;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Максим on 03.03.2018.
 */

public class ConverterDBData {


    public ConverterDBData() {
    }


    public List<DBForecast> convertDayForecastToDBForecast(String town,List<DayForecast> list){

        List<DBForecast> dbForecasts=new ArrayList<>();

        for(DayForecast dayForecast : list){

            dbForecasts.add(new DBForecast(town,
                    dayForecast.getWeather().get(0).getIcon()
                    , String.valueOf(dayForecast.getDt())
                    ,dayForecast.getWeather().get(0).getDescription()
                    ,String.valueOf(dayForecast.getTemp().getMin())
                    ,String.valueOf(dayForecast.getTemp().getMax())
                    ,String.valueOf(dayForecast.getTemp().getDay())
                    ,String.valueOf(dayForecast.getPressure())
                    ,String.valueOf(dayForecast.getHumidity())
                    ,String.valueOf(dayForecast.getSpeed())
                    ));

        }

         return dbForecasts;
    }


    public List<DayForecast> convertDBForecastToDayForecast(List<DBForecast> list){

        List<DayForecast> dayForecasts=new ArrayList<>();


        for(DBForecast dbForecast:list){

            List<Weather> weathers = new ArrayList<>();
            weathers.add(new Weather(dbForecast.getDescription(),dbForecast.getImageView()));

            dayForecasts.add(new DayForecast(

                    Long.parseLong(dbForecast.getDay())
                    ,new Temp(Double.parseDouble(dbForecast.getDayTemperature()),Double.parseDouble(dbForecast.getMinTemperature()),Double.parseDouble(dbForecast.getMaxTemperature()))
                    ,Integer.parseInt(dbForecast.getHumidity())
                    ,Double.parseDouble(dbForecast.getPressure())
                    ,weathers
                    ,Double.parseDouble(dbForecast.getSpeed())

            ));

        }

        return  dayForecasts;
    }






}
