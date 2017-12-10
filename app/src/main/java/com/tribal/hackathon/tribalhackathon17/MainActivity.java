package com.tribal.hackathon.tribalhackathon17;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tribal.hackathon.tribalhackathon17.Schemes.View.BottomNavigationSchemeActivity;
import com.tribal.hackathon.tribalhackathon17.Schemes.View.SearchResult.SearchResultActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadaccnt(View v) {
        Intent i = new Intent(this, BottomNavigationSchemeActivity.class);
        startActivity(i);
    }

    public void loadsearchresult(View v) {
        Intent i = new Intent(this, SearchResultActivity.class);
        startActivity(i);
    }
}