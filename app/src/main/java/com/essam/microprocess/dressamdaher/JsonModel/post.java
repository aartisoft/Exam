package com.essam.microprocess.dressamdaher.JsonModel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by microprocess on 2018-10-01.
 */

public class post {
    private String nameStudent;
    private String email;
    private String phone;
    private String uID;
    private String country;

    public post(String nameStudent, String email, String phone, String uID, String country) {
        this.nameStudent = nameStudent;
        this.email = email;
        this.phone = phone;
        this.uID = uID;
        this.country = country;
    }

    public post(){

    }

    protected post(Parcel in) {
        nameStudent = in.readString();
        email = in.readString();
        phone = in.readString();
        uID = in.readString();
        country = in.readString();
    }


    public String getNameStudent() {
        return nameStudent;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getuID() {
        return uID;
    }

    public String getCountry() {
        return country;
    }
}
