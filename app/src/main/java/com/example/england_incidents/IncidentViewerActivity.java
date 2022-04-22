package com.example.england_incidents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.InputStream;

/**
 * @author Margaret Maina s1906597
 */

public class IncidentViewerActivity extends AppCompatActivity {

    private WebView incidentViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident_viewer);
        incidentViewer = (WebView) findViewById(R.id.incidentViewer);

        try {
            Intent intent = getIntent();
            String url = intent.getStringExtra("url");
            incidentViewer.setWebViewClient(new WebViewClient());
            incidentViewer.getSettings().setJavaScriptEnabled(true);
            incidentViewer.loadUrl(url);

        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }
    @Override
    public void onBackPressed(){
        if (incidentViewer.canGoBack()){
            incidentViewer.goBack();
        }else{
            super.onBackPressed();
        }

    }
}