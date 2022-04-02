package com.example.android.donationapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.android.donationapp.Controller.DonorController;
import com.example.android.donationapp.Model.Donor;
import com.example.android.donationapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpPage  extends AppCompatActivity {

    private EditText mEmail ;
    private EditText mPassword;
    private EditText mPasswordConfirm;
    private EditText mName;
    private EditText mAge;
    private EditText mPhone;
    private EditText mGender;
    private Button mSignUp;
    private String email;
    private String password;
    private String confirmPassword;
    private String name;
    private String age;
    private String phone;
    private String gender;
    private Donor mDonor;
    private Donor [] mDonor_list;
    private boolean add=true;
    private boolean read=true;
    private DatabaseReference databaseDonor = FirebaseDatabase.getInstance().getReference("Donor");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        mEmail =(EditText) findViewById(R.id.email);
        mSignUp=(Button) findViewById(R.id.btn_signUp);
        mPassword=(EditText) findViewById(R.id.password);
        mPasswordConfirm=(EditText) findViewById(R.id.confirm_password);
        mName=(EditText) findViewById(R.id.name);
        mPhone=(EditText) findViewById(R.id.phone);
        mAge=(EditText) findViewById(R.id.age);
        mGender=(EditText) findViewById(R.id.gender);

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //_________Check Email Availability_____
                add=true;
                 read=true;

                name=mName.getText().toString();

                age=mAge.getText().toString();
                gender=mGender.getText().toString();
                phone=mPhone.getText().toString();
                email=mEmail.getText().toString();
                password=mPassword.getText().toString();
                confirmPassword=mPasswordConfirm.getText().toString();
                databaseDonor.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        int i = 0;
                        mDonor_list = new Donor[(int) dataSnapshot.getChildrenCount()];
                        for (DataSnapshot snap : dataSnapshot.getChildren()) {
                            mDonor_list[i] = new Donor();
                            mDonor= snap.getValue(Donor.class);
                            mDonor_list[i] =mDonor;
                            ++i;
                        }
                        for (int j = 0; j < mDonor_list.length; ++j) {
                            if (email.equals(mDonor_list[j].getEmail())&&read) {
                                add=false;
                                Toast.makeText(SignUpPage.this,"Please choose another email",Toast.LENGTH_SHORT).show();
                            }

                        }
                         if(name.length()<1)
                        {
                            Toast.makeText(SignUpPage.this,"Please enter your name",Toast.LENGTH_SHORT).show();
                        }
                         else if(email.length()<1)
                         {
                             Toast.makeText(SignUpPage.this,"Please enter your email",Toast.LENGTH_SHORT).show();
                         }
                     else if(password.length()<8)
                      {
                          Toast.makeText(SignUpPage.this,"Please enter password more than 8 digits",Toast.LENGTH_SHORT).show();
                      }
                     else if(!confirmPassword.equals(password))
                      {
                         add=false;
                          Toast.makeText(SignUpPage.this,"Wrong password confirmation",Toast.LENGTH_SHORT).show();
                      }
                     else if(phone.length()<1)
                         {
                             Toast.makeText(SignUpPage.this,"Please enter your phone number",Toast.LENGTH_SHORT).show();
                         }
                         else if(gender.length()<1)
                         {
                             Toast.makeText(SignUpPage.this,"Please enter your gender",Toast.LENGTH_SHORT).show();
                         }
                         else if(age.length()<1)
                         {
                             Toast.makeText(SignUpPage.this,"Please enter your age",Toast.LENGTH_SHORT).show();
                         }

                    else if(add&&read)
                    {
                        DonorController mDonor=new DonorController();
                    read=false;
                     mDonor.addDonor(name,email,password,phone,gender,age);
                        Intent intent;
                        Toast.makeText(SignUpPage.this,"Account Added Successfully",Toast.LENGTH_SHORT).show();

                        intent = new Intent(SignUpPage.this, HomePage.class);

                        startActivity(intent);
                        finish();

                    }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }});
            }
        });

    }
}
