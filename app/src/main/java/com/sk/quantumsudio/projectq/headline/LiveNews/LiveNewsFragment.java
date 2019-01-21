package com.sk.quantumsudio.projectq.headline.LiveNews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sk.quantumsudio.projectq.headline.R;

import java.util.ArrayList;

public class LiveNewsFragment extends Fragment {
    private static final String TAG = "LiveNewsFragment";

    ImageView iv_share,toolbarIcon;
    TextView tv_title;
    ListView listlive;
    String newsId;

    ListPageAdapter adapter;  // live news list adapter

    ArrayList<Integer> listImages;  //arraylist of list data
    ArrayList<String> listOptions;
    ArrayList<String> listLanguages;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_livenews,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: Inside onActivityCreated of liveNewsfragment");
        super.onActivityCreated(savedInstanceState);

        tv_title = getActivity().findViewById(R.id.text_view);  //widgets initialization
        iv_share = getActivity().findViewById(R.id.iv_share);
        listlive = getActivity().findViewById(R.id.livenews_list);
        toolbarIcon = getActivity().findViewById(R.id.iv_icon);
        iv_share.setVisibility(View.GONE);  //hides the share image
        tv_title.setText(R.string.live_toolbar_title);  //sets title
        toolbarIcon.setImageResource(R.drawable.ic_live_news);  //sets the toolbar icon

        listImages = new ArrayList<>();     //defining the Arraylists of data
        listOptions = new ArrayList<>();
        listLanguages = new ArrayList<>();
        listImages = getListImages();
        listOptions = getListOptions();
        listLanguages = getListLanguages();

        adapter = new ListPageAdapter(getContext(),listImages,listOptions,listLanguages,0);
        listlive.setAdapter(adapter);   //linking views with the adapter

        onListElementclick();  //click function on the listView
    }
    public ArrayList<Integer> getListImages () {   //options images list    (add more to show more options)
        Log.d(TAG, "getListImages: ListImages Arraylist");
        listImages = new ArrayList<>();
        listImages.add(R.drawable.live_ndtv_english);  //index 0
        listImages.add(R.drawable.live_dwtv);          //index 1
        listImages.add(R.drawable.live_ndtv_india);    //index 2
        listImages.add(R.drawable.live_ddnews);        //index 3
        listImages.add(R.drawable.live_news18_india);  //index 4
        listImages.add(R.drawable.live_india_tv);      //index 5
        listImages.add(R.drawable.live_abp_ananda);    //index 6

        return listImages;
    }
    public ArrayList<String> getListOptions () {    //options texts list   (add more to show more options)
        Log.d(TAG, "getListOptions: listTitles Arraylists");
        listOptions = new ArrayList<>();
        listOptions.add("NDTV 24x7");   //index 0
        listOptions.add("DW Tv live");  //index 1
        listOptions.add("NDTV India");  //index 2
        listOptions.add("DD News");     //index 3
        listOptions.add("News18 India");//index 4
        listOptions.add("India Tv");    //index 5
        listOptions.add("ABP Ananda");  //index 6
        return listOptions;
    }
    public ArrayList<String> getListLanguages () {    //options texts list   (add more to show more options)
        Log.d(TAG, "getListLanguages: listLanguages Arraylists");
        listLanguages = new ArrayList<>();
        listLanguages.add("English");  //index 0
        listLanguages.add("English");  //index 1
        listLanguages.add("Hindi");    //index 2
        listLanguages.add("Hindi");    //index 3
        listLanguages.add("Hindi");    //index 4
        listLanguages.add("Hindi");    //index 5
        listLanguages.add("Bengali");  //index 6
        return listLanguages;
    }
    private void onListElementclick(){
        Log.d(TAG, "onListElementclick: elements click functions");
        //For click action to every options of the list
        listlive.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){                  //passes the videoIds to the player
                    case 0:
                        newsId = "xSVljOUB8m4";
                        break;
                    case 1:
                        newsId = "m5Nst2zMZVY";
                        break;
                    case 2:
                        newsId = "CbktwWHKJxw";
                        break;
                    case 3:
                        newsId = "3tzc-UYV9iE";
                        break;
                    case 4:
                        newsId = "OpdDdJWIccU";
                        break;
                    case 5:
                        newsId = "ePa1g9hgjgM";
                        break;
                    case 6:
                        newsId = "vYipw_KipIo";
                        break;
                }
                Intent intent = new Intent(getActivity(), PlayerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("liveNewsId",newsId);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}