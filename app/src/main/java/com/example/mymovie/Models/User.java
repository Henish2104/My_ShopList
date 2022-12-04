package com.example.mymovie.Models;

public class User {
    String username;
    String phonenumber;
    String email;
    String confirmpassword;
    String useid;
    String photourl;

    public User(String username, String phonenumber, String email, String confirmpassword) {
        this.username = username;
        this.phonenumber = phonenumber;
        this.email = email;
        this.confirmpassword = confirmpassword;
    }


    public User() {
    }

    public String getUseid() {
        return useid;
    }

    public void setUseid(String useid) {
        this.useid = useid;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }
}
