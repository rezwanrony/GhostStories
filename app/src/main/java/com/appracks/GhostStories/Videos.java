package com.appracks.GhostStories;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Videos extends AppCompatActivity {
    private ProgressDialog pDialog;
    WebView videos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideos);
        pDialog = new ProgressDialog(this);
        if(isInternetConneted()){
            pDialog.setMessage("Please wait. Loading...");
            pDialog.show();
            videos=(WebView)findViewById(R.id.wv_videos);
            videos.getSettings().setJavaScriptEnabled(true);
            videos.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }

                public void onLoadResource (WebView view, String url) {

                }
                public void onPageFinished(WebView view, String url) {
                    try{
                        if (pDialog.isShowing()) {
                            pDialog.dismiss();
                        }
                    }catch(Exception exception){
                    }
                }

            });
            videos.loadUrl("file:///android_asset/yt.html");

        }else{
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
            alertDialog.setTitle("No Internet Available. Try Again Later...");
            alertDialog.setCancelable(false);
            alertDialog.setIcon(R.mipmap.ic_launcher);
            alertDialog.setPositiveButton("BACK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    onBackPressed();
                }
            });
            alertDialog.setNegativeButton("CLOSE", null);
            alertDialog.show();
        }
    }

    public boolean isInternetConneted(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        }
        return false;
    }
}
