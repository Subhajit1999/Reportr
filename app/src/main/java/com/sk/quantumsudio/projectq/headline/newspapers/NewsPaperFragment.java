package com.sk.quantumsudio.projectq.headline.newspapers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sk.quantumsudio.projectq.headline.DetailsActivity;
import com.sk.quantumsudio.projectq.headline.R;

public class NewsPaperFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "NewsPaperFragment";

    ImageView toi,telegraph,statesman,tnyt,guardian,china,bhaskar,jagran,hindustan,abp,bartaman,pratidin;
    TextView tv_english,tv_hindi,tv_bengali;
    ImageView share,toolbarIcon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Initializing fragment view of"+TAG);
        return inflater.inflate(R.layout.fragment_newspapers,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: onActivityCreated of"+TAG);
        super.onActivityCreated(savedInstanceState);

        initializedViews();  //calling function
        share.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {   //Click action of newsPapers
        Log.d(TAG, "onClick: Inside onClick of "+TAG);
        String url = null,paperName=null;

       switch(view.getId()){

           case R.id.iv_toi:
               url = "http://timesofindia.indiatimes.com";
               paperName = "The Times of India";
               break;
           case R.id.iv_telegraph:
               url = "http://www.telegraphindia.com";
               paperName = "The Telegraph";
               break;
           case R.id.iv_statesman:
               url = "http://www.thestatesman.com";
               paperName = "The Statesman";
               break;
           case R.id.iv_tnyt:
               url = "http://www.nytimes.com";
               paperName = "The New York Times";
               break;
           case R.id.iv_guardian:
               url = "http://www.theguardian.com";
               paperName = "The Guardian";
               break;
           case R.id.iv_china:
               url = "http://www.chinatoday.com.cn/english";
               paperName = "China Today";
               break;
           case R.id.iv_bhaskar:
               url = "http://www.bhaskar.com";
               paperName = "Dainik Bhaskar";
               break;
           case R.id.iv_jagran:
               url = "http://www.jagran.com";
               paperName = "Dainik Jagran";
               break;
           case R.id.iv_hindustan:
               url = "https://www.livehindustan.com";
               paperName = "Hindustan";
               break;
           case R.id.iv_abp:
               url = "http://www.anandabazar.com";
               paperName = "AnandaBazar Patrika";
               break;
           case R.id.iv_bartaman:
               url = "http://bartamanpatrika.com";
               paperName = "Bartaman";
               break;
           case R.id.iv_pratidin:
               url = "http://www.sangbadpratidin.in";
               paperName = "Sangbad Pratidin";
               break;
       }
        Intent i = new Intent(getContext(), DetailsActivity.class);  //Navigating to the webPage activity through Intent
        i.putExtra("Url", url);
        i.putExtra("fragmentSelectId",1);
        i.putExtra("paperName",paperName);
        startActivity(i);
    }
    public void initializedViews(){
        toi= getActivity().findViewById(R.id.iv_toi);  //Imageview and Textview widget Initialization
        telegraph = getActivity().findViewById(R.id.iv_telegraph);
        statesman = getActivity().findViewById(R.id.iv_statesman);
        tnyt = getActivity().findViewById(R.id.iv_tnyt);
        guardian = getActivity().findViewById(R.id.iv_guardian);
        china = getActivity().findViewById(R.id.iv_china);
        bhaskar = getActivity().findViewById(R.id.iv_bhaskar);
        jagran = getActivity().findViewById(R.id.iv_jagran);
        hindustan = getActivity().findViewById(R.id.iv_hindustan);
        abp = getActivity().findViewById(R.id.iv_abp);
        bartaman = getActivity().findViewById(R.id.iv_bartaman);
        pratidin = getActivity().findViewById(R.id.iv_pratidin);
        tv_english = getActivity().findViewById(R.id.tv_english);
        tv_hindi = getActivity().findViewById(R.id.tv_hindi);
        tv_bengali = getActivity().findViewById(R.id.tv_bengali);
        share = getActivity().findViewById(R.id.iv_share);  //share image button
        toolbarIcon = getActivity().findViewById(R.id.iv_icon);

        toi.setOnClickListener(this);  //setting up the click action
        telegraph.setOnClickListener(this);
        statesman.setOnClickListener(this);
        tnyt.setOnClickListener(this);
        guardian.setOnClickListener(this);
        china.setOnClickListener(this);
        bhaskar.setOnClickListener(this);
        jagran.setOnClickListener(this);
        hindustan.setOnClickListener(this);
        abp.setOnClickListener(this);
        bartaman.setOnClickListener(this);
        pratidin.setOnClickListener(this);
        toolbarIcon.setImageResource(R.drawable.ic_newspapers);  //sets the toolbar icon
    }
}
