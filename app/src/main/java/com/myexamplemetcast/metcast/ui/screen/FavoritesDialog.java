package com.myexamplemetcast.metcast.ui.screen;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.myexamplemetcast.metcast.R;
import com.myexamplemetcast.metcast.di.App;
import com.myexamplemetcast.metcast.model.favorites.MyFavorites;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;


public class FavoritesDialog extends DialogFragment implements FavoriteAdapter.FavoriteClickListener {


    private FavoritesDialogListener dialogListener;
    private RecyclerView rvTown;

    @Inject
    MyFavorites myFavorites;


    private  ListView listView;
    private List<String> towns;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        App.getAppComponent().injectFavoritesDialog(this);

        LayoutInflater inflater = getActivity().getLayoutInflater();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View contentView = inflater.inflate(R.layout.dialog_favorites, null);

        initComponentDialog(contentView);


        builder.setView(contentView);

        builder.setTitle(R.string.search_town);
        builder.setNegativeButton(R.string.cancel,negativListener);

        return builder.create();
    }

    private void initComponentDialog(View contentView) {
        towns=new ArrayList<>();

        rvTown=(RecyclerView)contentView.findViewById(R.id.rvFavor);
        rvTown.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTown.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));


        launchListFavorites();

    }


    private void launchListFavorites() {

        Map<String, ?> favorites = myFavorites.getKeysFavorites();

        if (!favorites.isEmpty()){

            for (Map.Entry<String, ?> entry : favorites.entrySet()) {
                towns.add(entry.getValue().toString());
            }


            rvTown.setAdapter(new FavoriteAdapter(towns,this));

        }else{showTextInfo();}

    }

    private void showTextInfo() {

        Toast.makeText(getContext(),getContext().getString(R.string.not_favorites),Toast.LENGTH_LONG).show();

    }


    private final DialogInterface.OnClickListener negativListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

            dismiss();

        }
    };


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FavoritesDialogListener) {
            dialogListener = (FavoritesDialogListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dialogListener = null;
    }

    @Override
    public void onFavoriteClick(String town) {
        dialogListener.onTownSet(town);
    }

    public interface FavoritesDialogListener {
        void onTownSet(String town);
    }


}
