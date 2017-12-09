package com.tribal.hackathon.tribalhackathon17.Fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tribal.hackathon.tribalhackathon17.Adapter.swipe_pager_Adapter;
import com.tribal.hackathon.tribalhackathon17.R;

import java.util.Timer;
import java.util.TimerTask;

public class swipe_image extends Fragment implements ViewPager.OnPageChangeListener ,View.OnClickListener{


    int currentPage = 0;
    private OnFragmentInteractionListener mListener;
    private LinearLayout pager_indicator;
    private ViewPager intro_images;
    private int dotsCount;
    private ImageView[] dots;
    private swipe_pager_Adapter mAdapter;
    public ImageButton btnNext, btnPrevious;
    Timer timer;
    private String[] mImageResources;

    public swipe_image() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.swipe_image, container, false);
        intro_images = (ViewPager) view.findViewById(R.id.viewpager);
        pager_indicator = (LinearLayout) view.findViewById(R.id.viewPagerCountDots);

            mImageResources = new String[]{
                    "http://images.shiksha.com/mediadata/images/1455102832php8CNi5r.jpeg",
                    "http://images.shiksha.com/mediadata/images/1455102832php8CNi5r.jpeg",
                    "http://images.shiksha.com/mediadata/images/1455102832php8CNi5r.jpeg",
                    "http://images.shiksha.com/mediadata/images/1455102832php8CNi5r.jpeg",
                    "http://images.shiksha.com/mediadata/images/1455102832php8CNi5r.jpeg"
            };

        mAdapter = new swipe_pager_Adapter(getActivity(), mImageResources);
        btnNext = (ImageButton)view.findViewById(R.id.btn_next);
        btnPrevious= (ImageButton)view.findViewById(R.id.btn_previous);
        btnNext.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);

        intro_images.setAdapter(mAdapter);
        intro_images.setCurrentItem(0);
        intro_images.setOnPageChangeListener(this);
        setUiPageViewController();
        intro_images.setAdapter(new swipe_pager_Adapter(getActivity(),mImageResources));


        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == 5) {
                    currentPage = 0;
                }
                intro_images.setCurrentItem(currentPage++, true);
            }
        };


        timer = new Timer(); // This will create a new Thread
        timer .schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, 500, 4000);
        return view;
    }
    private void setUiPageViewController() {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
        btnPrevious.setVisibility(View.INVISIBLE);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Activity activity = getActivity();
        if(isAdded() && activity!=null)
        {

            btnNext.setVisibility(View.VISIBLE);
            btnPrevious.setVisibility(View.INVISIBLE);
            for (int i = 0; i < dotsCount; i++) {
                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
            }

            dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
            if(position==4)
            {
                btnNext.setVisibility(View.INVISIBLE);
            }
            if(position!=0)
            {
                btnPrevious.setVisibility(View.VISIBLE);
            }

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                intro_images.setCurrentItem((intro_images.getCurrentItem() < dotsCount)
                        ? intro_images.getCurrentItem() + 1 : 0);
                break;

            case R.id.btn_previous:
                intro_images.setCurrentItem((intro_images.getCurrentItem() > 0)
                        ? intro_images.getCurrentItem() - 1 : 0);
                break;
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
