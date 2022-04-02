package com.example.android.donationapp.View.Charity;

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
import android.widget.Toast;

import com.example.android.donationapp.Controller.CharityController;
import com.example.android.donationapp.Model.EmergencyCase;
import com.example.android.donationapp.R;
import com.example.android.donationapp.View.HomePage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddEmergency extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

private String charityId;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private EditText mName;
    private EditText mDescription;
    private Button mAddActivity;
    boolean enter=false;
    private boolean add=false;
    private String name;
    private String description;
    private boolean read=false;
    private DatabaseReference databaseEmergencyCase = FirebaseDatabase.getInstance().getReference("EmergencyCase");
    private EmergencyCase mEmergencyCase;
    private EmergencyCase[] mEmergencyCase_list;
    @Override
    protected void onResume() {
        navigationView.setCheckedItem(R.id.add_emergency);
        super.onResume();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_emergency);
        Intent in = getIntent();
        charityId = in.getStringExtra("charityId");
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
        navigationView.setCheckedItem(R.id.add_emergency);


        mName = (EditText) findViewById(R.id.name6);
        mDescription = (EditText) findViewById(R.id.description6);
        mAddActivity = (Button) findViewById(R.id.btn_signUp6);
        mAddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             addCharity();

            }
        });

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_activity:
                Intent intent5 = new Intent(this, CharityView.class);
                intent5.putExtra("charityId",charityId);
                startActivity(intent5);
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

                break;
            case R.id.delete_emergency:

                Intent intentt = new Intent(this, DeleteEmergencyPage.class);
                intentt.putExtra("charityId",charityId);
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


    public void addCharity() {


        name=mName.getText().toString();
        description=mDescription.getText().toString();
        add=true;
        read=true;
        enter=true;

        databaseEmergencyCase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int i = 0;
                mEmergencyCase_list = new EmergencyCase[(int) dataSnapshot.getChildrenCount()];
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    mEmergencyCase_list[i] = new EmergencyCase();
                    mEmergencyCase= snap.getValue(EmergencyCase.class);
                    mEmergencyCase_list[i] =mEmergencyCase;
                    ++i;
                }
                if(enter){
                    for (int j = 0; j < mEmergencyCase_list.length; ++j) {
                        if (name.equals(mEmergencyCase_list[j].getTile())&&read) {
                            add=false;
                            Toast.makeText(AddEmergency.this,"Please choose another name",Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(name.length()<1)
                    {
                        Toast.makeText(AddEmergency.this,"Please enter name",Toast.LENGTH_SHORT).show();
                    }
                    else if(description.length()<1)
                    {
                        Toast.makeText(AddEmergency.this,"Please enter description",Toast.LENGTH_SHORT).show();
                    }

                    else if(add&&read)
                    {
                        CharityController mActivity = new CharityController();
                        read=false;
                        mActivity.addEmergencyCase(charityId,name,description,0);
                        Toast.makeText(AddEmergency.this,"Emergency added successfully",Toast.LENGTH_SHORT).show();
                        mName.setText("");
                        mDescription.setText("");
                        enter=false;

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }});}
}
