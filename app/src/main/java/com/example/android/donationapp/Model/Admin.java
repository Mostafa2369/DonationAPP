package com.example.android.donationapp.Model;



public class Admin {

    private String adminID;
    private String name;
    private String email;
    private String password;



    public Admin(String adminID, String name, String email, String password) {
        this.adminID = adminID;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Admin() {
    }

    public String getAdminID() {
        return adminID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }




}
