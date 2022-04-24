package com.example.england_incidents;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailedIncidents extends AppCompatActivity {
    private static CurrentIncidents currentIncidents;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);
        System.out.println("Entering detailed activity");
        currentIncidents = (CurrentIncidents) getIntent().getSerializableExtra("incidents");
        System.out.println(currentIncidents);

    }
}
