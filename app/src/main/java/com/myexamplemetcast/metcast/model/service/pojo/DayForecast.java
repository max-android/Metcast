package com.myexamplemetcast.metcast.model.service.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Максим on 03.03.2018.
 */

public class DayForecast implements Serializable {


    private long dt;
    private Temp temp;
    private int humidity;
    private double pressure;
    private List<Weather> weather;
    private double speed;

    public DayForecast(long dt, Temp temp, int humidity, double pressure, List<Weather>  weather, double speed) {

        this.dt = dt;
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        this.weather = weather;
        this.speed = speed;

    }


    public double getSpeed() {
        return speed;
    }

    public long getDt() {
        return dt;
    }

    public Temp getTemp() {
        return temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public List<Weather> getWeather() {
        return weather;
    }


}
