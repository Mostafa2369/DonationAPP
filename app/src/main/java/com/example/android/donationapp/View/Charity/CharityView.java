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
import com.example.android.donationapp.Model.CharityActivity;
import com.example.android.donationapp.R;
import com.example.android.donationapp.View.HomePage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CharityView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DatabaseReference databaseCharityActivity = FirebaseDatabase.getInstance().getReference("CharityActivity");
    boolean enter=false;
    private boolean add=false;
    private boolean read=false;
    private String name;
    private String description;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private EditText mName;
    private EditText mDescription;
    private Button mAddActivity;
    private CharityActivity mCharityActivity;
    private CharityActivity[] mCharityActivity_list;
    private String charityId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_charity_view);
        Intent in = getIntent();
        charityId=in.getStringExtra("charityId");

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
        navigationView.setCheckedItem(R.id.add_activity);

        mName = (EditText) findViewById(R.id.name5);
        mDescription = (EditText) findViewById(R.id.description);
        mAddActivity = (Button) findViewById(R.id.btn_signUp5);
        mAddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCharity();

            }
        });


    }
    @Override
    protected void onResume() {
        navigationView.setCheckedItem(R.id.add_activity);
        super.onResume();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_activity:

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

        databaseCharityActivity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int i = 0;
                mCharityActivity_list = new CharityActivity[(int) dataSnapshot.getChildrenCount()];
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    mCharityActivity_list[i] = new CharityActivity();
                    mCharityActivity= snap.getValue(CharityActivity.class);
                    mCharityActivity_list[i] =mCharityActivity;
                    ++i;
                }
                if(enter){
                    for (int j = 0; j < mCharityActivity_list.length; ++j) {
                        if (name.equals(mCharityActivity_list[j].getName())&&read) {
                            add=false;
                            Toast.makeText(CharityView.this,"Please choose another name",Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(name.length()<1)
                    {
                        Toast.makeText(CharityView.this,"Please enter name",Toast.LENGTH_SHORT).show();
                    }
                    else if(description.length()<1)
                    {
                        Toast.makeText(CharityView.this,"Please enter description",Toast.LENGTH_SHORT).show();
                    }

                    else if(add&&read)
                    {
                       CharityController mActivity = new CharityController();
                        read=false;
                      mActivity.addCharityActivity(charityId,name,description);
                        Toast.makeText(CharityView.this,"Charity Activity added successfully",Toast.LENGTH_SHORT).show();
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
