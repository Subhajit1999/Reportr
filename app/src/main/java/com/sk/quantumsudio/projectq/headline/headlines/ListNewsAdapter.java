package com.sk.quantumsudio.projectq.headline.headlines;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sk.quantumsudio.projectq.headline.R;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

class ListNewsAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;

    public ListNewsAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
    }
    public int getCount() {
        return data.size();
    }
    public Object getItem(int position) {
        return position;
    }
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ListNewsViewHolder holder = null;
        if (convertView == null) {
            holder = new ListNewsViewHolder();
            convertView = LayoutInflater.from(activity).inflate(
                    R.layout.list_row, parent, false);
            holder.newsImage = (ImageView) convertView.findViewById(R.id.newsImage);
            holder.newsTitle = (TextView) convertView.findViewById(R.id.news_title);
            holder.category = convertView.findViewById(R.id.tv_category);
            convertView.setTag(holder);
        } else {
            holder = (ListNewsViewHolder) convertView.getTag();
        }
        holder.newsImage.setId(position);
        holder.newsTitle.setId(position);

        HashMap<String, String> details = new HashMap<>();
        details = data.get(position);

        try{
            holder.newsTitle.setText(details.get(MainFragment.KEY_TITLE));
            holder.category.setText(MainFragment.category);

            if(details.get(MainFragment.KEY_URLTOIMAGE).toString().length() < 5)
            {
                holder.newsImage.setImageResource(R.drawable.default_image);
                holder.newsImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }else{
                Picasso.with(activity)
                        .load(details.get(MainFragment.KEY_URLTOIMAGE).toString())
                        .resize(300, 300)
                        .into(holder.newsImage);
            }
        }catch(Exception e) {}
        return convertView;
    }
}

class ListNewsViewHolder {
    ImageView newsImage;
    TextView newsTitle,category;
}
