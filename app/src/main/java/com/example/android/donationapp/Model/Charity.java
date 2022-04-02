package com.example.android.donationapp.Model;



public class Charity {
    private String charityID;
    private String name;
    private String email;
    private String password;
    private String location;
    private String phoneNumber;



    public Charity(String charityID, String name, String location, String phoneNumber,String email,String password) {
        this.charityID = charityID;
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.email=email;
        this.password=password;

    }

    public Charity() {
    }

    public String getCharityID() {
        return charityID;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
