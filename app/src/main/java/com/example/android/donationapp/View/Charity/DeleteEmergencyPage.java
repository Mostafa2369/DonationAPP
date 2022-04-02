package com.example.android.donationapp.View.Charity;

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

import com.example.android.donationapp.Model.EmergencyCase;
import com.example.android.donationapp.R;
import com.example.android.donationapp.View.Adapters.EmergencyAdapter;
import com.example.android.donationapp.View.HomePage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeleteEmergencyPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, EmergencyAdapter.EmergencyAdapterOnClickHandler {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    boolean enter=false;
    private boolean add=false;
    private boolean read=false;
    private String charityId;
    private EmergencyCase mEmergencyCase;
    private EmergencyCase[] mEmergencyCase_list;
    private DatabaseReference databaseEmergencyCase = FirebaseDatabase.getInstance().getReference("EmergencyCase");
    private EmergencyAdapter mAdapter;
    boolean enter2 = false;
    private RecyclerView mView;

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
                    if(mEmergencyCase.getCharityID().equals(charityId))
                    {
                        mEmergencyCase_list[i] = new EmergencyCase();
                        mEmergencyCase_list[i] =mEmergencyCase;
                        ++i;
                    }
                }
                EmergencyCase [] list = new EmergencyCase[i];
                for(int e = 0 ;e<i;++e)
                {
                    list[e]=new EmergencyCase();
                    list[e]=mEmergencyCase_list[e];

                }
                if(enter){

                    enter=false;
                    Intent intent = new Intent(DeleteEmergencyPage.this, DeleteEmergencyValidation.class);
                    intent.putExtra("EmergencyId",list[mActivityPosition].getEmergencyCaseID());
                    intent.putExtra("charityId",charityId);
                    startActivity(intent);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }});

    }
    @Override
    protected void onResume() {
        navigationView.setCheckedItem(R.id.delete_emergency);
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_delete_emergency);
        Intent in = getIntent();
        charityId = in.getStringExtra("charityId");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout8);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setCheckedItem(R.id.delete_emergency);



        mView = (RecyclerView) findViewById(R.id.emergency_recycle);
        mView.setLayoutManager(new GridLayoutManager(DeleteEmergencyPage.this, 1));


        enter2 = true;
        databaseEmergencyCase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int i = 0;
                mEmergencyCase_list = new EmergencyCase[(int) dataSnapshot.getChildrenCount()];
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    mEmergencyCase= snap.getValue(EmergencyCase.class);
                    if(mEmergencyCase.getCharityID().equals(charityId))
                    {
                        mEmergencyCase_list[i] = new EmergencyCase();
                        mEmergencyCase_list[i] =mEmergencyCase;
                        ++i;
                    }
                }
                EmergencyCase [] list = new EmergencyCase[i];
                for(int e = 0 ;e<i;++e)
                {
                    list[e]=new EmergencyCase();
                    list[e]=mEmergencyCase_list[e];

                }
                if(enter2){
                    mAdapter = new EmergencyAdapter(DeleteEmergencyPage.this);
                    mAdapter.getData(list);
                    mView.setAdapter(mAdapter);
                    enter2=false;
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }});








    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_activity:
                Intent intent12= new Intent(this, CharityView.class);
                intent12.putExtra("charityId",charityId);
                startActivity(intent12);
                break;
            case R.id.delete_activity:
                Intent intent3 = new Intent(this, DeleteActivityPage.class);
                intent3.putExtra("charityId",charityId);
                startActivity(intent3);

                break;
            case R.id.update_activity:
                Intent intent2 = new Intent(this, UpdateActivityPage.class);
                intent2.putExtra("charityId",charityId);
                startActivity(intent2);
                break;
            case R.id.view_emergency:
                Intent intent21 = new Intent(this, ViewEmergencyCases.class);
                intent21.putExtra("charityId",charityId);
                startActivity(intent21);
                break;
            case R.id.add_emergency:
                Intent intenta = new Intent(this, AddEmergency.class);
                intenta.putExtra("charityId",charityId);
                startActivity(intenta);
                break;
            case R.id.delete_emergency:


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
