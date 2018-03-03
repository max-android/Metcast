package com.myexamplemetcast.metcast.model.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Максим on 03.03.2018.
 */



@Entity(tableName = "metcast")
public class DBForecast {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String town;

    private String imageView;
    private  String day;
    private  String description;
    private  String minTemperature;
    private  String maxTemperature;
    private String dayTemperature;
    private  String pressure;
    private  String humidity;
    private  String speed;


    public DBForecast( String town, String imageView, String day, String description, String minTemperature, String maxTemperature, String dayTemperature, String pressure, String humidity, String speed) {

        this.town = town;
        this.imageView = imageView;
        this.day = day;
        this.description = description;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.dayTemperature = dayTemperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.speed = speed;
    }

    public int getId() {
        return id;
    }

    public String getTown() {
        return town;
    }

    public String getImageView() {
        return imageView;
    }

    public String getDay() {
        return day;
    }

    public String getDescription() {
        return description;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public String getDayTemperature() {
        return dayTemperature;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getSpeed() {
        return speed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setImageView(String imageView) {
        this.imageView = imageView;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMinTemperature(String minTemperature) {
        this.minTemperature = minTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public void setDayTemperature(String dayTemperature) {
        this.dayTemperature = dayTemperature;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
}
