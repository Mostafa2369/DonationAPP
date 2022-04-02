package com.example.android.donationapp.View.Adapters;



import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.donationapp.Model.EmergencyCase;
import com.example.android.donationapp.R;



public class EmergencyAdapter extends RecyclerView.Adapter<EmergencyAdapter.EmergencyAdapterViewHolder> {
    public EmergencyCase[] data;
    private TextView mEmergencyName;



    private  EmergencyAdapterOnClickHandler mClickHandler=null;
    public EmergencyAdapter (EmergencyAdapterOnClickHandler click) {
        mClickHandler = click;
    }

    public interface EmergencyAdapterOnClickHandler
    {

        void onClick(int hello);
    }

    public class EmergencyAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public EmergencyAdapterViewHolder(View itemView) {
            super(itemView);
            mEmergencyName = itemView.findViewById(R.id.emergency_name);
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
    public EmergencyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.emergency_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        return new EmergencyAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmergencyAdapterViewHolder holder, int position) {
        EmergencyCase new1 = data[position];
        mEmergencyName.setText(new1.getTile());


    }



    @Override
    public int getItemCount() {
        return data.length;
    }

    public void getData(EmergencyCase[] data1) {

        data=data1;

        notifyDataSetChanged();

    }







}
