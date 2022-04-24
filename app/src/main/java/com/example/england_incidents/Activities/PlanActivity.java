package com.example.england_incidents.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.england_incidents.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Margaret Maina s1906597
 */

public class PlanActivity extends AppCompatActivity {

    public static String getDate(long milliSeconds, String dateFormat) {

        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);


        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planner_dialog);


    }
}
