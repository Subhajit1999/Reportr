package com.sk.quantumsudio.projectq.headline.LiveNews;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sk.quantumsudio.projectq.headline.R;

import java.util.ArrayList;

public class ListPageAdapter extends BaseAdapter {
    //this class is response for adding the items to the list from the arrayLists
    private static final String TAG = "OptionsPageAdapter";

    private int fragmentId;
    private Context context;
    private ArrayList<Integer> listImages;   // ItemsImage arrayList
    private ArrayList<String> listOptions;   // ItemsText arrayList
    private ArrayList<String> listLanguages;  //itemsLanguage arrayList

    public ListPageAdapter(Context context, ArrayList<Integer> listImages, ArrayList<String> listOptions,
                           ArrayList<String> listLanguages,int fragMentId) {
        Log.d(TAG, "ListPageAdapter: LiveNewsAdapter constructor");
        this.context = context;
        this.listImages = listImages;
        this.listOptions = listOptions;
        this.fragmentId = fragMentId;
        if (fragmentId==0){
            this.listLanguages = listLanguages;
        }
    }
    //Overriden methods
    @Override
    public int getCount() {
        return listOptions.size();
    }
    @Override
    public Object getItem(int position) {
        return listOptions.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView: inside of getView of LiveNewsAdapter");

        if (convertView == null && fragmentId==0){  //connects elements with the elements layout
            convertView = View.inflate(context,R.layout.livelist_elementlayout,null);   //for liveNews fragment

        }else if (convertView==null && fragmentId==1){
            convertView = View.inflate(context,R.layout.optionlist_elementlayout,null);  //for Options fragment
        }
        if (fragmentId==0){
            ImageView iv_live_logo = (ImageView) convertView.findViewById(R.id.iv_live_logo);  /*Imageview and TextView
                                                                                               of the elements layout
                                                                                              initialization */
            TextView tv_live_title = (TextView) convertView.findViewById(R.id.tv_live_title);
            TextView tv_live_language = (TextView) convertView.findViewById(R.id.tv_live_language);
            ImageView playIcon = convertView.findViewById(R.id.iv_live_play);
            playIcon.setVisibility(View.VISIBLE);  //makes the play icon visible(Optional but recommended)

            iv_live_logo.setImageResource(listImages.get(position));
            tv_live_title.setText(listOptions.get(position));
            tv_live_language.setText(listLanguages.get(position));
        }else if (fragmentId==1){
            ImageView iv_elements = convertView.findViewById(R.id.iv_option_logo);
            TextView tv_elements = convertView.findViewById(R.id.tv_option_title);

            iv_elements.setImageResource(listImages.get(position));
            tv_elements.setText(listOptions.get(position));
        }
        return convertView;
    }
}
