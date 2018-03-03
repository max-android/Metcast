package com.myexamplemetcast.metcast.model.favorites;

import android.content.Context;
import android.content.SharedPreferences;

import com.myexamplemetcast.metcast.ui.general.Constants;

import java.util.Map;


public class MyFavorites {


private SharedPreferences preferences;
private SharedPreferences.Editor editor;

    private Context context;

    public MyFavorites(Context context) {
        preferences = context.getSharedPreferences(Constants.FAVORITES,Context.MODE_PRIVATE);
       setTown(Constants.SAINT,Constants.SAINT);
       setTown(Constants.MOSCOW,Constants.MOSCOW);
    }


    public void setTown(String key,String town){

        createSpEditor();
        editor.putString(key,town);
        editor.apply();
    }


    public  Map<String,?> getKeysFavorites(){

        Map<String,?> prefsMap = preferences.getAll();

           return prefsMap;
    }


    public SharedPreferences getPreferences() {
        return preferences;
    }



    public void deleteTown(String key){

        createSpEditor();
        editor.remove(key);
        editor.apply();

    }





    private void createSpEditor(){

        editor = preferences.edit();

    }


}
