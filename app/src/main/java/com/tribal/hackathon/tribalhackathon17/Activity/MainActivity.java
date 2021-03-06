package com.tribal.hackathon.tribalhackathon17.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.mindorks.placeholderview.ExpandablePlaceHolderView;
import com.tribal.hackathon.tribalhackathon17.Fragment.swipe_image;
import com.tribal.hackathon.tribalhackathon17.Helper.BottomNavigationViewHelper;
import com.tribal.hackathon.tribalhackathon17.Helper.ConnectivityReceiver;
import com.tribal.hackathon.tribalhackathon17.NewsFeed.Feed;
import com.tribal.hackathon.tribalhackathon17.NewsFeed.HeadingView;
import com.tribal.hackathon.tribalhackathon17.NewsFeed.Info;
import com.tribal.hackathon.tribalhackathon17.NewsFeed.InfoView;
import com.tribal.hackathon.tribalhackathon17.NewsFeed.Utils;
import com.tribal.hackathon.tribalhackathon17.R;
import com.tribal.hackathon.tribalhackathon17.Schemes.View.BottomNavigationSchemeActivity;
import com.tribal.hackathon.tribalhackathon17.Schemes.View.SearchResult.SearchResultActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    private TextView mTextMessage;
    private ExpandablePlaceHolderView mExpandableView;
    private Context mContext;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_scheme:
                    intent = new Intent(MainActivity.this, BottomNavigationSchemeActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_account:
                    intent = new Intent(MainActivity.this, AccountActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_settings:
                    intent = new Intent(MainActivity.this,Settings.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        Configuration config = getBaseContext().getResources().getConfiguration();

        String lang = settings.getString("LANG", "");
        Log.d("Ayush",lang);
        if (! "".equals(lang) && ! config.locale.getLanguage().equals(lang)) {
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                FragmentTransaction top_fragment = getSupportFragmentManager().beginTransaction();
                swipe_image frag = new swipe_image();
                top_fragment.replace(R.id.top_placeholder,frag);
                top_fragment.commit();
            }
        });

        mContext = this.getApplicationContext();
        mExpandableView = (ExpandablePlaceHolderView)findViewById(R.id.expandableView);
        for(Feed feed : Utils.loadFeeds(this.getApplicationContext())){
            mExpandableView.addView(new HeadingView(mContext, feed.getHeading()));
            for(Info info : feed.getInfoList()){
                mExpandableView.addView(new InfoView(mContext, info));
            }
        }

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