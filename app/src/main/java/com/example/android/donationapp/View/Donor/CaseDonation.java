package com.example.android.donationapp.View.Donor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.android.donationapp.Controller.CharityController;
import com.example.android.donationapp.Controller.DonorController;
import com.example.android.donationapp.R;

public class CaseDonation  extends AppCompatActivity {
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
private int am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_donation);
        Intent in = getIntent();
        charityId = in.getStringExtra("charityId");
        charityName = in.getStringExtra("charityName");
        activityId = in.getStringExtra("ActivityId");
        activityName = in.getStringExtra("ActivityName");
        donorId = in.getStringExtra("donorId");
        am=in.getIntExtra("amount",0);
        mAmount = (EditText) findViewById(R.id.amount1);
        mSms = (ImageButton) findViewById(R.id.sms1);
        mFawry = (ImageButton) findViewById(R.id.fawry1);

        mSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount = mAmount.getText().toString();
                if (amount.length() < 1)
                    Toast.makeText(CaseDonation.this, "Please enter amount", Toast.LENGTH_SHORT).show();
                else {

                    a = Integer.valueOf(amount);
                    DonorController d = new DonorController();
                    d.addDonation(charityId, charityName, activityId, activityName, donorId, "SMS", a);
                    Intent intent = new Intent(CaseDonation.this, ViewCases.class);
                    CharityController c =new CharityController();
                     c.editeAmount(activityId,a+am);



                    Toast.makeText(CaseDonation.this, "Donation Successfully Added", Toast.LENGTH_SHORT).show();

                    intent.putExtra("donorId",donorId);
                    startActivity(intent);
                    finish();
                }
            }
        });


        mFawry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount = mAmount.getText().toString();
                if (amount.length() < 1)
                    Toast.makeText(CaseDonation.this, "Please enter amount", Toast.LENGTH_SHORT).show();
                else {

                    a = Integer.valueOf(amount);
                    DonorController d = new DonorController();
                    d.addDonation(charityId, charityName, activityId, activityName, donorId, "Fawry", a);


                    CharityController c =new CharityController();
                    c.editeAmount(activityId,a+am);






                    Toast.makeText(CaseDonation.this, "Donation Successfully Added", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CaseDonation.this, ViewCases.class);

                    intent.putExtra("donorId",donorId);
                    startActivity(intent);
                    finish();
                }

            }
        });


    }


}
