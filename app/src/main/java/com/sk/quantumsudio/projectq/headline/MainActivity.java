package com.sk.quantumsudio.projectq.headline;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.sk.quantumsudio.projectq.headline.newspapers.NewsPaperFragment;
import com.sk.quantumsudio.projectq.headline.LiveNews.LiveNewsFragment;
import com.sk.quantumsudio.projectq.headline.headlines.MainFragment;
import com.sk.quantumsudio.projectq.headline.options.OptionsFragment;

import utils.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    BottomNavigationViewEx bottomNavigationViewEx; //custom bottomNavigationBar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Inside OnCreate of MainActivity");
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationViewEx = findViewById(R.id.bottom_navigation_view);
        //responsible for bottom navigation options fragments view
        bottomNavigationViewEx.setOnNavigationItemSelectedListener(nav_listener);

        //sets the Mainfragment as the initial view of the application
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.navigation_fragment_container,
                    new MainFragment()).commit();
        }

        customNavigationView();  //calls the custom bottomNavigationBar setup method
    }

    // setting up custom BottomNavigationViewBar
    public void customNavigationView() {
        Log.d(TAG, "customNavigationView: Inside customNavigationView method");

        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
    }

    BottomNavigationViewEx.OnNavigationItemSelectedListener nav_listener = new BottomNavigationViewEx.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            Fragment selectedFragment=null;

            switch (item.getItemId()) {  //Links the fragments with the NavigationBar options
                case R.id.bottom_navigation_top:
                    selectedFragment = new MainFragment();
                    break;
                case R.id.bottom_navigation_cat:
                    selectedFragment = new NewsPaperFragment();
                    break;
                case R.id.bottom_navigation_livenews:
                    selectedFragment = new LiveNewsFragment();
                    break;
                case R.id.bottom_navigation_options:
                    selectedFragment = new OptionsFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.navigation_fragment_container,
                    selectedFragment).commit();         //commits the current fragment transaction
            return true;
        }
    };
}

