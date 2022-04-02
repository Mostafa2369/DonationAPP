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



public class EmergencyAdapter3 extends RecyclerView.Adapter<EmergencyAdapter3.EmergencyAdapter3ViewHolder> {
    public EmergencyCase[] data;
    private TextView mEmergencyName;
    private TextView getmEmergencyAmount;
     private TextView mDesc;

    private  EmergencyAdapter3OnClickHandler mClickHandler=null;
    public EmergencyAdapter3 (EmergencyAdapter3OnClickHandler click) {
        mClickHandler = click;
    }

    public interface EmergencyAdapter3OnClickHandler
    {

        void onClick(int hello);
    }

    public class EmergencyAdapter3ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public EmergencyAdapter3ViewHolder(View itemView) {
            super(itemView);
            mEmergencyName = itemView.findViewById(R.id.emergency_name);
            getmEmergencyAmount=itemView.findViewById(R.id.emergency_amount);
            mDesc=itemView.findViewById(R.id.emergency_desc2);
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
    public EmergencyAdapter3ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.emergency_item2;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        return new EmergencyAdapter3ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmergencyAdapter3ViewHolder holder, int position) {
        EmergencyCase new1 = data[position];
        mEmergencyName.setText(new1.getTile());
        mDesc.setText(new1.getDescription());
        getmEmergencyAmount.setText("Total amount: "+new1.getAmount());


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
