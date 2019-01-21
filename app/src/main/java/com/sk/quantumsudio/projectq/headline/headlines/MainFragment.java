package com.sk.quantumsudio.projectq.headline.headlines;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sk.quantumsudio.projectq.headline.DetailsActivity;
import com.sk.quantumsudio.projectq.headline.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";
    public static String newsUrl,category,savedUrl,savedCategory;
    ListView listNews;
    ProgressBar loader;
    Toolbar toolbar;
    ImageView iv_categories;

    //arrayList of fetched data from the API
    ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
    static final String KEY_TITLE = "title";
    static final String KEY_URL = "url";
    static final String KEY_URLTOIMAGE = "urlToImage";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Initialing fragment view of"+TAG);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            //retrieving the passed data from bundle
            newsUrl = bundle.getString("newsUrl");
            category = bundle.getString("category");
        } else {
            newsUrl = "https://newsapi.org/v2/top-headlines?country=in&apiKey=dab6659cd2744a02987fe068bcb70002";
            category = "Latest Indian News";
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: onActivityCreated of"+TAG);
        super.onActivityCreated(savedInstanceState);
        toolbar = getActivity().findViewById(R.id.toolbar_newslist);
        iv_categories = getActivity().findViewById(R.id.image_categories);
        listNews = getActivity().findViewById(R.id.listNews);
        loader = getActivity().findViewById(R.id.loader);
        listNews.setEmptyView(loader);

        if (Function.isNetworkAvailable(getContext())) {
            MainFragment.DownloadNews newsTask = new MainFragment.DownloadNews();

            newsTask.execute();
        } else {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }
        //category image Button click action
        iv_categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //navigate to CategoriesFragment
                CategoriesFragment categoriesFragment = new CategoriesFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.navigation_fragment_container,
                        categoriesFragment).commit();
            }
        });
    }

     @SuppressLint("StaticFieldLeak")
     class DownloadNews extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            String xml = "";
            String urlParameters = "";
            xml = Function.excuteGet(newsUrl, urlParameters);
            return xml;
        }

        @Override
        protected void onPostExecute(String xml) {

            try {
                JSONObject jsonResponse = new JSONObject(xml);
                JSONArray jsonArray = jsonResponse.optJSONArray("articles");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(KEY_TITLE, jsonObject.optString(KEY_TITLE).toString());
                    map.put(KEY_URL, jsonObject.optString(KEY_URL).toString());
                    map.put(KEY_URLTOIMAGE, jsonObject.optString(KEY_URLTOIMAGE).toString());
                    dataList.add(map);
                }
            } catch (JSONException e) {
                Toast.makeText(getContext(), "Unexpected error", Toast.LENGTH_SHORT).show();
            }
            ListNewsAdapter adapter = new ListNewsAdapter(getActivity(), dataList);
            listNews.setAdapter(adapter);

            listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Intent i = new Intent(getContext(), DetailsActivity.class);
                    i.putExtra("url", dataList.get(+position).get(KEY_URL));
                    i.putExtra("fragmentSelectId",0);
                    i.putExtra("category",category);
                    startActivity(i);
                }
            });
        }
    }
}
