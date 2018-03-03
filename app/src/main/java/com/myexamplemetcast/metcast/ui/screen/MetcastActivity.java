package com.myexamplemetcast.metcast.ui.screen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.myexamplemetcast.metcast.R;
import com.myexamplemetcast.metcast.di.App;
import com.myexamplemetcast.metcast.model.favorites.MyFavorites;
import com.myexamplemetcast.metcast.model.service.pojo.DayForecast;
import com.myexamplemetcast.metcast.presenter.MetcastPresenter;
import com.myexamplemetcast.metcast.ui.general.Constants;
import com.myexamplemetcast.metcast.ui.general.Notification;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;


public class MetcastActivity extends AppCompatActivity implements MetcastAdapter.MetcastClickListener,FavoritesDialog.FavoritesDialogListener{



    @Inject
    MetcastPresenter metcastPresenter;


    @Inject
    RequestManager requestManager;

    @Inject
    MyFavorites myFavorites;

    private   EditText etTown;
    private   RecyclerView rvMetcast;
    private   TextView tvCountDay;
    private   int countDay;
    private Notification notification;
    private  FavoritesDialog favoritesDialog;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metcast);

        initComponents();
        initData();
        createItemTouch();

    }


    private void initComponents(){


        App.getAppComponent().injectMetcastActivity(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tbMetcast);
        setSupportActionBar(toolbar);

        SeekBar seekBar=(SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        rvMetcast=(RecyclerView)findViewById(R.id.rvMetcast);
        rvMetcast.setLayoutManager(new LinearLayoutManager(this));
        rvMetcast.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        progressBar=(ProgressBar)findViewById(R.id.progressBar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        tvCountDay=(TextView) findViewById(R.id.tvCountDay);
        etTown=(EditText)findViewById(R.id.editText);
        notification=new Notification(findViewById(R.id.coordMetcast),this);

        fab.setOnClickListener(this::showMetcast);
    }

    private void showMetcast(View view) {

        if (validateForm()) {

            metcastPresenter.attach(()->etTown.getText().toString(),()->transferCountDay(countDay));

        }else{

            notification.showMessage(getString(R.string.fill_in_the_fields));
        }
    }


    private void initData(){

        metcastPresenter.setData(this::showMetcast);

        metcastPresenter.setErrors(error ->  showMessage(getString(R.string.empty)));
    }


    private void showMetcast(List<DayForecast> dayForecasts){

        if(dayForecasts.size()==0){
            showMessage(getString(R.string.repeat_query));
        }else {
            rvMetcast.setAdapter(new MetcastAdapter(dayForecasts,requestManager,this));
            progressBar.setVisibility(View.INVISIBLE);
        }
    }


    private boolean validateForm() {
        boolean valid = true;

        if (TextUtils.isEmpty(etTown.getText().toString())) {
            etTown.setError("Required.");
            valid = false;
        } else {
            etTown.setError(null);
        }
        return valid;
    }


    private SeekBar.OnSeekBarChangeListener seekBarChangeListener=new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            countDay=progress;
            tvCountDay.setText(String.valueOf(progress)+" day");
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


    private void showMessage(String message){

        Toast.makeText(this,message,Toast.LENGTH_LONG).show();


        progressBar.setVisibility(View.INVISIBLE);
    }



    public String transferCountDay(int countDay){

        if(countDay==0){
            countDay= Constants.DEFAULTDAY;

            notification.showMessage(getString(R.string.default_countDay));

        }

        return String.valueOf(countDay);
    }

    @Override
    public void onForecastClick(DayForecast dayForecast) {

        launchDetailMetcastActivity(dayForecast);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.favorite:
                showFavorites();
                break;

            default:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void  showFavorites(){

        if (validateForm()) {

           String  town = etTown.getText().toString();

            saveTownIntoFavorites(town);
        }

        FragmentManager manager =  getSupportFragmentManager();
        favoritesDialog = new FavoritesDialog();
        favoritesDialog.setCancelable(false);
        favoritesDialog.show(manager, getString(R.string.tag_town));

    }


    private void saveTownIntoFavorites(String town) {


        if(myFavorites.getPreferences().contains(town)){

            notification.showMessage(getString(R.string.error_save));

        }else{
            myFavorites.setTown(town,town);

            notification.showMessage(getString(R.string.successful_save));


            Map<String, ?> favorites = myFavorites.getKeysFavorites();

            for(Map.Entry<String, ?> entry : favorites.entrySet()){


                Log.d("TAG",entry.getKey()+"------"+entry.getValue());
            }
        }
    }



    private void launchDetailMetcastActivity(DayForecast dayForecast){

        startActivity(new Intent(this,DetailMetcastActivity.class).putExtra(Constants.KEY_DATA,dayForecast));
    }

    private void createItemTouch(){
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvMetcast);
    }

    private ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
            MetcastAdapter adapter = (MetcastAdapter)rvMetcast.getAdapter();
            adapter.adOnMove(fromPosition,toPosition);
            return true;
        }
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            MetcastAdapter adapter = (MetcastAdapter)rvMetcast.getAdapter();
            adapter.adOnSwiped(viewHolder);
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        metcastPresenter.detach();

    }

    @Override
    public void onTownSet(String town) {

        metcastPresenter.attach(()->town,()->transferCountDay(countDay));

        etTown.setText(town);
        favoritesDialog.dismiss();
    }



}
