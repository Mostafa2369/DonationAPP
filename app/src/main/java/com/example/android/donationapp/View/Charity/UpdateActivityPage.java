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
import android.util.Log;
import android.view.MenuItem;

import com.example.android.donationapp.Model.CharityActivity;
import com.example.android.donationapp.R;
import com.example.android.donationapp.View.Adapters.ActivityAdapter;
import com.example.android.donationapp.View.HomePage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class UpdateActivityPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ActivityAdapter.ActivityAdapterOnClickHandler {

    private DatabaseReference databaseCharityActivity = FirebaseDatabase.getInstance().getReference("CharityActivity");
    private CharityActivity mCharityActivity;
    private CharityActivity[] mCharityActivity_list;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ActivityAdapter mAdapter;
    private RecyclerView mView;
    private boolean enter;
    private String charityId;

    @Override
    public void onClick(final int mActivityPosition) {
        enter = true;
        databaseCharityActivity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int i = 0;
                mCharityActivity_list = new CharityActivity[(int) dataSnapshot.getChildrenCount()];
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    mCharityActivity = snap.getValue(CharityActivity.class);
                    if (mCharityActivity.getCharityID().equals(charityId)) {
                        mCharityActivity_list[i] = new CharityActivity();
                        mCharityActivity_list[i] = mCharityActivity;
                        ++i;
                    }
                }
                CharityActivity[] list = new CharityActivity[i];
                for (int e = 0; e < i; ++e) {
                    list[e] = new CharityActivity();
                    list[e] = mCharityActivity_list[e];

                }
                if (enter) {

                    enter = false;
                    Intent intent = new Intent(UpdateActivityPage.this, UpdateActivityFields.class);
                    intent.putExtra("ActivityName", list[mActivityPosition].getName());
                    intent.putExtra("ActivityId", list[mActivityPosition].getCharityActivityID());
                    intent.putExtra("charityId", charityId);
                    intent.putExtra("ActivityDescription", list[mActivityPosition].getDescription());
                    startActivity(intent);


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onResume() {
        navigationView.setCheckedItem(R.id.update_activity);
        super.onResume();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_activity);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setCheckedItem(R.id.update_activity);

        databaseCharityActivity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Intent in = getIntent();
                charityId = in.getStringExtra("charityId");

                Log.d("UpdateActivityPage", charityId + "hello1");
                int i = 0;
                mCharityActivity_list = new CharityActivity[(int) dataSnapshot.getChildrenCount()];
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    mCharityActivity = snap.getValue(CharityActivity.class);

                    if (mCharityActivity.getCharityID().equals(charityId)) {
                        mCharityActivity_list[i] = new CharityActivity();
                        mCharityActivity_list[i] = mCharityActivity;
                        ++i;
                    }
                }
                CharityActivity[] list = new CharityActivity[i];
                for (int e = 0; e < i; ++e) {
                    list[e] = new CharityActivity();
                    list[e] = mCharityActivity_list[e];

                }

                mView = (RecyclerView) findViewById(R.id.activity_recycle);
                mView.setLayoutManager(new GridLayoutManager(UpdateActivityPage.this, 1));
                mAdapter = new ActivityAdapter(UpdateActivityPage.this);
                mAdapter.getData(list);
                mView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_activity:
                Intent intent2 = new Intent(this, CharityView.class);
                intent2.putExtra("charityId", charityId);
                startActivity(intent2);
                break;
            case R.id.delete_activity:

                Intent intent3 = new Intent(this, DeleteActivityPage.class);
                intent3.putExtra("charityId", charityId);
                startActivity(intent3);
                break;
            case R.id.update_activity:

                break;
            case R.id.view_emergency:
                Intent intent21 = new Intent(this, ViewEmergencyCases.class);
                intent21.putExtra("charityId", charityId);
                startActivity(intent21);
                break;
            case R.id.add_emergency:
                Intent intenta = new Intent(this, AddEmergency.class);
                intenta.putExtra("charityId", charityId);
                startActivity(intenta);
                break;
            case R.id.delete_emergency:
                Intent intentt = new Intent(this, DeleteEmergencyPage.class);
                intentt.putExtra("charityId", charityId);
                startActivity(intentt);

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
