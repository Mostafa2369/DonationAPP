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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.donationapp.Controller.DonorController;
import com.example.android.donationapp.Model.Donor;
import com.example.android.donationapp.R;
import com.example.android.donationapp.View.HomePage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ViewProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DatabaseReference databaseDonor = FirebaseDatabase.getInstance().getReference("Donor");
    private Donor mDonor;
    private Donor[] mDonor_list;
    private String donorId;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private TextView mName;
    private TextView mEmail;
    private TextView mPassword;
    private TextView mAge;
    private TextView mPhoneNumer;
    private TextView mGender;
    private Button mUpdateProfile;
    private Button mDone;
    private EditText mEmailEdite;
    private EditText mPasswordEdite;
    private EditText mNameEdite;
    private EditText mAgeEdite;
    private EditText mPhoneEdite;
    private EditText mGenderEdite;
    private Donor mDonorGet;
    private String email;
    private String password;
    private String name;
    private String age;
    private String phone;
    private String gender;
    boolean add =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        Intent in = getIntent();
        donorId = in.getStringExtra("donorId");
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout12);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setCheckedItem(R.id.view_profile);
        mName = (TextView) findViewById(R.id.pro_name);
        mEmail = (TextView) findViewById(R.id.pro_email);
        mPassword = (TextView) findViewById(R.id.pro_password);
        mAge = (TextView) findViewById(R.id.pro_age);
        mGender = (TextView) findViewById(R.id.pro_gender);
        mPhoneNumer = (TextView) findViewById(R.id.pro_phone);
        mUpdateProfile = (Button) findViewById(R.id.btn_signUp90);

        mNameEdite = (EditText) findViewById(R.id.name_pro);
        mEmailEdite = (EditText) findViewById(R.id.email_pro);
        mPasswordEdite = (EditText) findViewById(R.id.password_pro);
        mAgeEdite = (EditText) findViewById(R.id.age_pro);
        mGenderEdite = (EditText) findViewById(R.id.gender_pro);
        mPhoneEdite = (EditText) findViewById(R.id.phone_pro);
        mDone = (Button) findViewById(R.id.btn_signUp91);

        mNameEdite.setVisibility(View.GONE);
        mPasswordEdite.setVisibility(View.GONE);
        mEmailEdite.setVisibility(View.GONE);
        mAgeEdite.setVisibility(View.GONE);
        mGenderEdite.setVisibility(View.GONE);
        mPhoneEdite.setVisibility(View.GONE);
        mDone.setVisibility(View.GONE);


        databaseDonor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                mDonor_list = new Donor[(int) dataSnapshot.getChildrenCount()];
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    mDonor_list[i] = new Donor();
                    mDonor = snap.getValue(Donor.class);
                    if (mDonor.getDonorID().equals(donorId)) {
                        mDonorGet = mDonor;
                        break;

                    }
                    mDonor_list[i] = mDonor;
                    ++i;
                }

                mName.setText("Name: "+mDonorGet.getName());
                mEmail.setText("Email: "+mDonorGet.getEmail());
                mPassword.setText("Password: "+mDonorGet.getPassword());
                mAge.setText("Age: "+mDonorGet.getAge());
                mPhoneNumer.setText("Phone Number: "+mDonorGet.getPhoneNumber());
                mGender.setText("Gender: "+mDonorGet.getGender());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });




        mUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mName.setVisibility(View.GONE);
                mPassword.setVisibility(View.GONE);
                mEmail.setVisibility(View.GONE);
                mAge.setVisibility(View.GONE);
                mGender.setVisibility(View.GONE);
                mPhoneNumer.setVisibility(View.GONE);
                mUpdateProfile.setVisibility(View.GONE);

                mNameEdite.setVisibility(View.VISIBLE);
                mPasswordEdite.setVisibility(View.VISIBLE);
                mEmailEdite.setVisibility(View.VISIBLE);
                mAgeEdite.setVisibility(View.VISIBLE);
                mGenderEdite.setVisibility(View.VISIBLE);
                mPhoneEdite.setVisibility(View.VISIBLE);
                mDone.setVisibility(View.VISIBLE);

                databaseDonor.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int i = 0;
                        mDonor_list = new Donor[(int) dataSnapshot.getChildrenCount()];
                        for (DataSnapshot snap : dataSnapshot.getChildren()) {
                            mDonor_list[i] = new Donor();
                            mDonor = snap.getValue(Donor.class);
                            if (mDonor.getDonorID().equals(donorId)) {
                                mDonorGet = mDonor;
                                break;

                            }
                            mDonor_list[i] = mDonor;
                            ++i;
                        }
                        mNameEdite.setText(mDonorGet.getName());
                        mEmailEdite.setText(mDonorGet.getEmail());
                        mPasswordEdite.setText(mDonorGet.getPassword());
                        mAgeEdite.setText(mDonorGet.getAge());
                        mPhoneEdite.setText(mDonorGet.getPhoneNumber());
                        mGenderEdite.setText(mDonorGet.getGender());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

            }
        });


        mDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add=true;
                name=mNameEdite.getText().toString();
                age=mAgeEdite.getText().toString();
                gender=mGenderEdite.getText().toString();
                phone=mPhoneEdite.getText().toString();
                email=mEmailEdite.getText().toString();
                password=mPasswordEdite.getText().toString();
                if(name.length()<1)
                {
                    Toast.makeText(ViewProfile.this,"Please enter your name",Toast.LENGTH_SHORT).show();
                }
                else if(email.length()<1)
                {
                    Toast.makeText(ViewProfile.this,"Please enter your email",Toast.LENGTH_SHORT).show();
                }
                else if(password.length()<8)
                {
                    Toast.makeText(ViewProfile.this,"Please enter password more than 8 digits",Toast.LENGTH_SHORT).show();
                }
                else if(phone.length()<1)
                {
                    Toast.makeText(ViewProfile.this,"Please enter your phone number",Toast.LENGTH_SHORT).show();
                }
                else if(gender.length()<1)
                {
                    Toast.makeText(ViewProfile.this,"Please enter your gender",Toast.LENGTH_SHORT).show();
                }
                else if(age.length()<1)
                {
                    Toast.makeText(ViewProfile.this,"Please enter your age",Toast.LENGTH_SHORT).show();
                }

                else if(add) {
                    DonorController mDonor=new DonorController();
                    add=false;
                    mDonor.editeName(donorId,name);
                    mDonor.editeEmail(donorId,email);
                    mDonor.editePassword(donorId,password);
                    mDonor.editeAge(donorId,age);
                    mDonor.editeGender(donorId,gender);
                    mDonor.editePhoneNumber(donorId,phone);
                    Intent intent;
                    Toast.makeText(ViewProfile.this,"Profile Updated Successfully",Toast.LENGTH_SHORT).show();

                    intent = new Intent(ViewProfile.this, ViewProfile.class);
                    intent.putExtra("donorId", donorId);
                    startActivity(intent);
                    finish();


                }
            }
        });
    }

    @Override
    protected void onResume() {
        navigationView.setCheckedItem(R.id.view_profile);
        super.onResume();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.view_charities:
                Intent intent1;
                intent1 = new Intent(ViewProfile.this, DonorView.class);
                intent1.putExtra("donorId", donorId);
                startActivity(intent1);
                break;
            case R.id.view_profile:
                Intent intent2;
                intent2 = new Intent(ViewProfile.this, ViewProfile.class);
                intent2.putExtra("donorId",donorId);
                startActivity(intent2);

                break;
            case R.id.view_cases:
                Intent intent3;
                intent3 = new Intent(ViewProfile.this, ViewCases.class);
                intent3.putExtra("donorId",donorId);
                startActivity(intent3);
                break;
            case R.id.view_report:

                Intent intent4;
                intent4 = new Intent(ViewProfile.this, DonorReport.class);
                intent4.putExtra("donorId",donorId);
                startActivity(intent4);
                break;
            case R.id.zakah_measure:
                Intent intent5;
                intent5 = new Intent(ViewProfile.this, ZakahMeasurment.class);
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
