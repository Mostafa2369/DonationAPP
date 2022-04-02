package com.example.android.donationapp.Model;



public class CharityActivity {
    private String charityActivityID;
    private String name;
    private String charityID;
    private String description;



    public CharityActivity(String charityActivityID, String charityID, String name, String description) {
        this.charityActivityID = charityActivityID;
        this.charityID = charityID;
        this.name = name;
        this.description = description;
    }

    public CharityActivity() {
    }

    public String getCharityActivityID() {
        return charityActivityID;
    }

    public String getCharityID() {
        return charityID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }



}

