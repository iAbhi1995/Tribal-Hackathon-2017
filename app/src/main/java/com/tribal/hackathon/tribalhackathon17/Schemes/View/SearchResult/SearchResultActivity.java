package com.tribal.hackathon.tribalhackathon17.Schemes.View.SearchResult;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tribal.hackathon.tribalhackathon17.R;
import com.tribal.hackathon.tribalhackathon17.Schemes.View.PlacesFragment;
import com.tribal.hackathon.tribalhackathon17.Schemes.View.SectionsPagerAdapter;

public class SearchResultActivity extends AppCompatActivity {

    String data = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TOdo: to check appropriate data to decide between 2 or 3 tabs in the search result...
        //data=getIntent().getExtras().toString();


        setUpViewPager();

    }

    private void setUpViewPager() {
        Fragment schm, perfm;
        schm = new SchemeFragment();
        perfm = new PerformanceFragment();
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(perfm);
        adapter.addFragment(schm);
        if (data.equals("1")) {
            adapter.addFragment(new PlacesFragment());
        }
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Performance");
        tabLayout.getTabAt(1).setText("Schemes");
        if (data.equals("1")) {
            tabLayout.getTabAt(2).setText("Sub-levels");
        }
    }
}