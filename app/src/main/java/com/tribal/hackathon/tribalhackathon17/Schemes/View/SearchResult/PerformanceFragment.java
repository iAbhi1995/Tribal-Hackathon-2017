package com.tribal.hackathon.tribalhackathon17.Schemes.View.SearchResult;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tribal.hackathon.tribalhackathon17.Helper.Urls;
import com.tribal.hackathon.tribalhackathon17.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerformanceFragment extends Fragment {


    public PerformanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_performance, container, false);
        WebView myWebView = (WebView) v.findViewById(R.id.webView);
        //WebView.getSettings().setJavaScriptEnabled(true);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl(Urls.Base_Url + "/api/graph/222/546");
        myWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
            }
        });
        return v;
    }
}
