package com.myexamplemetcast.metcast.model.service.pojo;

import java.io.Serializable;

/**
 * Created by Максим on 03.03.2018.
 */

public class Temp implements Serializable {

    private double day;
    private double min;
    private double max;

    public Temp(double day, double min, double max) {
        this.day = day;
        this.min = min;
        this.max = max;
    }

    public double getDay() {
        return day;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}

