package com.example.android.donationapp.Model;



public class Donor {
    private String donorID;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String gender;
    private String age;



    public Donor(){}
    public Donor(String donorID, String name, String email, String password, String phoneNumber, String gender,  String age) {
        this.donorID = donorID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public String getDonorID() {
        return donorID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }


    public String getPassword() {
        return password;
    }



}
