package com.essam.microprocess.dressamdaher.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.essam.microprocess.dressamdaher.Dialog.AlertDialog;
import com.essam.microprocess.dressamdaher.R;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String Message = bundle.getString("Message");

            AlertDialog alertDialog = new AlertDialog(this,Message);
            alertDialog.show();

        }
    }
}
