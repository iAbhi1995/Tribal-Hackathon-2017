package com.tribal.hackathon.tribalhackathon17.Schemes.View.SearchResult;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.tribal.hackathon.tribalhackathon17.Helper.DataBaseHandler;
import com.tribal.hackathon.tribalhackathon17.R;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Places;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Schemes;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.SearchResult;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.MockSchemeProvider;
import com.tribal.hackathon.tribalhackathon17.Schemes.Presenter.PresenterImpl;
import com.tribal.hackathon.tribalhackathon17.Schemes.Presenter.SchemePresenter;
import com.tribal.hackathon.tribalhackathon17.Schemes.View.PlacesFragment;
import com.tribal.hackathon.tribalhackathon17.Schemes.View.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity implements SearchResultView {

    private static String url;
    private static String id;
    String data = "1";
    private DataBaseHandler db;
    private SchemePresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        db = new DataBaseHandler(this);
        Bundle bundle = intent.getExtras();
        data = bundle.getString("intent_extra_data_key");
        Log.d("abhi", "" + data);
        if (data.charAt(0) == 'P') {
            url = data.substring(1);
        }
        presenter = new PresenterImpl(new MockSchemeProvider(), this, this);
        presenter.searchByPlace(data.substring(1));
        setUpViewPager();
        /*if (bundle != null) {
            for (String key : bundle.keySet()) {
                Object value = bundle.get(key);
                Log.d("abhi", String.format("%s %s (%s)", key,
                        value.toString(), value.getClass().getName()));
            }
        }*/
        //TOdo: to check appropriate data to decide between 2 or 3 tabs in the search result...
    }

    private void setUpViewPager() {
        Fragment schm, perfm;
        schm = new SchemeFragment();
        perfm = new PerformanceFragment();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(perfm);
        adapter.addFragment(schm);
        adapter.addFragment(new PlacesFragment());
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Performance");
        tabLayout.getTabAt(1).setText("Schemes");
        if (!data.equals("1")) {
            tabLayout.getTabAt(2).setText("Sub-levels");
        }
    }

    @Override
    public void showProgressBar(boolean b) {

    }

    @Override
    public void showMessage(String success) {

    }

    @Override
    public void displayResult(SearchResult body) {
        long used = body.getUsed_amount(), allocated = body.getAllocated_amount();
        List<Places.Place> places = new ArrayList<>();
        List<Schemes.SchemeData> schemeDatas = new ArrayList<>();
        for (int i = 0; i < body.getAllocations().size(); i++) {
            SearchResult.allocation allocation = body.getAllocations().get(i);
            Schemes.SchemeData scheme = new Schemes.SchemeData(allocation.getId(), allocation.getScheme_name(), "", allocation.getUsed_amount() + "", allocation.getAllocated_amount() + "", "");
            schemeDatas.add(scheme);
        }
        db.addSchemes(schemeDatas);
        for (int i = 0; i < body.getSub_levels().size(); i++) {
            SearchResult.sub_level sub_level = body.getSub_levels().get(i);
            Places.Place place = new Places.Place(sub_level.getId(), sub_level.getName(), "", "");
            places.add(place);
        }
        db.addPlaces(places);
        setUpViewPager();
    }
}