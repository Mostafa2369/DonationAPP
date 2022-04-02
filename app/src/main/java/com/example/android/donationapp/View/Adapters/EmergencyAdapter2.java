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



public class EmergencyAdapter2 extends RecyclerView.Adapter<EmergencyAdapter2.EmergencyAdapter2ViewHolder> {
    public EmergencyCase[] data;
    private TextView mEmergencyName;
private TextView getmEmergencyAmount;
private TextView mDesc;


    private  EmergencyAdapter2OnClickHandler mClickHandler=null;
    public EmergencyAdapter2 (EmergencyAdapter2OnClickHandler click) {
        mClickHandler = click;
    }

    public interface EmergencyAdapter2OnClickHandler
    {

        void onClick(int hello);
    }

    public class EmergencyAdapter2ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public EmergencyAdapter2ViewHolder(View itemView) {
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
    public EmergencyAdapter2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.emergency_item2;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        return new EmergencyAdapter2ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmergencyAdapter2ViewHolder holder, int position) {
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
