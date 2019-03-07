package com.example.ambit.mobilepoliceadmin.Adaptar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ambit.mobilepoliceadmin.Model.AllDetails;
import com.example.ambit.mobilepoliceadmin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Details extends RecyclerView.Adapter<Details.MyViewHolder> {

    private Context context;
    private List<AllDetails> allDetails;

    private onItemClickListener listener;

    public Details(Context context, List<AllDetails> allDetails) {
        this.context = context;
        this.allDetails = allDetails;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_layout,viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        AllDetails allDetails = this.allDetails.get(i);

        myViewHolder.crimeType.setText(allDetails.getCrimeType());
        myViewHolder.crimeDateTime.setText(allDetails.getTimeAndDate());
        myViewHolder.withnessPhone.setText(allDetails.getPhoneNumber());
        myViewHolder.crimeLocation.setText(allDetails.getLocationName());
        myViewHolder.crimeDetails.setText(allDetails.getComplain());
        myViewHolder.crimeLat.setText(allDetails.getLat().toString());
        myViewHolder.crimeLon.setText(allDetails.getLon().toString());

        Picasso.with(context).load(allDetails.getImageUrl()).placeholder(R.mipmap.ic_launcher_round).fit().centerCrop().into(myViewHolder.crimePicture);

    }

    @Override
    public int getItemCount() {
        return allDetails.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        TextView crimeType, crimeDateTime, withnessPhone, crimeLocation,crimeDetails,crimeLat,crimeLon;
        ImageView crimePicture;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            crimeType =  itemView.findViewById(R.id.crimeType);
            crimeDateTime =  itemView.findViewById(R.id.crimeTime);
            withnessPhone =  itemView.findViewById(R.id.withnessPhone);
            crimeLocation =  itemView.findViewById(R.id.crimeLocation);
            crimeDetails =  itemView.findViewById(R.id.crimeDetails);
            crimeLat =  itemView.findViewById(R.id.crimeLat);
            crimeLon =  itemView.findViewById(R.id.crimeLon);

            crimePicture =  itemView.findViewById(R.id.crimePhoto);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v) {

            if (listener!=null){
                int position = getAdapterPosition();

                if (position!=RecyclerView.NO_POSITION){
                    listener.onItemClick(position);
                }
            }

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            MenuItem delete =  menu.add(Menu.NONE, 1, 1, "Delete Report");

            delete.setOnMenuItemClickListener(this);

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            if (listener!=null){
                int position = getAdapterPosition();

                if (position!=RecyclerView.NO_POSITION){

                    switch (item.getItemId()){
                        case 1:
                            listener.onDelete(position);
                    }
                }
            }

            return false;
        }
    }

    public interface onItemClickListener{
        void onItemClick(int position);
        void onDelete(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener){

        this.listener = listener;

    }
}
