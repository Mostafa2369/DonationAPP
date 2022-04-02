package com.example.android.donationapp.View.Donor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.android.donationapp.Model.Charity;
import com.example.android.donationapp.R;
import com.example.android.donationapp.View.Adapters.CharityAdapter3;
import com.example.android.donationapp.View.HomePage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DonorView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CharityAdapter3.CharityAdapter3OnClickHandler {
    private DatabaseReference databaseCharity= FirebaseDatabase.getInstance().getReference("Charity");
    private Charity mCharity;
    private Charity []mCharity_list;
    private  boolean enter=false;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private RecyclerView mView;
    private CharityAdapter3 mAdapter;
    private boolean enter3=false;
    private String donorId;

    @Override
    public void onClick(final int mCharityPosition) {
        enter = true;
        databaseCharity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int i = 0;
                mCharity_list = new Charity[(int) dataSnapshot.getChildrenCount()];
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    mCharity_list[i] = new Charity();
                    mCharity= snap.getValue(Charity.class);
                    mCharity_list[i] =mCharity;
                    ++i;
                }
                if(enter){

                    enter=false;
                    Intent intent = new Intent(DonorView.this, ViewActivities.class);
                    intent.putExtra("charityId",mCharity_list[mCharityPosition].getCharityID());
                    intent.putExtra("charityName",mCharity_list[mCharityPosition].getName());
                    intent.putExtra("donorId",donorId);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }});

    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_view);
        Intent in = getIntent();
        donorId=in.getStringExtra("donorId");


    Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");

    setSupportActionBar(toolbar);

    drawer = findViewById(R.id.drawer_layout34);

    navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setCheckedItem(R.id.view_charities);

        mView = (RecyclerView) findViewById(R.id.charity_recycle3);
        mView.setLayoutManager(new GridLayoutManager(DonorView.this, 1));

        enter3=true;
        databaseCharity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int i = 0;
                mCharity_list = new Charity[(int) dataSnapshot.getChildrenCount()];
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    mCharity_list[i] = new Charity();
                    mCharity= snap.getValue(Charity.class);
                    mCharity_list[i] =mCharity;
                    ++i;
                }
                if(enter3){
                    mAdapter = new CharityAdapter3(DonorView.this);
                    mAdapter.getData(mCharity_list);
                    mView.setAdapter(mAdapter);
                    enter3=false;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }});







    }
    @Override
    protected void onResume() {
        navigationView.setCheckedItem(R.id.view_charities);
        super.onResume();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.view_charities:

                break;
            case R.id.view_profile:
                Intent intent1;
                intent1 = new Intent(DonorView.this, ViewProfile.class);
                intent1.putExtra("donorId",donorId);
                startActivity(intent1);
                break;
            case R.id.view_cases:
                Intent intent2;
                intent2 = new Intent(DonorView.this, ViewCases.class);
                intent2.putExtra("donorId",donorId);
                startActivity(intent2);
                break;
            case R.id.view_report:
                Intent intent4;
                intent4 = new Intent(DonorView.this, DonorReport.class);
                intent4.putExtra("donorId",donorId);
                startActivity(intent4);

                break;
            case R.id.zakah_measure:
                Intent intent5;
                intent5 = new Intent(DonorView.this, ZakahMeasurment.class);
                intent5.putExtra("donorId",donorId);
                startActivity(intent5);
                break;
            case R.id.sign_out:
                Intent intent = new Intent(this, HomePage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                break;

        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }





}
