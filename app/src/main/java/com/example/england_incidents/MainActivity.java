package com.example.england_incidents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author Margaret Maina s1906597
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ArrayList <CurrentIncidents> incidentarray;

    private RecyclerView recyclerView;
    private RecyclerViewAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adaptor = new RecyclerViewAdaptor(this);

        incidentarray = new ArrayList<>();
        GetRSSAsyncTask getRSSAsyncTask = new GetRSSAsyncTask();
        getRSSAsyncTask.execute();
    }
    private class GetRSSAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
           InputStream inputStream = getInputStream();
            try {
                initXMLPullParser(inputStream);
            } catch (XmlPullParserException | IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            adaptor.setIncidents(incidentarray);
            super.onPostExecute(aVoid);
        }
    }

    private void initXMLPullParser (InputStream inputStream) throws XmlPullParserException, IOException {
        Log.d(TAG, "initXMLPullParser: called");
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(inputStream, null);
        parser.nextTag();

        parser.require(XmlPullParser.START_TAG, null, "rss");
        while (parser.next() != XmlPullParser.END_TAG){
            if (parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }
             parser.require(XmlPullParser.START_TAG, null, "channel");
            while (parser.next() != XmlPullParser.END_TAG){
                if (parser.getEventType() != XmlPullParser.START_TAG){
                    continue;
                }
                if (parser.getName().equals("item")) {
                    parser.require(XmlPullParser.START_TAG, null, "item");

                    String title = "";
                    String description = "";
                    String road = "";
                    String latitude = "";
                    String longitude = "";
                    String eventStart = "";
                    String eventEnd = "";

                    while (parser.next() != XmlPullParser.END_TAG) {
                        if (parser.getEventType() != XmlPullParser.START_TAG) {
                            continue;
                        }

                        String tagName = parser.getName();
                        if (tagName.equals("title")) {
                            //get title content
                            title = getContent(parser, "title");
                        } else if (tagName.equals("description")) {
                            //get description content
                            description = getContent(parser, "description");
                        } else if (tagName.equals("road")) {
                            //get road content
                            road = getContent(parser, "road");
                        } else if (tagName.equals("latitude")) {
                            //get latitude content
                            latitude = getContent(parser, "latitude");
                        } else if (tagName.equals("longitude")) {
                            //get longitude content
                            longitude = getContent(parser, "longitude");
                        } else if (tagName.equals("eventStart")) {
                            //get eventStart content
                            eventStart = getContent(parser, "eventStart");
                        } else if (tagName.equals("eventEnd")) {
                            //get eventEnd content
                            eventEnd = getContent(parser, "eventEnd");
                        } else {
                            //Skip the tag
                            skipTag(parser);
                        }
                    }
                    CurrentIncidents incidents = new CurrentIncidents(title, description, road, latitude, longitude, eventStart, eventEnd);
                    incidentarray.add(incidents);

                }else {
                    //Skip the tag
                    skipTag(parser);
                }
            }
        }
    }


    private void skipTag (XmlPullParser parser) throws XmlPullParserException, IOException{
        Log.d(TAG, "skipTag: skipping: " + parser.getName());
        if (parser.getEventType() != XmlPullParser.START_TAG){
            throw new IllegalStateException();
        }

        int num = 1;
        while (num !=0){
            switch (parser.next()){
                case XmlPullParser.START_TAG:
                    num ++;
                    break;
                case XmlPullParser.END_TAG:
                    num--;
                    break;
                default:
                    break;
            }
        }
    }

    private String getContent (XmlPullParser parser, String tagName){
        try {
            parser.require(XmlPullParser.START_TAG, null, tagName);

            String content = "";

            if (parser.next() == XmlPullParser.TEXT){
                content = parser.getText();
                parser.next();

            }
            return content;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return null;

    }


    private InputStream getInputStream (){
        Log.d(TAG, "getInputStream: started");
        try {
            URL url = new URL("http://m.highwaysengland.co.uk/feeds/rss/AllEvents.xml");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            return connection.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}