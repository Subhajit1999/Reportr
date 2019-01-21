package com.sk.quantumsudio.projectq.headline.headlines;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sk.quantumsudio.projectq.headline.R;

public class CategoriesFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "CategoriesFragment";
    static final String API_KEY = "dab6659cd2744a02987fe068bcb70002";  // ### NEWS API key HERE ###

    Button entertainment,science,technology,global,business,health,sports;
    TextView categories,newsSources,bbc,google,nat_geo,hindu,usaToday,telegraph;
    String url,category;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Initialing fragment view of"+TAG);
        return inflater.inflate(R.layout.fragment_categories,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: onActivityCreated of" + TAG);
        super.onActivityCreated(savedInstanceState);

        onInitWidgets();
    }
    public void onInitWidgets(){
        categories = getActivity().findViewById(R.id.tv_categories);  //textview and button widgets initialization
        newsSources = getActivity().findViewById(R.id.tv_newssource);
        entertainment = getActivity().findViewById(R.id.entertainment);
        science = getActivity().findViewById(R.id.science);
        technology = getActivity().findViewById(R.id.technology);
        global = getActivity().findViewById(R.id.global);
        business = getActivity().findViewById(R.id.business);
        health = getActivity().findViewById(R.id.health);
        sports = getActivity().findViewById(R.id.sports);
        bbc = getActivity().findViewById(R.id.bbc_news);
        google = getActivity().findViewById(R.id.google_news);
        nat_geo = getActivity().findViewById(R.id.national_geo);
        hindu = getActivity().findViewById(R.id.the_hindu);
        usaToday = getActivity().findViewById(R.id.usa_today);
        telegraph = getActivity().findViewById(R.id.telegraph);

        entertainment.setOnClickListener(this); //setting up button click with onClick listener
        science.setOnClickListener(this);
        technology.setOnClickListener(this);
        global.setOnClickListener(this);
        business.setOnClickListener(this);
        health.setOnClickListener(this);
        sports.setOnClickListener(this);
        bbc.setOnClickListener(this);
        google.setOnClickListener(this);
        nat_geo.setOnClickListener(this);
        hindu.setOnClickListener(this);
        usaToday.setOnClickListener(this);
        telegraph.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        Log.d(TAG, "onCreateView: onClick action of"+TAG);

        Bundle bundle = new Bundle();
        switch(view.getId()){

            case R.id.entertainment:
                url = "https://newsapi.org/v2/top-headlines?country=in&category=entertainment&apiKey="+API_KEY;
                category = "Entertainment";
                break;
            case R.id.science:
                url = "https://newsapi.org/v2/top-headlines?country=in&category=science&apiKey="+API_KEY;
                category = "Science";
                break;
            case R.id.technology:
                url = "https://newsapi.org/v2/top-headlines?country=in&category=technology&apiKey="+API_KEY;
                category = "Technology";
                break;
            case R.id.global:
                url = "https://newsapi.org/v2/top-headlines?country=us&apiKey="+API_KEY;
                category = "Global";
                break;
            case R.id.business:
                url = "https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey="+API_KEY;
                category = "Business";
                break;
            case R.id.health:
                url = "https://newsapi.org/v2/top-headlines?country=in&category=health&apiKey="+API_KEY;
                category = "Health";
                break;
            case R.id.sports:
                url = "https://newsapi.org/v2/top-headlines?country=in&category=sports&apiKey="+API_KEY;
                category = "Sports";
                break;
            case R.id.bbc_news:
                url = "https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey="+API_KEY;
                category = "BBC News";
                break;
            case R.id.google_news:
                url = "https://newsapi.org/v2/top-headlines?sources=google-news&apiKey="+API_KEY;
                category = "Google News";
                break;
            case R.id.national_geo:
                url = "https://newsapi.org/v2/top-headlines?sources=national-geographic&apiKey="+API_KEY;
                category = "National Geographic";
                break;
            case R.id.the_hindu:
                url = "https://newsapi.org/v2/top-headlines?sources=the-hindu&apiKey="+API_KEY;
                category = "The Hindu";
                break;
            case R.id.usa_today:
                url = "https://newsapi.org/v2/top-headlines?sources=usa-today&apiKey="+API_KEY;
                category = "USA Today";
                break;
            case R.id.telegraph:
                url = "https://newsapi.org/v2/top-headlines?sources=the-telegraph&apiKey="+API_KEY;
                category = "The Telegraph";
                break;
        }
        bundle.putString("newsUrl",url);
        bundle.putString("category",category);
        MainFragment mainFragment = new MainFragment();
        mainFragment.setArguments(bundle);  //passing the bundle data to the fragment
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.navigation_fragment_container,
                mainFragment).commit();
    }
}
