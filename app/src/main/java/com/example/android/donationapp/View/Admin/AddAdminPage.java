package com.example.android.donationapp.View.Admin;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.example.android.donationapp.Controller.AdminContoller;
import com.example.android.donationapp.Model.Admin;

import com.example.android.donationapp.Model.Charity;
import com.example.android.donationapp.Model.Donation;
import com.example.android.donationapp.R;
import com.example.android.donationapp.View.HomePage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddAdminPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private DatabaseReference databaseCharity= FirebaseDatabase.getInstance().getReference("Charity");
    private EditText mName;
    private boolean enter2;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private Button mAddAdmin;
    private String name;
    private Charity mCharity;
    private String email;
    private String password;
    private String confirmPassword;
    private Admin mAdmin;
    private DatabaseReference databaseAdmin= FirebaseDatabase.getInstance().getReference("Admin");
    private Admin [] mAdmin_list;
    private boolean add=true;
    private boolean read=true;
    private Charity []mCharity_list;
    private DatabaseReference databaseDonation= FirebaseDatabase.getInstance().getReference("Donation");
    private Donation[] mDonation_list;
    private Donation mDonation;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout2);
        navigationView = findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setCheckedItem(R.id.add_admin);


        mAddAdmin=(Button) findViewById(R.id.btn_signUp3);
        mEmail =(EditText) findViewById(R.id.email3);
        mPassword=(EditText) findViewById(R.id.password3);
        mConfirmPassword=(EditText) findViewById(R.id.confirm_password3);
        mName=(EditText) findViewById(R.id.name3);

        mAddAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAdmin();

            }
        });

    }
    @Override
    protected void onResume() {
        navigationView.setCheckedItem(R.id.add_admin);
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
                Intent intent4 = new Intent(this, DeleteCharityPage.class);
                startActivity(intent4);
                break;
            case R.id.add_admin:

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
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public void addAdmin()
    {
        name=mName.getText().toString();
        email=mEmail.getText().toString();
        password=mPassword.getText().toString();

        confirmPassword=mConfirmPassword.getText().toString();

        add=true;
        read=true;
        databaseAdmin.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int i = 0;
                mAdmin_list = new Admin[(int) dataSnapshot.getChildrenCount()];
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    mAdmin_list[i] = new Admin();
                    mAdmin= snap.getValue(Admin.class);
                    mAdmin_list[i] =mAdmin;
                    ++i;
                }
                for (int j = 0; j < mAdmin_list.length; ++j) {
                    if (email.equals(mAdmin_list[j].getEmail())&&read) {
                        add=false;
                        Toast.makeText(AddAdminPage.this,"Please choose another email",Toast.LENGTH_SHORT).show();
                    }

                }
                if(name.length()<1)
                {
                    Toast.makeText(AddAdminPage.this,"Please enter name",Toast.LENGTH_SHORT).show();
                }
                else if(email.length()<1)
                {
                    Toast.makeText(AddAdminPage.this,"Please enter email",Toast.LENGTH_SHORT).show();
                }
                else if(password.length()<8)
                {
                    Toast.makeText(AddAdminPage.this,"Please enter password more than 8 digits",Toast.LENGTH_SHORT).show();
                }
                else if(!confirmPassword.equals(password))
                {
                    add=false;
                    Toast.makeText(AddAdminPage.this,"Wrong password confirmation",Toast.LENGTH_SHORT).show();
                }

                else if(add&&read)
                {
                    AdminContoller mAdminC=new AdminContoller();
                    read=false;
                    mAdminC.addAdmin(name,email,password);
                    Toast.makeText(AddAdminPage.this,"Admin added successfully",Toast.LENGTH_SHORT).show();
                    mName.setText("");
                    mEmail.setText("");
                    mPassword.setText("");
                    mConfirmPassword.setText("");


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }});}
    public void sendReport()
    { enter2=true;
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

        if(enter2) {
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
                    Log.d("AdminView", "hello" + mCharity_list[0].getCharityID());
                    Log.d("AdminView", "hello" + mDonation_list[0].getCharityID());
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
                    Intent in = new Intent(Intent.ACTION_SEND);
                    in.setType("message/rfc822");
                    enter2=false;
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


