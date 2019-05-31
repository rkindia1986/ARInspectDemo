package com.optimumus.arinspectdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.optimumus.arinspectdemo.factdatascreeen.FactDataView;
import com.optimumus.arinspectdemo.models.FactDataList;

public class MainActivity extends AppCompatActivity implements FactDataView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void UpdateListView(FactDataList f) {

    }

    @Override
    public void displayMessage(String s, boolean isfinish) {

    }
}
