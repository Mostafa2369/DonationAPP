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



public class ActivityAdapter2 extends RecyclerView.Adapter<ActivityAdapter2.ActivityAdapter2ViewHolder> {
    public CharityActivity[] data;
    private TextView mActivityName;



    private ActivityAdapter2OnClickHandler mClickHandler=null;
    public ActivityAdapter2(ActivityAdapter2OnClickHandler click) {
        mClickHandler = click;
    }

    public interface ActivityAdapter2OnClickHandler
    {

        void onClick(int hello);
    }

    public class ActivityAdapter2ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public ActivityAdapter2ViewHolder(View itemView) {
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
    public ActivityAdapter2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.activity_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        return new ActivityAdapter2ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityAdapter2ViewHolder holder, int position) {
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
