package com.tribal.hackathon.tribalhackathon17.Schemes.View.SearchResult;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.tribal.hackathon.tribalhackathon17.Helper.DataBaseHandler;
import com.tribal.hackathon.tribalhackathon17.Helper.Urls;
import com.tribal.hackathon.tribalhackathon17.R;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Schemes;

import java.util.List;

public class SchemeAdapter extends RecyclerView.Adapter<SchemeAdapter.MyViewHolder> {

    private final DataBaseHandler db;
    public Context context;
    public List<Schemes.SchemeData> schemeList;

    public SchemeAdapter(Context context) {
        this.context = context;
        db = new DataBaseHandler(context);
        this.schemeList = db.getSchemes();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scheme_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Schemes.SchemeData data = schemeList.get(position);
        holder.name.setText(data.getName());
        holder.dept.setText(data.getDept_name());
        if(holder.dept.getText()!=null)
        {
            holder.dept.setVisibility(View.VISIBLE);
        }
        WebView myWebView = holder.performance;
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl(Urls.Base_Url + "/api/graph/" + 560 + "/" + 770);
        Log.d("abhi", Urls.Base_Url + "/api/graph/" + 567 + "/" + 770);
        myWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
            }
        });

        /*Picasso.with(context).load(pg.getImage_url2()).into(holder.img, new com.squareup.picasso.Callback() {

            @Override
            public void onSuccess() {
                App.hideProgressBar(holder.progressBar);
            }

            @Override
            public void onError() {
                Log.d("abhi", "Error in image loading");
                App.hideProgressBar(holder.progressBar);
            }
        });
        if (favList.contains(pg.getHostel_id()))
            holder.favButton.setImageResource(R.drawable.red_heart);
        else
            holder.favButton.setImageResource(R.drawable.heart_outline);*/

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return schemeList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView name;
        public final WebView performance;
        public final TextView dept;


        public MyViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            name = (TextView) itemView.findViewById(R.id.name);
            dept = (TextView) itemView.findViewById(R.id.dept);
            performance = (WebView) itemView.findViewById(R.id.webView);
        }
    }
}