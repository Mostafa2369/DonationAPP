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
import com.example.android.donationapp.Model.EmergencyCase;
import com.example.android.donationapp.R;
import com.example.android.donationapp.View.Adapters.EmergencyAdapter3;
import com.example.android.donationapp.View.HomePage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewCases extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, EmergencyAdapter3.EmergencyAdapter3OnClickHandler {
    private boolean enter3=false;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private String donorId;
    private EmergencyCase mEmergencyCase;
    private EmergencyCase[] mEmergencyCase_list;
    private DatabaseReference databaseEmergencyCase = FirebaseDatabase.getInstance().getReference("EmergencyCase");
    private EmergencyAdapter3 mAdapter;
    boolean enter2 = false;
    private RecyclerView mView;
    boolean enter=false;
    private DatabaseReference databaseCharity= FirebaseDatabase.getInstance().getReference("Charity");
    private Charity mCharity;
    private Charity []mCharity_list;
    private String charityName;

    @Override
    public void onClick(final int mActivityPosition) {
        enter = true;
        databaseEmergencyCase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int i = 0;
                mEmergencyCase_list = new EmergencyCase[(int) dataSnapshot.getChildrenCount()];
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    mEmergencyCase= snap.getValue(EmergencyCase.class);

                        mEmergencyCase_list[i] = new EmergencyCase();
                        mEmergencyCase_list[i] =mEmergencyCase;
                        ++i;
                }
                if(enter){


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
                                if(mCharity.getCharityID().equals(mEmergencyCase_list[mActivityPosition].getCharityID()))
                                {
                                    charityName=mCharity.getName();
                                    break;
                                }
                                ++i;
                            }
                            if(enter3){

                                enter3=false;
                                enter=false;
                                Intent intent = new Intent(ViewCases.this, CaseDonation.class);
                                intent.putExtra("ActivityId",mEmergencyCase_list[mActivityPosition].getEmergencyCaseID());
                                intent.putExtra("ActivityName",mEmergencyCase_list[mActivityPosition].getTile());
                                intent.putExtra("charityId",mEmergencyCase_list[mActivityPosition].getCharityID());
                                intent.putExtra("charityName",charityName);
                                intent.putExtra("amount",mEmergencyCase_list[mActivityPosition].getAmount());
                                intent.putExtra("donorId",donorId);
                                startActivity(intent);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }});
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }});

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cases);
        Intent in = getIntent();
        donorId = in.getStringExtra("donorId");


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout81);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setCheckedItem(R.id.view_cases);



        mView = (RecyclerView) findViewById(R.id.emergency_recycle20);
        mView.setLayoutManager(new GridLayoutManager(ViewCases.this, 1));


        enter2 = true;
        databaseEmergencyCase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int i = 0;
                mEmergencyCase_list = new EmergencyCase[(int) dataSnapshot.getChildrenCount()];
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    mEmergencyCase= snap.getValue(EmergencyCase.class);

                        mEmergencyCase_list[i] = new EmergencyCase();
                        mEmergencyCase_list[i] =mEmergencyCase;
                        ++i;
                    }

                if(enter2){
                    mAdapter = new EmergencyAdapter3(ViewCases.this);
                    mAdapter.getData(mEmergencyCase_list);
                    mView.setAdapter(mAdapter);
                    enter2=true;
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }});







    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.view_charities:
                Intent intent1;
                intent1 = new Intent(ViewCases.this, DonorView.class);
                intent1.putExtra("donorId", donorId);
                startActivity(intent1);
                break;
            case R.id.view_profile:
                Intent intent3;
                intent3 = new Intent(ViewCases.this, ViewProfile.class);
                intent3.putExtra("donorId",donorId);
                startActivity(intent3);
                break;
            case R.id.view_cases:

                break;
            case R.id.view_report:

                Intent intent4;
                intent4 = new Intent(ViewCases.this, DonorReport.class);
                intent4.putExtra("donorId",donorId);
                startActivity(intent4);
                break;
            case R.id.zakah_measure:
                Intent intent5;
                intent5 = new Intent(ViewCases.this, ZakahMeasurment.class);
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
    protected void onResume() {
        navigationView.setCheckedItem(R.id.view_cases);
        super.onResume();
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


}}
