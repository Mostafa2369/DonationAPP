package com.example.android.donationapp.Controller;

import com.example.android.donationapp.Model.Donation;
import com.example.android.donationapp.Model.Donor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DonorController {

    private DatabaseReference databaseDonation;
    private Donation mDonation;
    private DatabaseReference databaseDonor = FirebaseDatabase.getInstance().getReference("Donor");
    private Donor mDonor;



    //__________________Add Donor__________________


    public void addDonor(String name, String email, String password, String phoneNumber, String gender,  String age) {

        String id;
        id = databaseDonor.push().getKey();
        mDonor = new Donor(id, name, email, password, phoneNumber, gender,age);
        databaseDonor.child(id).setValue(mDonor);
    }


    //___________________Update Donor_____________________


    public void editeName(String id, String name) {
        databaseDonor.child(id).child("name").setValue(name);

    }

    public void editeEmail(String id, String email) {
        databaseDonor.child(id).child("email").setValue(email);

    }

    public void editePassword(String id, String password) {
        databaseDonor.child(id).child("password").setValue(password);

    }

    public void editePhoneNumber(String id, String phoneNumber) {
        databaseDonor.child(id).child("phoneNumber").setValue(phoneNumber);

    }

    public void editeGender(String id, String gender) {
        databaseDonor.child(id).child("gender").setValue(gender);

    }

    public void editeAge(String id, String age) {
        databaseDonor.child(id).child("age").setValue(age);

    }

    //____________Add Donation_______________________

    public void addDonation( String charityID, String charityName, String charityActivityID, String charityActivityName, String donorID, String donationType, int amount) {
        databaseDonation = FirebaseDatabase.getInstance().getReference("Donation");
        String id;
        id = databaseDonation.push().getKey();
        mDonation = new Donation(id,charityID,charityName,charityActivityID,charityActivityName,donorID,donationType,amount);
        databaseDonation.child(id).setValue(mDonation);
    }








}
