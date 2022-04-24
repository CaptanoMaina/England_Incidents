package com.example.england_incidents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailedIncidents extends AppCompatActivity implements View.OnClickListener {

private  TextView textView;
private static CurrentIncidents currentIncidents=null;
Button mapButton;



/*public class DetailedIncidents extends ArrayAdapter {
    //private static CurrentIncidents currentIncidents;
    private Context context;
    private List<CurrentIncidents> incidents = new ArrayList<>();

    public  DetailedIncidents(@NonNull Context context, int position,@NonNull ArrayList<CurrentIncidents> itemView){
        super(context, position, itemView);
        this.context = context;
        this.incidents = itemView;

    }

 */



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);
        currentIncidents = (CurrentIncidents) getIntent().getSerializableExtra("incidents");

        mapButton = findViewById(R.id.mapButton);

        mapButton.setOnClickListener(this::onClick);




        System.out.println("Entering detailed activity");

        currentIncidents = (CurrentIncidents) getIntent().getSerializableExtra("incidents");
        System.out.println(currentIncidents);

        textView = findViewById(R.id.txtTitle);
        textView.setText("Title:  " + currentIncidents.getTitle());

        textView =  findViewById(R.id.txtDescription);
        textView.setText("Description:  " + currentIncidents.getDescription());

        textView=  findViewById(R.id.txtRoad);
        textView.setText("Road  " + currentIncidents.getRoad());

        textView =  findViewById(R.id.txtLongitude);
        textView.setText("Longitude  " + currentIncidents.getLongitude());

        textView = findViewById(R.id.txtLatitude);
        textView.setText("Latitude  " + currentIncidents.getLatitude());

        textView= findViewById(R.id.txtEndDate);
        textView.setText("Start Date  " + currentIncidents.getEventStart());

        textView =  findViewById(R.id.txtStartDate);
        textView.setText("End Date " + currentIncidents.getEventEnd());




    }

        @Override
        public void onClick(View view){

        Intent intent;
        view.findViewById(R.id.mapButton);
        intent = new Intent(getApplicationContext(),MapsActivity.class);
        intent.putExtra("latitude", currentIncidents.getLatitude());
        intent.putExtra("longitude", currentIncidents.getLongitude());
        intent.putExtra("road", currentIncidents.getRoad());
        intent.putExtra("startDate", currentIncidents.getEventStart());
        startActivity(intent);

    }


}



    /*
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialog=inflater.inflate(R.layout.list_item,null,true);


        TextView txtEndDate, txtStartDate,txtLongitude,txtLatitude, txtRoad, txtDescription, txtTitle;


        //final Dialog dialog = new Dialog(getApplicationContext());
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.setCancelable(false);
        //dialog.setContentView(R.layout.list_item);


        txtEndDate = dialog.findViewById(R.id.txtEndDate);
        txtStartDate =  dialog.findViewById(R.id.txtStartDate);
        txtLongitude =  dialog.findViewById(R.id.txtLongitude);
        txtLatitude = dialog.findViewById(R.id.txtLatitude);
        txtRoad =  dialog.findViewById(R.id.txtRoad);
        txtDescription =  dialog.findViewById(R.id.txtDescription);
        txtTitle = dialog.findViewById(R.id.txtTitle);


        txtTitle.setText(incidents.get(position).getTitle());
        txtDescription.setText(incidents.get(position).getDescription());
        txtRoad.setText(incidents.get(position).getRoad());
        txtLatitude.setText(incidents.get(position).getLatitude());
        txtLongitude.setText(incidents.get(position).getLongitude());
        txtStartDate.setText(incidents.get(position).getEventStart());
        txtEndDate.setText(incidents.get(position).getEventEnd());

        return dialog;
    }


*/



