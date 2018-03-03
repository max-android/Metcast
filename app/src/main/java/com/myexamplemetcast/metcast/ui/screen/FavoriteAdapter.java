package com.myexamplemetcast.metcast.ui.screen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myexamplemetcast.metcast.R;

import java.util.List;

/**
 * Created by Максим on 03.03.2018.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>  {


    private List<String> list;
    private final FavoriteClickListener listener;



    public FavoriteAdapter  (List<String> list,FavoriteClickListener listener) {
        this.list = list;
        this.listener=listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater= LayoutInflater.from(parent.getContext());

        View view=inflater.inflate(R.layout.item_town,parent,false);

        return  new ViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String town = list.get(position);

        holder.bindTo(town);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }




    class ViewHolder extends RecyclerView.ViewHolder{


        private final TextView tvTown;

        private   String town;

        public ViewHolder(View itemView, final FavoriteClickListener listener){
            super(itemView);


            tvTown =(TextView)itemView.findViewById(R.id.tvFavTown);


            itemView.setOnClickListener(this::launchForecast);

        }

        private void launchForecast(View view){

            listener.onFavoriteClick(town);

        }


        public void bindTo(String town) {
            this.town=town;

            tvTown.setText(town);
        }
    }

    interface FavoriteClickListener {

        void onFavoriteClick(String town);

    }
}
