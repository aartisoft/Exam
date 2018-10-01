package com.essam.microprocess.dressamdaher.Enums;

public enum DataBase_Refrences {


    USERREF("Users"),BLOCKUSER("Blocked_User");

    private String val;

    private DataBase_Refrences(String val) {

        this.val = val;
    }

    public String getRef(){

        return this.val;

    }
}
