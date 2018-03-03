package com.myexamplemetcast.metcast.ui.screen;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.myexamplemetcast.metcast.R;
import com.myexamplemetcast.metcast.di.App;
import com.myexamplemetcast.metcast.model.service.pojo.DayForecast;
import com.myexamplemetcast.metcast.ui.general.Constants;

import javax.inject.Inject;

public class DetailMetcastActivity extends AppCompatActivity {


    @Inject
    RequestManager requestManager;


    private DayForecast dayForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_metcast);

        initData();
        initComponents();

    }


private void initComponents(){

    App.getAppComponent().injectDetailMetcastActivity(this);

    Toolbar toolbar = (Toolbar) findViewById(R.id.tbDayForecast);
    toolbar.setTitle(R.string.detailed_forecast);
    toolbar.setNavigationIcon(ContextCompat.getDrawable(this,R.drawable.ic_arrow_back_24dp));
    toolbar.setNavigationOnClickListener(exit -> onBackPressed());

    TextView  tvDayWeekDeit=(TextView)findViewById(R.id.dayWeekDeit);
    TextView tvDescriptionDayDeit=(TextView)findViewById(R.id.descriptiondayDeit);
    TextView tvDayTemperatureDeit=(TextView)findViewById(R.id.dayTemperatureDeit);
    TextView tvMinDayDeit=(TextView)findViewById(R.id.minDayDeit);
    TextView tvMaxDayDeit=(TextView)findViewById(R.id.maxDayDeit);
    TextView tvWindSpeedDeit=(TextView)findViewById(R.id.windSpeedDeit);
    TextView tvPressureDeit=(TextView)findViewById(R.id.pressureDeit);
    TextView tvHumidityDeit=(TextView)findViewById(R.id.humidityDeit);
    ImageView imageView=(ImageView)findViewById(R.id.imgDeit);

    requestManager.load("http://openweathermap.org/img/w/"+dayForecast.getWeather().get(0).getIcon()+".png").into(imageView);
    tvDayWeekDeit.setText(String.valueOf(MetcastAdapter.convertTimeToDay(dayForecast.getDt())));
    tvDescriptionDayDeit.setText(dayForecast.getWeather().get(0).getDescription());
    tvDayTemperatureDeit.setText(String.valueOf(String.format("%.0f",(dayForecast.getTemp().getDay())))+" °C");

    tvMinDayDeit.setText(String.valueOf(String.format("%.0f",(dayForecast.getTemp().getMin())))+" °C");
    tvMaxDayDeit.setText(String.valueOf(String.format("%.0f",(dayForecast.getTemp().getMax())))+" °C");
    tvWindSpeedDeit.setText(String.valueOf(String.format("%.0f",(dayForecast.getSpeed())))+" m/s");
    tvPressureDeit.setText(String.valueOf(String.format("%.0f",(dayForecast.getPressure())))+" hPa");
    tvHumidityDeit.setText(String.valueOf(dayForecast.getHumidity())+" %");

}


private void initData(){
    dayForecast=(DayForecast)getIntent().getSerializableExtra(Constants.KEY_DATA);
}

}
