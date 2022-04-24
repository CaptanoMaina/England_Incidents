package com.example.england_incidents;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * @author Margaret Maina s1906597
 */

public class RecyclerViewAdaptor extends  RecyclerView.Adapter<RecyclerViewAdaptor.ViewHolder>{
    private static final String TAG = "RecyclerViewAdaptor";

    private Context context;

    private ArrayList<CurrentIncidents> incidents = new ArrayList<>();

    public RecyclerViewAdaptor(Context context){
        this.context = context;
    }

    public RecyclerViewAdaptor(){

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public  void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG,"onBindViewHolder: called");

        viewHolder.txtTitle.setText(incidents.get(i).getTitle());
        viewHolder.txtDescription.setText(incidents.get(i).getDescription());
        viewHolder.txtRoad.setText(incidents.get(i).getRoad());
        viewHolder.txtLatitude.setText(incidents.get(i).getLatitude());
        viewHolder.txtLongitude.setText(incidents.get(i).getLongitude());
        viewHolder.txtStartDate.setText(incidents.get(i).getEventStart());
        viewHolder.txtEndDate.setText(incidents.get(i).getEventEnd());

        viewHolder.parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //navigate to another view
                Intent intent = new Intent (context, IncidentViewerActivity.class);
               //intent.putExtra("url", incidents.get(i).getLink());
                context.startActivity(intent);
            }

        });



    }

    @Override
    public int getItemCount() {
        return incidents.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtEndDate, txtStartDate,txtLongitude,txtLatitude, txtRoad, txtDescription, txtTitle;
        private CardView parent;


        public ViewHolder (@NonNull View itemView){
            super (itemView);

            txtEndDate = (TextView) itemView.findViewById(R.id.txtEndDate);
            txtStartDate = (TextView) itemView.findViewById(R.id.txtStartDate);
            txtLongitude = (TextView) itemView.findViewById(R.id.txtLongitude);
            txtLatitude = (TextView) itemView.findViewById(R.id.txtLatitude);
            txtRoad = (TextView) itemView.findViewById(R.id.txtRoad);
            txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);

            parent= (CardView) itemView.findViewById(R.id.parent);
        }


    }

    public void setIncidents(ArrayList<CurrentIncidents> incidents){
        this.incidents = incidents;
        notifyDataSetChanged();
    }
}
