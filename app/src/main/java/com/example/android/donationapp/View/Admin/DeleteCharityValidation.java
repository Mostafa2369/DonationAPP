package com.example.android.donationapp.View.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.donationapp.Controller.AdminContoller;
import com.example.android.donationapp.R;

public class DeleteCharityValidation extends AppCompatActivity {

private Button mYes;
private Button mNo;
private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_charity_validation);
        Intent in=getIntent();
         id=in.getStringExtra("charityId");


        mYes=(Button) findViewById(R.id.yes);
        mNo=(Button) findViewById(R.id.no);

        mYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminContoller a=new AdminContoller();
                a.deleteCharity(id);
                Intent intent = new Intent(DeleteCharityValidation.this, DeleteCharityPage.class);
                startActivity(intent);
                finish();

            }
        });

        mNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(DeleteCharityValidation.this, DeleteCharityPage.class);
                startActivity(intent);
                finish();

            }
        });


    }


}
