package com.example.android.donationapp.View.Charity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.donationapp.Controller.CharityController;
import com.example.android.donationapp.R;

public class DeleteEmergencyValidation extends AppCompatActivity {


    private Button mYes;
    private Button mNo;
    private String id;
    private String charityId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_emergency_validation);
        Intent in=getIntent();
        id=in.getStringExtra("EmergencyId");
        charityId=in.getStringExtra("charityId");

        mYes=(Button) findViewById(R.id.yes9);
        mNo=(Button) findViewById(R.id.no9);

        mYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharityController c=new CharityController();
                c.deleteEmergencyCase(id);

                Intent intent = new Intent(DeleteEmergencyValidation.this, DeleteEmergencyPage.class);
                intent.putExtra("charityId",charityId);
                startActivity(intent);
                finish();

            }
        });

        mNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(DeleteEmergencyValidation.this, DeleteEmergencyPage.class);
                intent.putExtra("charityId",charityId);
                startActivity(intent);
                finish();

            }
        });



    }
}
