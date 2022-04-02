package com.example.android.donationapp.Model;


public class EmergencyCase {
    private String emergencyCaseID;
    private String charityID;
    private String tile;
    private String description;
    private int amount;

    public EmergencyCase() {
    }

    public EmergencyCase(String emergencyCaseID, String charityID, String tile, String description, int amount) {
        this.emergencyCaseID = emergencyCaseID;
        this.charityID = charityID;
        this.tile = tile;
        this.description = description;
        this.amount = amount;
    }



    public String getEmergencyCaseID() {
        return emergencyCaseID;
    }

    public String getTile() {
        return tile;
    }

    public String getCharityID() {
        return charityID;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }



}
