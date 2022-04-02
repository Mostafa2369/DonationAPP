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

public class CharityAdapter3 extends RecyclerView.Adapter<CharityAdapter3.CharityAdapter3ViewHolder> {
    public Charity[] data;
    private TextView mCharityName;



    private  CharityAdapter3OnClickHandler mClickHandler=null;
    public CharityAdapter3(CharityAdapter3OnClickHandler click) {
        mClickHandler = click;
    }

    public interface CharityAdapter3OnClickHandler
    {

        void onClick(int hello);
    }

    public class CharityAdapter3ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public CharityAdapter3ViewHolder(View itemView) {
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
    public CharityAdapter3ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.charity_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        return new CharityAdapter3ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharityAdapter3ViewHolder holder, int position) {
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
