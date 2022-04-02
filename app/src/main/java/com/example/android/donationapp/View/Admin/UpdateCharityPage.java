package com.example.android.donationapp.View.Admin;

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

import com.example.android.donationapp.Model.Charity;
import com.example.android.donationapp.Model.Donation;
import com.example.android.donationapp.R;
import com.example.android.donationapp.View.Adapters.CharityAdapter2;
import com.example.android.donationapp.View.HomePage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateCharityPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CharityAdapter2.CharityAdapter2OnClickHandler  {

    private DatabaseReference databaseCharity= FirebaseDatabase.getInstance().getReference("Charity");
    private Charity mCharity;
    private Charity []mCharity_list;
    private DatabaseReference databaseDonation= FirebaseDatabase.getInstance().getReference("Donation");
    private Donation[] mDonation_list;
    private Donation mDonation;
    private DrawerLayout drawer;
    private CharityAdapter2 mAdapter;
    private RecyclerView mView;
    private boolean enter1=true;
    private  boolean enter;
    private NavigationView navigationView;

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
                    Intent intent = new Intent(UpdateCharityPage.this, UpdateCharityFields.class);
                    intent.putExtra("charityId",mCharity_list[mCharityPosition].getCharityID());
                    intent.putExtra("charityEmail",mCharity_list[mCharityPosition].getEmail());
                    intent.putExtra("charityName",mCharity_list[mCharityPosition].getName());
                    intent.putExtra("charityLocation",mCharity_list[mCharityPosition].getLocation());
                    intent.putExtra("charityPassword",mCharity_list[mCharityPosition].getPassword());
                    intent.putExtra("charityPhone",mCharity_list[mCharityPosition].getPhoneNumber());
                    startActivity(intent);


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }});





    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_charity);
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


        navigationView.setCheckedItem(R.id.update_charity);

        databaseCharity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int i = 0;
                mCharity_list = new Charity[(int) dataSnapshot.getChildrenCount()];
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    mCharity_list[i] = new Charity();
                    mCharity = snap.getValue(Charity.class);
                    mCharity_list[i] = mCharity;
                    ++i;
                }
                mView = (RecyclerView) findViewById(R.id.charity_recycle2);
                mView.setLayoutManager(new GridLayoutManager(UpdateCharityPage.this, 1));
                mAdapter = new CharityAdapter2(UpdateCharityPage.this);
                mAdapter.getData(mCharity_list);
                mView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    @Override
    protected void onResume() {
        navigationView.setCheckedItem(R.id.update_charity);
        super.onResume();
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_charity:
                Intent intent2 = new Intent(this, AdminView.class);
                startActivity(intent2);

                break;
            case R.id.delete_charity:
                Intent intent3 = new Intent(this, DeleteCharityPage.class);
                startActivity(intent3);

                break;
            case R.id.update_charity:

                break;
            case R.id.add_admin:
                Intent intent1 = new Intent(this, AddAdminPage.class);
                startActivity(intent1);

                break;
            case R.id.send_email:
                sendReport();
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
    public void sendReport()
    { enter1=true;
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

                if(enter1) {
                    databaseDonation.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            int i = 0;
                            mDonation_list = new Donation[(int) dataSnapshot.getChildrenCount()];
                            for (DataSnapshot snap : dataSnapshot.getChildren()) {
                                mDonation_list[i] = new Donation();
                                mDonation = snap.getValue(Donation.class);
                                mDonation_list[i] = mDonation;
                                ++i;
                            }
                            int total = 0;
                            String name;
                            String message = "";

                            for (int t = 0; t < mCharity_list.length; ++t) {
                                total = 0;
                                for (int r = 0; r < mDonation_list.length; ++r) {
                                    if (mCharity_list[t].getCharityID().equals(mDonation_list[r].getCharityID())) {
                                        Log.d("AdminView", "hello" + total);
                                        total += mDonation_list[r].getAmount();


                                    }


                                }
                                name = mCharity_list[t].getName();
                                String total2 = Integer.toString(total);
                                message = message + "\nCharity Name: " + name + " Total Donation: " + total2;

                            }
                            enter1=false;
                            Intent in = new Intent(Intent.ACTION_SEND);
                            in.setType("message/rfc822");
                            in.putExtra(Intent.EXTRA_EMAIL, "mostafa2369@gmail.com");
                            in.putExtra(Intent.EXTRA_SUBJECT, "Charities Report");
                            in.putExtra(Intent.EXTRA_TEXT, message);
                            try {
                                startActivity(Intent.createChooser(in, "Send mail..."));
                            } catch (android.content.ActivityNotFoundException ex) {

                            }


                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }});



    }
}
