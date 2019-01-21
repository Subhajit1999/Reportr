package com.sk.quantumsudio.projectq.headline.options;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sk.quantumsudio.projectq.headline.DetailsActivity;
import com.sk.quantumsudio.projectq.headline.R;
import com.sk.quantumsudio.projectq.headline.headlines.MainFragment;

public class AboutFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "AboutFragment";

    TextView tv_about,tv_terms,tv_privacy,tv_version,toolbarTitle;
    ImageView toolbarShare, toolbarIcon;
    String url,page_name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_options_about,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tv_about = getActivity().findViewById(R.id.tv_about);   //widgets initialization
        tv_terms = getActivity().findViewById(R.id.tv_tAndC);
        tv_privacy = getActivity().findViewById(R.id.tv_privacyP);
        tv_version = getActivity().findViewById(R.id.tv_version);
        toolbarTitle = getActivity().findViewById(R.id.text_view);
        toolbarShare = getActivity().findViewById(R.id.iv_share);
        toolbarIcon = getActivity().findViewById(R.id.iv_icon);

        toolbarIcon.setImageResource(R.drawable.ic_arrow_back);  //sets about icon
        toolbarShare.setVisibility(View.GONE);   //hides share button
        toolbarTitle.setText(R.string.about_this_app);    // sets the title

        tv_terms.setOnClickListener(this);   //setting click listener
        tv_privacy.setOnClickListener(this);

        toolbarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.navigation_fragment_container,
                        new OptionsFragment()).commit();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.tv_tAndC:
                url = "file:///android_asset/terms_and_conditions.html";
                page_name = "Terms and Conditions";
                break;
            case R.id.tv_privacyP:
                url = "file:///android_asset/privacy_policy.html";
                page_name = "Privacy Policy";
                break;
        }
        Intent i = new Intent(getContext(),DetailsActivity.class);
        i.putExtra("fragmentSelectId",2);
        i.putExtra("URL",url);
        i.putExtra("name",page_name);
        startActivity(i);
    }
}
