package com.example.england_incidents.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.england_incidents.Model.CurrentIncidents;
import com.example.england_incidents.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Margaret Maina s1906597
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button filterButton;
    private ListView listView;
    //private RecyclerViewAdaptor adaptor;
    private ArrayAdapter<CurrentIncidents> currentIncidentsArrayAdapter;
    private GetRSSAsyncTask getRSSAsyncTask;
    private ArrayList<CurrentIncidents> incidentarray;


    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    private Handler handler;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        handler = new Handler();
        setContentView(R.layout.incident_list);








        //recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //adaptor = new RecyclerViewAdaptor(this);
        //recyclerView.setAdapter(adaptor);


        currentIncidentsArrayAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,new ArrayList<CurrentIncidents>());

        getRSSAsyncTask = new GetRSSAsyncTask();

        listView = findViewById(R.id.incidentList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CurrentIncidents currentIncidents = currentIncidentsArrayAdapter.getItem(i);
                Intent intent;
                intent = new Intent(getApplicationContext(), DetailedIncidents.class);
                intent.putExtra("incidents", currentIncidents);
                System.out.println("Starting this activity");
                startActivity(intent);

            }
        });

        listView.setAdapter(currentIncidentsArrayAdapter);

        incidentarray = new ArrayList<>();
        //getRSSAsyncTask.doInBackground();
        //getRSSAsyncTask.execute();
        executorService.execute(()->{
            getRSSAsyncTask.doInBackground();
            handler.post(()->{
                incidentarray = getRSSAsyncTask.getIncidentarray();
                currentIncidentsArrayAdapter.addAll(getRSSAsyncTask.getIncidentarray());
            });
        });


        filterButton = findViewById(R.id.filterButton);
        filterButton.setOnClickListener(this::onClick);



    }

    @Override
    public void onClick(View view){
        Intent intent;
        view.findViewById(R.id.filterButton);
        intent = new Intent(MainActivity.this, PlanActivity.class);
        startActivity(intent);
    }





}