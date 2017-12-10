package com.tribal.hackathon.tribalhackathon17.Schemes.View.SearchResult;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tribal.hackathon.tribalhackathon17.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SchemeFragment extends Fragment {


    private RecyclerView recyclerView;

    public SchemeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_scheme, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        SchemeAdapter adapter = new SchemeAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        return v;
    }
}