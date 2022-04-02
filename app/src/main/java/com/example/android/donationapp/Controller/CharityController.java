package com.example.android.donationapp.Controller;


import com.example.android.donationapp.Model.CharityActivity;
import com.example.android.donationapp.Model.EmergencyCase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CharityController {



    private EmergencyCase mEmergencyCase;
    private DatabaseReference databaseEmergencyCase = FirebaseDatabase.getInstance().getReference("EmergencyCase");
    private DatabaseReference databaseCharityActivity = FirebaseDatabase.getInstance().getReference("CharityActivity");
    private CharityActivity mCharityActivity;


    //___________________Add Emergency Case_____________________

    public void addEmergencyCase(String charityID, String tile, String description, int amount) {

        String id;
        id = databaseEmergencyCase.push().getKey();
        mEmergencyCase = new EmergencyCase(id, charityID, tile, description, amount);
        databaseEmergencyCase.child(id).setValue(mEmergencyCase);
    }




//________________________Delete Emergency case____________________

    public void deleteEmergencyCase(String id) {
        databaseEmergencyCase.child(id).removeValue();
    }






    //_____________Add CharityActivity_______________________

    public void addCharityActivity(String charityID, String name, String description) {

        String id;
        id = databaseCharityActivity.push().getKey();
        mCharityActivity = new CharityActivity(id, charityID, name, description);
        databaseCharityActivity.child(id).setValue(mCharityActivity);
    }


    //___________CharityActivity Update______________________

    public void editeName(String id, String name) {
        databaseCharityActivity.child(id).child("name").setValue(name);


    }

    public void editeDescription(String id, String description) {
        databaseCharityActivity.child(id).child("description").setValue(description);


    }


    //______________________Delete CharityActivity_______________


    public void deleteCharityActivity(String id) {
        databaseCharityActivity.child(id).removeValue();
    }


    public void editeAmount(String id, int amount) {
        databaseEmergencyCase.child(id).child("amount").setValue(amount);


    }

}
