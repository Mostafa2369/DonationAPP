package com.example.android.donationapp.Model;


public class Donation {
    private String donationID;
    private String charityID;
    private String charityName;
    private String charityActivityID;
    private String charityActivityName;
    private String donorID;
    private String donationType;
    private int amount;



    public Donation() {
    }


    public Donation(String donationID, String charityID, String charityName, String charityActivityID, String charityActivityName, String donorID, String donationType, int amount) {
        this.donationID = donationID;
        this.charityID = charityID;
        this.charityName = charityName;
        this.charityActivityID = charityActivityID;
        this.charityActivityName = charityActivityName;
        this.donorID = donorID;
        this.donationType = donationType;
        this.amount = amount;
    }

    public String getDonationID() {
        return donationID;
    }

    public String getCharityID() {
        return charityID;
    }

    public String getCharityActivityID() {
        return charityActivityID;
    }

    public String getDonationType() {
        return donationType;
    }

    public int getAmount() {
        return amount;
    }

    public String getDonorID() {
        return donorID;
    }

    public String getCharityName() {
        return charityName;
    }

    public String getCharityActivityName() {
        return charityActivityName;
    }
}
