package com.sk.quantumsudio.projectq.headline.options;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sk.quantumsudio.projectq.headline.LiveNews.ListPageAdapter;
import com.sk.quantumsudio.projectq.headline.LiveNews.PlayerActivity;
import com.sk.quantumsudio.projectq.headline.R;
import com.sk.quantumsudio.projectq.headline.headlines.MainFragment;

import java.util.ArrayList;

public class OptionsFragment extends Fragment {
    private static final String TAG = "OptionsFragment";

    ArrayList<Integer> listImages;  //arraylist of list data
    ArrayList<String> listOptions;
    ImageView toolbarIcon,share;
    TextView toolbarTitle;
    ListView listViewOptions;

    ListPageAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_options,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        toolbarIcon = getActivity().findViewById(R.id.iv_icon);
        toolbarTitle = getActivity().findViewById(R.id.text_view);
        share = getActivity().findViewById(R.id.iv_share);
        listViewOptions = getActivity().findViewById(R.id.listview_options);

        share.setVisibility(View.GONE);  //sets the views to toolbar
        toolbarIcon.setImageResource(R.drawable.ic_settings);
        toolbarTitle.setText(R.string.options);

        listImages = new ArrayList<>();     //defining the Arraylists of data
        listOptions = new ArrayList<>();
        listImages = getListImages();
        listOptions = getListOptions();                 //2nd listOptions arg. has no use
        adapter = new ListPageAdapter(getContext(),listImages,listOptions,listOptions,1);
        listViewOptions.setAdapter(adapter);

        onListElementclick();  //sets the list element click function
    }
    public ArrayList<Integer> getListImages () {   //options images list  (add more to show more options)
        Log.d(TAG, "getListImages: ListImages Arraylist");
        listImages = new ArrayList<>();
        listImages.add(R.drawable.options_share);          //index 0
        listImages.add(R.drawable.options_rate);    //index 1
        listImages.add(R.drawable.options_contact);        //index 2
        listImages.add(R.drawable.options_feedback);  //index 3
        listImages.add(R.drawable.options_about);      //index 4

        return listImages;
    }
    public ArrayList<String> getListOptions () {    //options texts list (add more to show more options)
        Log.d(TAG, "getListOptions: listTitles Arraylists");
        listOptions = new ArrayList<>();
        listOptions.add("Share");  //index 0
        listOptions.add("Rate the app");  //index 1
        listOptions.add("Contact developer");     //index 2
        listOptions.add("Feedback/Suggestion");//index 3
        listOptions.add("About this app");    //index 4

        return listOptions;
    }
    private void onListElementclick(){
        Log.d(TAG, "onListElementclick: options elements click functions");
        //For click action to every options of the list
        listViewOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){                  //passes the videoIds to the player
                    case 0:                                 //share button

                        String url = "http://play.google.com/store/apps/details?id=" + getContext().getPackageName();
                        Intent shareIntent = new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND);
                        shareIntent.putExtra(Intent.EXTRA_TEXT,"Hey! I found this amazing NEWS application " +
                                "\"Reportr\" on the play store. Please check this out: "+url);
                        shareIntent.setType("text/plain");
                        startActivity(Intent.createChooser(shareIntent,"Share App through..."));
                        break;
                    case 1:                                            //rate button

                        Uri uri = Uri.parse("market://details?id=" + getContext().getPackageName());
                        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                        // To count with Play market backstack, After pressing back button,
                        // to taken back to our application, we need to add following flags to intent.
                        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        try {
                            startActivity(goToMarket);
                        } catch (Exception e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://play.google.com/store/apps/details?id=" + getContext().getPackageName())));
                        }
                        break;
                    case 2:                            //contact button

                        Intent contact = new Intent(Intent.ACTION_SEND);
                        contact.setData(Uri.parse("mailto:"));
                        String[] to = {"developercontact.subhajitkar@gmail.com"};
                        contact.putExtra(Intent.EXTRA_EMAIL,to);
                        contact.putExtra(Intent.EXTRA_SUBJECT,"Contacting to see your App");
                        contact.putExtra(Intent.EXTRA_TEXT,"Write what you want...");
                        contact.setType("message/rfc822");
                        startActivity(Intent.createChooser(contact,"Send Email with..."));
                        break;
                    case 3:                              //feedback/suggestion button

                        Intent feedback = new Intent(Intent.ACTION_SEND);
                        feedback.setData(Uri.parse("mailto:"));
                        String[] emailTo = {"developercontact.subhajitkar@gmail.com"};
                        feedback.putExtra(Intent.EXTRA_EMAIL,emailTo);
                        feedback.putExtra(Intent.EXTRA_SUBJECT,"feedback/Suggestion related "+getString(R.string.app_name)+"Android app");
                        feedback.putExtra(Intent.EXTRA_TEXT,"Write what you want...");
                        feedback.setType("message/rfc822");
                        startActivity(Intent.createChooser(feedback,"Send Email with..."));
                        break;
                    case 4:                         //about button
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.navigation_fragment_container,
                                new AboutFragment()).commit();
                        break;
                }
            }
        });
    }
}
