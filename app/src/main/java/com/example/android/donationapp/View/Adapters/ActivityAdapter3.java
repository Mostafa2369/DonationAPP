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



public class ActivityAdapter3 extends RecyclerView.Adapter<ActivityAdapter3.ActivityAdapter3ViewHolder> {
    public CharityActivity[] data;
    private TextView mActivityName;
    private TextView  mActivityDesc;


    private  ActivityAdapter3OnClickHandler mClickHandler=null;
    public ActivityAdapter3 (ActivityAdapter3OnClickHandler click) {
        mClickHandler = click;
    }

    public interface ActivityAdapter3OnClickHandler
    {

        void onClick(int hello);
    }

    public class ActivityAdapter3ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public ActivityAdapter3ViewHolder(View itemView) {
            super(itemView);
            mActivityName = itemView.findViewById(R.id.activity_title);
            mActivityDesc = itemView.findViewById(R.id.description4);
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
    public ActivityAdapter3ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.actvity_item_2;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        return new ActivityAdapter3ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityAdapter3ViewHolder holder, int position) {
        CharityActivity new1 = data[position];
        mActivityName.setText(new1.getName());
        mActivityDesc.setText(new1.getDescription());
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
