package com.example.android.donationapp.View.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.donationapp.Controller.AdminContoller;
import com.example.android.donationapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateCharityFields extends AppCompatActivity {

private String id;
private String name;
private String email;
private String location;
private String phone;
private String password;
    private EditText mName;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mLocation;
    private EditText mPhone;
    private Button mDone;

    private DatabaseReference databaseCharity= FirebaseDatabase.getInstance().getReference("Charity");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_charity_fields);

        Intent in=getIntent();
        id=in.getStringExtra("charityId");
        name=in.getStringExtra("charityName");
        email=in.getStringExtra("charityEmail");
        location=in.getStringExtra("charityLocation");
        password=in.getStringExtra("charityPassword");
        phone=in.getStringExtra("charityPhone");


        mEmail =(EditText) findViewById(R.id.email3);
        mLocation =(EditText) findViewById(R.id.location3);
        mPassword=(EditText) findViewById(R.id.password3);
        mName=(EditText) findViewById(R.id.name3);
        mPhone=(EditText) findViewById(R.id.phone3);
        mDone=(Button) findViewById(R.id.btn_signUp3);

        mEmail.setText(email);
        mLocation.setText(location);
        mName.setText(name);
        mPassword.setText(password);
        mPhone.setText(phone);

mDone.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if(mName.getText().toString().length()<1)
        {
            Toast.makeText(UpdateCharityFields.this,"Please enter name",Toast.LENGTH_SHORT).show();
        }
        else if(mEmail.getText().toString().length()<1)
        {
            Toast.makeText(UpdateCharityFields.this,"Please enter email",Toast.LENGTH_SHORT).show();
        }
        else if(mPassword.getText().toString().length()<8)
        {
            Toast.makeText(UpdateCharityFields.this,"Please enter password more than 8 digits",Toast.LENGTH_SHORT).show();
        }

        else if(mPhone.getText().toString().length()<1)
        {
            Toast.makeText(UpdateCharityFields.this,"Please enter phone number",Toast.LENGTH_SHORT).show();
        }
        else if(mLocation.getText().toString().length()<1)
        {
            Toast.makeText(UpdateCharityFields.this,"Please enter location",Toast.LENGTH_SHORT).show();
        }



        else {
            AdminContoller mAdmin = new AdminContoller();
            mAdmin.editeName(id, mName.getText().toString());
            mAdmin.editeEmail(id, mEmail.getText().toString());
            mAdmin.editePassword(id, mPassword.getText().toString());
            mAdmin.editeLocation(id, mLocation.getText().toString());
            mAdmin.editePhoneNumber(id, mPhone.getText().toString());
            Toast.makeText(UpdateCharityFields.this, "Charity Updated Successfully", Toast.LENGTH_SHORT).show();
        }
    }
});


    }

}
