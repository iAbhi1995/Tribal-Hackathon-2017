package com.tribal.hackathon.tribalhackathon17.Schemes.View;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.tribal.hackathon.tribalhackathon17.Activity.AccountActivity;
import com.tribal.hackathon.tribalhackathon17.Activity.MainActivity;
import com.tribal.hackathon.tribalhackathon17.Activity.Settings;
import com.tribal.hackathon.tribalhackathon17.Helper.BottomNavigationViewHelper;
import com.tribal.hackathon.tribalhackathon17.Helper.DataBaseHandler;
import com.tribal.hackathon.tribalhackathon17.R;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.MockSchemeProvider;
import com.tribal.hackathon.tribalhackathon17.Schemes.Presenter.PresenterImpl;
import com.tribal.hackathon.tribalhackathon17.Schemes.Presenter.SchemePresenter;

public class BottomNavigationSchemeActivity extends AppCompatActivity implements SchemeView {

    ProgressBar pBar;
    private Intent intent;
    private DataBaseHandler db;
    private SchemePresenter presenter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    intent = new Intent(BottomNavigationSchemeActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_scheme:
                    intent = new Intent(BottomNavigationSchemeActivity.this, BottomNavigationSchemeActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_account:
                    intent = new Intent(BottomNavigationSchemeActivity.this, AccountActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_settings:
                    intent = new Intent(BottomNavigationSchemeActivity.this,Settings.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.TEXT_ALIGNMENT_CENTER);
        }
        setContentView(R.layout.activity_scheme);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        pBar = (ProgressBar) findViewById(R.id.progressBar);
        db = new DataBaseHandler(getApplicationContext());
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
                return false;
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