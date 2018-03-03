package com.myexamplemetcast.metcast.ui.general;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.myexamplemetcast.metcast.R;
import com.myexamplemetcast.metcast.ui.general.callbacks.FuncVoid;


public class Dialog {



    public void showDialog(Context context, Drawable drawable, FuncVoid funcDelete, FuncVoid funcDetail, int title, int message, int positive){

        AlertDialog.Builder establishBuilder = new AlertDialog.Builder(context);

        establishBuilder.setIcon(drawable)
                .setTitle(context.getString(title))
                .setMessage(context.getString(message))
                .setCancelable(false);

        establishBuilder.setNegativeButton(context.getString(R.string.cancel),(dialog, which)->{dialog.cancel();});

        establishBuilder.setNeutralButton(context.getString(R.string.delete),(dialog,which)->{funcDelete.action();});

        establishBuilder.setPositiveButton(context.getString(positive),(dialog,which)->{funcDetail.action();});

        AlertDialog alert = establishBuilder.create();

        alert.show();
    }


}
