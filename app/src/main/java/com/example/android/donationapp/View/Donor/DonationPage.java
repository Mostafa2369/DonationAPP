package com.example.android.donationapp.View.Donor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.android.donationapp.Controller.DonorController;
import com.example.android.donationapp.R;

public class DonationPage extends AppCompatActivity {
    private String charityId;
    private String charityName;
    private String activityId;
    private String activityName;
    private String amount;
    private EditText mAmount;
    private ImageButton mSms;
    private ImageButton mFawry;
    private String donorId;
    private int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_page);
        Intent in = getIntent();
        charityId = in.getStringExtra("charityId");
        charityName = in.getStringExtra("charityName");
        activityId = in.getStringExtra("ActivityId");
        activityName = in.getStringExtra("ActivityName");
        donorId = in.getStringExtra("donorId");
        mAmount = (EditText) findViewById(R.id.amount);
        mSms = (ImageButton) findViewById(R.id.sms);
        mFawry = (ImageButton) findViewById(R.id.fawry);

        mSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount = mAmount.getText().toString();
                if (amount.length() < 1)
                    Toast.makeText(DonationPage.this, "Please enter amount", Toast.LENGTH_SHORT).show();
                else {

                    a = Integer.valueOf(amount);
                    DonorController d = new DonorController();
                    d.addDonation(charityId, charityName, activityId, activityName, donorId, "SMS", a);
                    Intent intent = new Intent(DonationPage.this, DonorView.class);
                    Toast.makeText(DonationPage.this, "Donation Successfully Add", Toast.LENGTH_SHORT).show();

                    intent.putExtra("donorId",donorId);
                    startActivity(intent);
                }
            }
        });


        mFawry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount = mAmount.getText().toString();
                if (amount.length() < 1)
                    Toast.makeText(DonationPage.this, "Please enter amount", Toast.LENGTH_SHORT).show();
                else {

                    a = Integer.valueOf(amount);
                    DonorController d = new DonorController();
                    d.addDonation(charityId, charityName, activityId, activityName, donorId, "Fawry", a);

                    Toast.makeText(DonationPage.this, "Donation Successfully Add", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(DonationPage.this, DonorView.class);

                    intent.putExtra("donorId",donorId);
                    startActivity(intent);
                }

            }
        });


    }


}
