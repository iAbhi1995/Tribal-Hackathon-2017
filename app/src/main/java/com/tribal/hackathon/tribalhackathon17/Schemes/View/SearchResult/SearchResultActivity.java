package com.tribal.hackathon.tribalhackathon17.Schemes.View.SearchResult;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tribal.hackathon.tribalhackathon17.Activity.AccountActivity;
import com.tribal.hackathon.tribalhackathon17.Activity.MainActivity;
import com.tribal.hackathon.tribalhackathon17.Activity.Settings;
import com.tribal.hackathon.tribalhackathon17.Helper.BottomNavigationViewHelper;
import com.tribal.hackathon.tribalhackathon17.R;
import com.tribal.hackathon.tribalhackathon17.Schemes.View.BottomNavigationSchemeActivity;
import com.tribal.hackathon.tribalhackathon17.Schemes.View.PlacesFragment;
import com.tribal.hackathon.tribalhackathon17.Schemes.View.SectionsPagerAdapter;

public class SearchResultActivity extends AppCompatActivity {

    String data = "1";
    private Intent intent;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    intent = new Intent(SearchResultActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_scheme:
                    intent = new Intent(SearchResultActivity.this, BottomNavigationSchemeActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_account:
                    intent = new Intent(SearchResultActivity.this, AccountActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_settings:
                    intent = new Intent(SearchResultActivity.this,Settings.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        //    data = getIntent().getStringExtra("_id");
        //        Log.d("abhi", data);
        //TOdo: to check appropriate data to decide between 2 or 3 tabs in the search result...
        //    data = getIntent().getExtras().toString();
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