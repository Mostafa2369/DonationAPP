package com.example.android.donationapp.View.Donor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.donationapp.Model.CharityActivity;
import com.example.android.donationapp.R;
import com.example.android.donationapp.View.Adapters.ActivityAdapter3;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewActivities extends AppCompatActivity implements ActivityAdapter3.ActivityAdapter3OnClickHandler{

    private DatabaseReference databaseCharityActivity = FirebaseDatabase.getInstance().getReference("CharityActivity");
    private CharityActivity mCharityActivity;
    private CharityActivity[] mCharityActivity_list;
    private ActivityAdapter3 mAdapter;
    private RecyclerView mView;
    private  boolean enter;
    private String charityId;
    private String donorId;
    private String charityName;
    @Override
    public void onClick(final int mActivityPosition) {
        enter = true;
        databaseCharityActivity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int i = 0;
                mCharityActivity_list = new CharityActivity[(int) dataSnapshot.getChildrenCount()];
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    mCharityActivity= snap.getValue(CharityActivity.class);
                    if(mCharityActivity.getCharityID().equals(charityId))
                    {
                        mCharityActivity_list[i] = new CharityActivity();
                        mCharityActivity_list[i] =mCharityActivity;
                        ++i;
                    }
                }
                CharityActivity [] list = new CharityActivity[i];
                for(int e = 0 ;e<i;++e)
                {
                    list[e]=new CharityActivity();
                    list[e]=mCharityActivity_list[e];

                }
                if(enter){

                    enter=false;
                    Intent intent = new Intent(ViewActivities.this, DonationPage.class);
                    intent.putExtra("ActivityName",list[mActivityPosition].getName());
                    intent.putExtra("ActivityId",list[mActivityPosition].getCharityActivityID());
                    intent.putExtra("charityId",charityId);
                    intent.putExtra("charityName",charityName);
                    intent.putExtra("donorId",donorId);
                    startActivity(intent);


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }});}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_activities);
        Intent in = getIntent();
        charityId=in.getStringExtra("charityId");
        charityName=in.getStringExtra("charityName");
        donorId=in.getStringExtra("donorId");

        databaseCharityActivity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Intent in = getIntent();
                charityId=in.getStringExtra("charityId");
                int i = 0;
                mCharityActivity_list = new CharityActivity[(int) dataSnapshot.getChildrenCount()];
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    mCharityActivity= snap.getValue(CharityActivity.class);

                    if(mCharityActivity.getCharityID().equals(charityId))
                    {
                        mCharityActivity_list[i] = new CharityActivity();
                        mCharityActivity_list[i] =mCharityActivity;
                        ++i;
                    }
                }
                CharityActivity [] list = new CharityActivity[i];
                for(int e = 0 ;e<i;++e)
                {
                    list[e]=new CharityActivity();
                    list[e]=mCharityActivity_list[e];

                }

                mView = (RecyclerView) findViewById(R.id.activity_recycle3);
                mView.setLayoutManager(new GridLayoutManager(ViewActivities.this, 1));
                mAdapter = new ActivityAdapter3(ViewActivities.this);
                mAdapter.getData(list);
                mView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

}
