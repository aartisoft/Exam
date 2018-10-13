package com.essam.microprocess.dressamdaher.Enums;

public enum DataBase_Refrences {


    USERREF("Users"),BLOCKUSER("Blocked_User"),BANKQUESTIONS("Banck_Questions"),CHOSENQUESTIONID("Chosen_questions_ID")
    ,EXAMS("Exams"),ADMIN("Admins"),TimeApiKey("KXG6INZZU6EO"),Format("json");



    private String val;

    private DataBase_Refrences(String val) {

        this.val = val;
    }

    public String getRef(){

        return this.val;

    }
}
