package com.example.android.donationapp.View.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.donationapp.Model.Charity;
import com.example.android.donationapp.R;

public class CharityAdapter extends RecyclerView.Adapter<CharityAdapter.CharityAdapterViewHolder> {
    public Charity[] data;
    private TextView mCharityName;



    private  CharityAdapterOnClickHandler mClickHandler=null;
    public CharityAdapter(CharityAdapterOnClickHandler click) {
        mClickHandler = click;
    }

    public interface CharityAdapterOnClickHandler
    {

        void onClick(int hello);
    }

    public class CharityAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public CharityAdapterViewHolder(View itemView) {
            super(itemView);
            mCharityName = itemView.findViewById(R.id.charity_name);
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
    public CharityAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.charity_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        return new CharityAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharityAdapterViewHolder holder, int position) {
        Charity new1 = data[position];
        mCharityName.setText(new1.getName());


    }



    @Override
    public int getItemCount() {
        return data.length;
    }

    public void getData(Charity[] data1) {

        data=data1;

        notifyDataSetChanged();

    }







}
