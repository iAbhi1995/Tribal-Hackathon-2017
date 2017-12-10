package com.tribal.hackathon.tribalhackathon17.Schemes.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.tribal.hackathon.tribalhackathon17.Helper.DataBaseHandler;
import com.tribal.hackathon.tribalhackathon17.R;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Department;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Places;
import com.tribal.hackathon.tribalhackathon17.Schemes.View.SearchResult.SearchResultActivity;

import java.util.List;

public class PlacesDepartmentAdapter extends RecyclerView.Adapter<PlacesDepartmentAdapter.MyViewHolder> {

    private final DataBaseHandler db;
    public Context context;
    public List<Places> places;
    public List<Department> departments;
    public int type;

    public PlacesDepartmentAdapter(Context context, int type) {
        this.context = context;
        db = new DataBaseHandler(context);
        this.places = db.getPlaces();
        this.departments = db.getDepartments();
        this.type = type;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scheme_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Places place = null;
        Department department = null;
        if (type == 1)//for places
        {
            place = places.get(position);
            holder.name.setText(place.getName());

        } else//for departments
        {
            department = departments.get(position);
            holder.name.setText(department.getName());
        }

        final Places finalPlace = place;
        final Department finalDepartment = department;
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SearchResultActivity.class);
                if (type == 1)
                    i.putExtra("_id", finalPlace.getId());
                else
                    i.putExtra("_id", finalDepartment.getId());
                context.startActivity(i);
            }
        });
        if(holder.dept.getText()==null)
        {
            holder.dept.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if (type == 1) return places.size();
        else return departments.size();
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