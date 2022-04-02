package com.example.android.donationapp.View.Charity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.donationapp.Controller.CharityController;
import com.example.android.donationapp.R;





public class UpdateActivityFields extends AppCompatActivity {

    private EditText mName;
    private EditText mDescription;
    private Button mDone;
    private String charityId;
    private String name;
    private String description;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_activity_fields);
        Intent in=getIntent();
        id=in.getStringExtra("ActivityId");
        name=in.getStringExtra("ActivityName");
        description=in.getStringExtra("ActivityDescription");
        charityId=in.getStringExtra("charityId");
        mName=(EditText) findViewById(R.id.activity_name2);
        mDescription=(EditText) findViewById(R.id.description2);
        mDone=(Button) findViewById(R.id.btn_signUp4);
        mName.setText(name);
        mDescription.setText(description);
        mDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mName.getText().toString().length()<1)
            {
                Toast.makeText(UpdateActivityFields.this,"Please enter name",Toast.LENGTH_SHORT).show();
            }
                else if(mDescription.getText().toString().length()<1)
            {
                Toast.makeText(UpdateActivityFields.this,"Please enter description",Toast.LENGTH_SHORT).show();
            }
                else {
                    CharityController mActivity = new CharityController();
                    mActivity.editeName(id, mName.getText().toString());
                    mActivity.editeDescription(id, mDescription.getText().toString());
                    Toast.makeText(UpdateActivityFields.this, "Activity Updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(UpdateActivityFields.this, UpdateActivityPage.class);
                    intent2.putExtra("charityId",charityId);
                    startActivity(intent2);
                }
            }
        });


    }







}
