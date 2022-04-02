package com.example.android.donationapp.View;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.donationapp.Model.Admin;
import com.example.android.donationapp.Model.Charity;
import com.example.android.donationapp.Model.Donation;
import com.example.android.donationapp.Model.Donor;
import com.example.android.donationapp.R;
import com.example.android.donationapp.View.Admin.AdminView;
import com.example.android.donationapp.View.Charity.CharityView;
import com.example.android.donationapp.View.Donor.DonorView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomePage extends AppCompatActivity {
    private EditText mGetEmail;
    private EditText mGetPassword;
    private String Email;
    private String Password;
    private Button mLogin;
    private Admin mAdmin ;
    private Admin [] mAdmin_list;
    private Charity[] mCharities;
    private Charity mCharity;
    private Donor mDonor;
    private int i = 1;
    private Donor [] mDonor_list;
    boolean enter =false;
    private Donation mDonation;
    private DatabaseReference databaseCharity= FirebaseDatabase.getInstance().getReference("Charity");
    private DatabaseReference databaseDonor = FirebaseDatabase.getInstance().getReference("Donor");
    private DatabaseReference databaseAdmin= FirebaseDatabase.getInstance().getReference("Admin");
    boolean found=false;
    private TextView mSiignUp ;
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        final ImageView mLogo = (ImageView) findViewById(R.id.logo1);
         mGetEmail = (EditText) findViewById(R.id.input_email);
         mGetPassword=(EditText) findViewById(R.id.input_password);
         mLogin=(Button) findViewById(R.id.btn_login);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLogo.setVisibility(View.GONE);
            }
        }, 2000);


    mLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(isNetworkConnected()) {
                enter =true;
            found = false;
            Email = mGetEmail.getText().toString();
            Password = mGetPassword.getText().toString();
                if(Email.length()<1)
                {
                    Toast.makeText(HomePage.this,"Please enter your email",Toast.LENGTH_SHORT).show();
                }
                else if(Password.length()<1)
                {
                    Toast.makeText(HomePage.this,"Please enter your password",Toast.LENGTH_SHORT).show();
                }
            else {
            databaseAdmin.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    mAdmin_list = new Admin[(int) dataSnapshot.getChildrenCount()];int i = 0;
                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                        mAdmin = new Admin();
                        mAdmin = snap.getValue(Admin.class);
                        mAdmin_list[i] = mAdmin;
                        ++i;
                    }
                    for (int j = 0; j < mAdmin_list.length; ++j) {
                        if (Email.equals(mAdmin_list[j].getEmail()) && Password.equals(mAdmin_list[j].getPassword())&&enter) {
                            Intent intent;
                            found = true;
                            enter=false;
                            intent = new Intent(HomePage.this, AdminView.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                    databaseDonor.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            int i = 0;
                            mDonor_list = new Donor[(int) dataSnapshot.getChildrenCount()];
                            for (DataSnapshot snap : dataSnapshot.getChildren()) {
                                mDonor_list[i] = new Donor();
                                mDonor = snap.getValue(Donor.class);
                                mDonor_list[i] = mDonor;
                                ++i;
                            }
                            for (int j = 0; j < mDonor_list.length; ++j) {
                                if (Email.equals(mDonor_list[j].getEmail()) && Password.equals(mDonor_list[j].getPassword())&&enter) {
                                    Intent intent;
                                    found = true;
                                    enter=false;
                                    intent = new Intent(HomePage.this, DonorView.class);
                                    intent.putExtra("donorId",mDonor_list[j].getDonorID());
                                    startActivity(intent);
                                    finish();
                                }
                            }
                            databaseCharity.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    int i = 0;
                                    mCharities = new Charity[(int) dataSnapshot.getChildrenCount()];
                                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                                        mCharities[i] = new Charity();
                                        mCharity = snap.getValue(Charity.class);
                                        mCharities[i] = mCharity;
                                        ++i;
                                    }
                                    for (int j = 0; j < mCharities.length; ++j) {
                                        if (Email.equals(mCharities[j].getEmail()) && Password.equals(mCharities[j].getPassword())&&enter) {
                                            Intent intent;
                                            found = true;
                                            enter=false;
                                            intent = new Intent(HomePage.this, CharityView.class);
                                            intent.putExtra("charityId",mCharities[j].getCharityID());
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                    if (!found)
                                        Toast.makeText(HomePage.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            }
            );


            }







        }
            else{ Toast.makeText(HomePage.this,"Please connect to internet",Toast.LENGTH_SHORT).show();}

            }
    });
    mSiignUp = (TextView) findViewById(R.id.link_signup);
    mSiignUp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(isNetworkConnected()) {
            Intent intent;
            intent = new Intent(HomePage.this, SignUpPage.class);
            startActivity(intent);
        }
            else{ Toast.makeText(HomePage.this,"Please connect to internet",Toast.LENGTH_SHORT).show();}

            }
    });




}

    @Override
    public void onBackPressed() {
        if(i!=1){super.onBackPressed();}


    }
}
