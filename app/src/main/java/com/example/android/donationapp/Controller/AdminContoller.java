package com.example.android.donationapp.Controller;


import android.support.v7.app.AppCompatActivity;
import com.example.android.donationapp.Model.Admin;
import com.example.android.donationapp.Model.Charity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminContoller extends AppCompatActivity {

    private Charity mCharity;
    private DatabaseReference databaseCharity= FirebaseDatabase.getInstance().getReference("Charity");
    private Admin mAdmin;
    private DatabaseReference databaseAdmin= FirebaseDatabase.getInstance().getReference("Admin");


    //______________Add Admin_________________________


    public void addAdmin(String Name, String Email, String Password) {

        String id;
        id = databaseAdmin.push().getKey();
        mAdmin = new Admin(id, Name, Email, Password);
        databaseAdmin.child(id).setValue(mAdmin);
    }



    //__________________Add Charity_________________

    public void addCharity(String Name, String Location, String PhoneNumber,String Email,String Password) {

        String id;
        id = databaseCharity.push().getKey();
        mCharity = new Charity(id, Name, Location, PhoneNumber,Email,Password);
        databaseCharity.child(id).setValue(mCharity);
    }
    //___________Charity Update______________________

    public void editeName(String id, String name) {
        databaseCharity.child(id).child("name").setValue(name);


    }

    public void editeLocation(String id, String location) {
        databaseCharity.child(id).child("location").setValue(location);


    }

    public void editePhoneNumber(String id,String phoneNumber) {
        databaseCharity.child(id).child("phoneNumber").setValue(phoneNumber);


    }
    public void editeEmail(String id, String email) {
        databaseCharity.child(id).child("email").setValue(email);


    }
    public void editePassword(String id, String password) {
        databaseCharity.child(id).child("password").setValue(password);


    }


    //_____________Delete Charity_____________________


    public void deleteCharity(String id) {
        databaseCharity.child(id).removeValue();
    }





}
