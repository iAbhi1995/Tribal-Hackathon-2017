package com.tribal.hackathon.tribalhackathon17.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tribal.hackathon.tribalhackathon17.Helper.BottomNavigationViewHelper;
import com.tribal.hackathon.tribalhackathon17.R;
import com.tribal.hackathon.tribalhackathon17.Schemes.View.BottomNavigationSchemeActivity;

import java.util.Locale;

public class Settings extends AppCompatActivity {

    RadioGroup radiolanguagegroup;
    RadioButton radioButton;
    Button save;
    View view ;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    intent = new Intent(Settings.this,MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_scheme:
                    intent = new Intent(Settings.this,BottomNavigationSchemeActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_account:
                    intent = new Intent(Settings.this,AccountActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_settings:
                    intent = new Intent(Settings.this,Settings.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.title_settings);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        languagelistener();
        view = (LinearLayout)findViewById(R.id.container);

    }

    public void languagelistener()
    {
        radiolanguagegroup = (RadioGroup)findViewById(R.id.radioLanguage);
        save = (Button) findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radiolanguagegroup.getCheckedRadioButtonId();
                //Log.d("ayush", String.valueOf(selectedId));
                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                if(radioButton==(RadioButton)findViewById(R.id.englishButton)) //English
                {
                    Toast.makeText(getApplicationContext(),"Language Changed Successfully",
                    Toast.LENGTH_SHORT).show();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("LANG", "en").commit();
                    setLangRecreate("en");
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"हिंदी भाषा को सफलतापूर्वक चुना |",
                            Toast.LENGTH_SHORT).show();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("LANG", "hi").commit();
                    setLangRecreate("hi");
                }
            }
        });
    }
    public void setLangRecreate(String langval) {
        Configuration config = getBaseContext().getResources().getConfiguration();
        Locale locale = new Locale(langval);
        Locale.setDefault(locale);
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }

}
