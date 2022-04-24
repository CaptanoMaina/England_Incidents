package com.example.england_incidents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Margaret Maina s1906597
 */

public class MainActivity extends AppCompatActivity {


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
        currentIncidentsArrayAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,new ArrayList<CurrentIncidents>());
        //adaptor = new RecyclerViewAdaptor(this);
        getRSSAsyncTask = new GetRSSAsyncTask();
        //recyclerView.setAdapter(adaptor);
        listView = findViewById(R.id.incidentList);
        listView.setAdapter(currentIncidentsArrayAdapter);
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



    }





}