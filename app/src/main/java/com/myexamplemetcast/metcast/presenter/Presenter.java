package com.myexamplemetcast.metcast.presenter;

import com.myexamplemetcast.metcast.ui.general.callbacks.GetFunc;


public interface Presenter {

        //экран создан
        void attach(GetFunc<String> town, GetFunc<String> days);
        //экран уничтожается
        void detach();


}
