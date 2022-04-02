package com.example.android.donationapp.View.Charity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.donationapp.Controller.CharityController;
import com.example.android.donationapp.R;

public class DeleteActivityValidation extends AppCompatActivity {


    private Button mYes;
    private Button mNo;
    private String id;
    private String charityId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_activity_validation);
        Intent in=getIntent();
        id=in.getStringExtra("ActivityId");
        Intent in2 = getIntent();
        charityId=in2.getStringExtra("charityId");

        mYes=(Button) findViewById(R.id.yes1);
        mNo=(Button) findViewById(R.id.no1);

        mYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharityController c=new CharityController();
                c.deleteCharityActivity(id);
                Intent intent = new Intent(DeleteActivityValidation.this, DeleteActivityPage.class);
                intent.putExtra("charityId",charityId);
                startActivity(intent);
                finish();

            }
        });

        mNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(DeleteActivityValidation.this, DeleteActivityPage.class);
                intent.putExtra("charityId",charityId);
                startActivity(intent);
                finish();

            }
        });



    }


}
