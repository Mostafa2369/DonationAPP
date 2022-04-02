package com.example.android.donationapp.View.Admin;




import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.example.android.donationapp.Controller.AdminContoller;
import com.example.android.donationapp.Model.Charity;
import com.example.android.donationapp.Model.Donation;
import com.example.android.donationapp.R;
import com.example.android.donationapp.View.HomePage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class AdminView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    private Button mAddCharity;
    private Charity mCharity;
    private DatabaseReference databaseCharity= FirebaseDatabase.getInstance().getReference("Charity");
    private String name;
    private String email;
    private String password;
    private String location;
    private String phone;
    private String confirmPassword;
    private boolean add=false;
    private boolean read=false;
    private Charity []mCharity_list;
    private EditText mName;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mLocation;
    private EditText mPhone;
    private boolean enter2=false;
    private EditText mConfirmPassword;
    private DatabaseReference databaseDonation= FirebaseDatabase.getInstance().getReference("Donation");
    private Donation[] mDonation_list;
    private Donation mDonation;
    boolean enter=false;
private NavigationView navigationView;



    @Override
    protected void onResume() {
        navigationView.setCheckedItem(R.id.add_charity);
        super.onResume();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);
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




        navigationView.setCheckedItem(R.id.add_charity);

        mAddCharity=(Button) findViewById(R.id.btn_signUp2);
        mEmail =(EditText) findViewById(R.id.email2);
        mLocation =(EditText) findViewById(R.id.location2);
        mPassword=(EditText) findViewById(R.id.password2);
        mConfirmPassword=(EditText) findViewById(R.id.confirm_password2);
        mName=(EditText) findViewById(R.id.name2);
        mPhone=(EditText) findViewById(R.id.phone2);


        mAddCharity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           addCharity();

            }
        });

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_charity:

                break;
            case R.id.delete_charity:
                Intent intent3 = new Intent(this, DeleteCharityPage.class);
                startActivity(intent3);

                break;
            case R.id.update_charity:
                Intent intent4 = new Intent(this, UpdateCharityPage.class);
                startActivity(intent4);
                finish();
                break;
            case R.id.add_admin:
                Intent intent2 = new Intent(this, AddAdminPage.class);
                startActivity(intent2);

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




    public void addCharity() {


        name=mName.getText().toString();
        email=mEmail.getText().toString();
        password=mPassword.getText().toString();
        location=mLocation.getText().toString();
        confirmPassword=mConfirmPassword.getText().toString();
        phone=mPhone.getText().toString();
        add=true;
        read=true;
        enter=true;

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
                for (int j = 0; j < mCharity_list.length; ++j) {
                    if (email.equals(mCharity_list[j].getEmail())&&read) {
                        add=false;
                        Toast.makeText(AdminView.this,"Please choose another email",Toast.LENGTH_SHORT).show();
                    }

                }
                if(name.length()<1)
                {
                    Toast.makeText(AdminView.this,"Please enter name",Toast.LENGTH_SHORT).show();
                }
                else if(email.length()<1)
                {
                    Toast.makeText(AdminView.this,"Please enter email",Toast.LENGTH_SHORT).show();
                }
                else if(password.length()<8)
                {
                    Toast.makeText(AdminView.this,"Please enter password more than 8 digits",Toast.LENGTH_SHORT).show();
                }
                else if(!confirmPassword.equals(password))
                {
                    add=false;
                    Toast.makeText(AdminView.this,"Wrong password confirmation",Toast.LENGTH_SHORT).show();
                }
                else if(phone.length()<1)
                {
                    Toast.makeText(AdminView.this,"Please enter phone number",Toast.LENGTH_SHORT).show();
                }
                else if(location.length()<1)
                {
                    Toast.makeText(AdminView.this,"Please enter location",Toast.LENGTH_SHORT).show();
                }

                else if(add&&read)
                {
                    AdminContoller mCharity=new AdminContoller();
                    read=false;
                    mCharity.addCharity(name,location,phone,email,password);
                    Toast.makeText(AdminView.this,"Charity added successfully",Toast.LENGTH_SHORT).show();
                     mName.setText("");
                    mEmail.setText("");
                    mPassword.setText("");
                    mConfirmPassword.setText("");
                    mLocation.setText("");
                    mPhone.setText("");
                    enter=false;

                }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }});}
    public void sendReport() {
        enter2=true;
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

            for (int t = 0; t < mCharity_list.length; ++t) {
                total = 0;
                for (int r = 0; r < mDonation_list.length; ++r) {

                    if (mCharity_list[t].getCharityID().equals(mDonation_list[r].getCharityID())) {

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
