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

import com.example.android.donationapp.R;
import com.example.android.donationapp.View.HomePage;

public class ZakahMeasurment extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private String donorId;
    private TextView mResult;
private EditText mMonye;
private EditText mGold;
private Button mCalc;
private String mony;
private String gold;
    @Override
    protected void onResume() {
        navigationView.setCheckedItem(R.id.zakah_measure);
        super.onResume();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakah_measure);
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
        navigationView.setCheckedItem(R.id.zakah_measure);

        mMonye=(EditText) findViewById(R.id.monye);
        mGold=(EditText) findViewById(R.id.gold);
        mCalc=(Button) findViewById(R.id.calc);
        mResult=(TextView) findViewById(R.id.result);

        mCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



        mony=mMonye.getText().toString();
        gold=mGold.getText().toString();
        int m = Integer.valueOf(mony);
        int g = Integer.valueOf(gold);

        if(m<1)
            Toast.makeText(ZakahMeasurment.this,"Please enter Amount of money",Toast.LENGTH_SHORT).show();
          else if(g<1)
            Toast.makeText(ZakahMeasurment.this,"Please enter price of gram of gold",Toast.LENGTH_SHORT).show();


        else if(m/g>=85)
        {

            mResult.setText("You should pay: "+m*0.025);

        }
         else if(m/g<85)
        {

            mResult.setText("You don't have to pay zakah");


        }





            }
        });






    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.view_charities:
                Intent intent1;
                intent1 = new Intent(ZakahMeasurment.this, DonorView.class);
                intent1.putExtra("donorId", donorId);
                startActivity(intent1);
                break;
            case R.id.view_profile:
                Intent intent2;
                intent2 = new Intent(ZakahMeasurment.this, ViewProfile.class);
                intent2.putExtra("donorId",donorId);
                startActivity(intent2);

                break;
            case R.id.view_cases:
                Intent intent3;
                intent3 = new Intent(ZakahMeasurment.this, ViewCases.class);
                intent3.putExtra("donorId",donorId);
                startActivity(intent3);
                break;
            case R.id.view_report:

                Intent intent4;
                intent4 = new Intent(ZakahMeasurment.this, DonorReport.class);
                intent4.putExtra("donorId",donorId);
                startActivity(intent4);
                break;
            case R.id.zakah_measure:

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