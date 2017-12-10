package com.tribal.hackathon.tribalhackathon17.Schemes.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tribal.hackathon.tribalhackathon17.R;


public class DepartmentFragment extends Fragment {

    public DepartmentFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_department, container, false);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        PlacesDepartmentAdapter adapter = new PlacesDepartmentAdapter(getActivity(), 2);
        recyclerView.setAdapter(adapter);
        return v;
    }

}
