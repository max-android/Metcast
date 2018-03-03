package com.myexamplemetcast.metcast.model.service.pojo;

import java.io.Serializable;

/**
 * Created by Максим on 03.03.2018.
 */

public  class Weather implements Serializable {

    private String description;
    private String icon;

    public Weather(String description, String icon) {
        this.description = description;
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
