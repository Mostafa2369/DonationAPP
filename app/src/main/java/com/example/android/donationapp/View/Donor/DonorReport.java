package com.example.android.donationapp.View.Donor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.donationapp.Model.Donation;
import com.example.android.donationapp.R;
import com.example.android.donationapp.View.HomePage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DonorReport extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String donorId;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private DatabaseReference databaseDonation= FirebaseDatabase.getInstance().getReference("Donation");

    private Donation mDonation;
    private TextView mReport;
private String report="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_report);
        Intent in = getIntent();
        donorId = in.getStringExtra("donorId");


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout54);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setCheckedItem(R.id.view_report);
        mReport=(TextView) findViewById(R.id.report);
        databaseDonation.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int i = 0;
                report="";
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    ++i;
                    mDonation = snap.getValue(Donation.class);
                    report+="Donation #"+i+"\n" +
                            "  Charity name: "+mDonation.getCharityName()+"\n  Activity name: "
                            +mDonation.getCharityActivityName()+"\n  Payment method: "+mDonation.getDonationType()+"\n  Amount ="+mDonation.getAmount()
                            +"\n\n";



                }
                 mReport.setText(report);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






        Toast.makeText(DonorReport.this,"Thank you for your donations <3",Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onResume() {
        navigationView.setCheckedItem(R.id.view_report);
        super.onResume();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.view_charities:
                Intent intent4;
                intent4 = new Intent(DonorReport.this, DonorView.class);
                intent4.putExtra("donorId",donorId);
                startActivity(intent4);
                break;
            case R.id.view_profile:
                Intent intent1;
                intent1 = new Intent(DonorReport.this, ViewProfile.class);
                intent1.putExtra("donorId",donorId);
                startActivity(intent1);
                break;
            case R.id.view_cases:
                Intent intent2;
                intent2 = new Intent(DonorReport.this, ViewCases.class);
                intent2.putExtra("donorId",donorId);
                startActivity(intent2);
                break;
            case R.id.view_report:


                break;
            case R.id.zakah_measure:
                Intent intent5;
                intent5 = new Intent(DonorReport.this, ZakahMeasurment.class);
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
