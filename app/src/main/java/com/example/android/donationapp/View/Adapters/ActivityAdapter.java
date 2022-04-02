package com.example.android.donationapp.View.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.donationapp.Model.CharityActivity;
import com.example.android.donationapp.R;



public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityAdapterViewHolder> {
    public CharityActivity[] data;
    private TextView mActivityName;



    private  ActivityAdapterOnClickHandler mClickHandler=null;
    public ActivityAdapter (ActivityAdapterOnClickHandler click) {
        mClickHandler = click;
    }

    public interface ActivityAdapterOnClickHandler
    {

        void onClick(int hello);
    }

    public class ActivityAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public ActivityAdapterViewHolder(View itemView) {
            super(itemView);
            mActivityName = itemView.findViewById(R.id.activity_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int mAdapterPosition = getAdapterPosition();
            mClickHandler.onClick(mAdapterPosition);

        }
    }

    @NonNull
    @Override
    public ActivityAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.activity_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        return new ActivityAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityAdapterViewHolder holder, int position) {
        CharityActivity new1 = data[position];
        mActivityName.setText(new1.getName());


    }



    @Override
    public int getItemCount() {
        return data.length;
    }

    public void getData(CharityActivity[] data1) {

        data=data1;

        notifyDataSetChanged();

    }







}
