package com.myexamplemetcast.metcast.ui.screen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.myexamplemetcast.metcast.R;
import com.myexamplemetcast.metcast.model.service.pojo.DayForecast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Максим on 03.03.2018.
 */

public class MetcastAdapter  extends RecyclerView.Adapter<MetcastAdapter.ViewHolder>  {


    private List<DayForecast> list;

    private final RequestManager requestManager;

    private final MetcastClickListener listener;

    public  MetcastAdapter  (List<DayForecast> list,RequestManager requestManager,MetcastClickListener listener) {
        this.list = list;

        this.requestManager=requestManager;
        this.listener=listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater= LayoutInflater.from(parent.getContext());

        View view=inflater.inflate(R.layout.item_metcast,parent,false);

        return  new ViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        DayForecast dayForecast = list.get(position);

        holder.bindTo(dayForecast);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public void adOnMove(int fromPos,int toPos) {


        Collections.swap( list,fromPos,toPos);
        notifyItemMoved(fromPos, toPos);
    }

    public void adOnSwiped(RecyclerView.ViewHolder viewHolder) {
        int swipedPosition = viewHolder.getAdapterPosition();
        list.remove(swipedPosition);

        notifyItemRemoved(swipedPosition);
    }

    public static String convertTimeToDay(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time * 1000);
        TimeZone tz = TimeZone.getDefault();


        calendar.add(Calendar.MILLISECOND,
                tz.getOffset(calendar.getTimeInMillis()));

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MM yyyy");
        return dateFormatter.format(calendar.getTime());
    }



    class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final TextView day;
        private final TextView description;
        private final TextView minTemperature;
        private final TextView maxTemperature;
        private final TextView dayTemperature;
        private final TextView pressure;
        private final TextView humidity;
        private final TextView speed;
        private DayForecast dayForecast;

        public ViewHolder(View itemView, final MetcastClickListener listener){
            super(itemView);

            imageView=(ImageView)itemView.findViewById(R.id.image);
            day =(TextView)itemView.findViewById(R.id.dayWeek);
            description =(TextView)itemView.findViewById(R.id.description);
            dayTemperature =(TextView)itemView.findViewById(R.id.dayTemperature);
            minTemperature=(TextView)itemView.findViewById(R.id.minTemperature);
            maxTemperature=(TextView)itemView.findViewById(R.id.maxTemperature);
            pressure=(TextView)itemView.findViewById(R.id.pressure);
            humidity=(TextView)itemView.findViewById(R.id.humidity);
            speed=(TextView)itemView.findViewById(R.id.speed);

            itemView.setOnClickListener(this::launchForecast);

        }

        private void launchForecast(View view){

            listener.onForecastClick(dayForecast);

        }



        public void bindTo(DayForecast dayForecast) {
            this.dayForecast=dayForecast;

            day.setText(String.valueOf(convertTimeToDay(dayForecast.getDt())));
            description.setText(dayForecast.getWeather().get(0).getDescription());
            dayTemperature.setText("dayT: "+String.valueOf(String.format("%.0f",(dayForecast.getTemp().getDay())))+" °C");

            minTemperature.setText("minT: "+String.valueOf(String.format("%.0f",(dayForecast.getTemp().getMin())))+" °C");
            maxTemperature.setText("maxT: "+String.valueOf(String.format("%.0f",(dayForecast.getTemp().getMax())))+" °C");
            pressure.setText("P: "+String.valueOf(String.format("%.0f",(dayForecast.getPressure())))+" hPa");
            humidity.setText("H: "+String.valueOf(dayForecast.getHumidity())+" %");
            speed.setText("W: "+String.valueOf(String.format("%.0f",(dayForecast.getSpeed())))+" m/s");

            requestManager.load("http://openweathermap.org/img/w/"+dayForecast.getWeather().get(0).getIcon()+".png").into(imageView);
        }
    }

    interface MetcastClickListener {

        void onForecastClick(DayForecast dayForecast);

    }
}
