package com.tribal.hackathon.tribalhackathon17.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.squareup.picasso.Picasso;
import com.tribal.hackathon.tribalhackathon17.Helper.App;
import com.tribal.hackathon.tribalhackathon17.R;

public class swipe_pager_Adapter extends PagerAdapter {

    private Context mContext;
    private String[] mResources;

    public swipe_pager_Adapter(Context mContext,String[] mResources) {
        this.mContext = mContext;
        this.mResources = mResources;
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((FrameLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.img_pager_item);
        final ProgressBar progressBar = (ProgressBar) itemView.findViewById(R.id.progressbar);
        Picasso.with(mContext)
                .load(mResources[position])
                .into(imageView, new com.squareup.picasso.Callback() {

                    @Override
                    public void onSuccess() {
                        App.hideProgressBar(progressBar);
                    }

                    @Override
                    public void onError() {
                        App.hideProgressBar(progressBar);
                        //Toast.makeText(AppController.getInstance().getApplicationContext(), "Error Loading Image!", Toast.LENGTH_LONG).show();
                    }
                });;

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }
}