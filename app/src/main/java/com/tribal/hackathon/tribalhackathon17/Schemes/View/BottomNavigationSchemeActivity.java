package com.tribal.hackathon.tribalhackathon17.Schemes.View;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;

import com.tribal.hackathon.tribalhackathon17.R;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.MockSchemeProvider;
import com.tribal.hackathon.tribalhackathon17.Schemes.Presenter.PresenterImpl;
import com.tribal.hackathon.tribalhackathon17.Schemes.Presenter.SchemePresenter;
import com.tribal.hackathon.tribalhackathon17.helper.DataBaseHandler;

public class BottomNavigationSchemeActivity extends AppCompatActivity implements SchemeView {

    ProgressBar pBar;
    private DataBaseHandler db;
    private SchemePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pBar = (ProgressBar) findViewById(R.id.progressBar);
        db = new DataBaseHandler(getApplicationContext());
        db.addInitialEntries("1", "Abhi");
        db.addInitialEntries("7", "Abhi1");
        db.addInitialEntries("6", "Abhi2");
        db.addInitialEntries("5", "Abhi3");
        db.addInitialEntries("4", "Abhi4");
        db.addInitialEntries("3", "Abhi5");
        db.addInitialEntries("2", "Abhi6");
        presenter = new PresenterImpl(new MockSchemeProvider(), this, this);
        presenter.getScheme();
        setUpViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        if (searchView == null) {
            Log.d("abhi", "nit running");
            return true;
        }
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryRefinementEnabled(true);
        searchView.setQueryHint("Search by Schemes or Place name..");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                showMessage("No match found for " + query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                showMessage("onQueryTextChange " + newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public void showProgressBar(boolean b) {
        if (b)
            pBar.setVisibility(View.VISIBLE);
        else
            pBar.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(findViewById(R.id.layout_scheme), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void setView() {
        setUpViewPager();
    }

    private void setUpViewPager() {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PlacesFragment());
        adapter.addFragment(new DepartmentFragment());
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Places");
        tabLayout.getTabAt(1).setText("Departments");
    }
}