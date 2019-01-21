package com.sk.quantumsudio.projectq.headline;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = "DetailsActivity";
    WebView webView;
    ProgressBar loader;
    int id;
    String URL = "",name="";
    ImageView share;
    TextView toolbarTitle;
    ImageView toolbarIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onKeyDown: onCreate of"+TAG);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        share = findViewById(R.id.iv_share);  //share ImageButton action
        toolbarTitle = findViewById(R.id.text_view); //toolbar title textView and icon imageview
        toolbarIcon = findViewById(R.id.iv_icon);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //click action
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                if(id==0){  //for main fragment
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey!! I found this amazing news on \"Reportr\"-News reading android app. Have a look at this-\n"+URL);
                }else if(id==1){  //for newpaper fragment
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey!! I found this amazing news on today's "+name+" newspaper. Have a look at this news-\n"+URL);
                }
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Share News through..."));
            }
        });

        Intent intent = getIntent();
        id = intent.getIntExtra("fragmentSelectId",3);
        if(id==0){
            URL = intent.getStringExtra("url");     //from headlines/main fragment
            name = intent.getStringExtra("category");
            toolbarTitle.setText(name);
        }else if(id==1){
            URL = intent.getStringExtra("Url");   //from newsPapers fragment
            name = intent.getStringExtra("paperName");
            toolbarTitle.setText(name);
        }else if (id==2){                             //from optionsfragment about section
            URL = intent.getStringExtra("URL");
            name = intent.getStringExtra("name");
            share.setVisibility(View.GONE);
            toolbarTitle.setText(name);
        }else{
            URL ="";
            toolbarTitle.setVisibility(View.GONE);
        }
        toolbarIcon.setVisibility(View.GONE);
        loader = (ProgressBar) findViewById(R.id.loader);
        webView = (WebView) findViewById(R.id.webView);


        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setJavaScriptEnabled(true);  //can cause error, in case
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl(URL);


        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    loader.setVisibility(View.GONE);
                } else {
                    loader.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyDown: SystembackButtonHandler of"+TAG);
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private class MyWebViewClient extends WebViewClient {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            if (Objects.equals(Uri.parse(URL).getHost(), URL)) {
                //Open url contents in Webview
                return false;
            }
            return super.shouldOverrideUrlLoading(view,request);
        }
    }
}

