package com.essam.microprocess.dressamdaher.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.essam.microprocess.dressamdaher.Dialog.AnimatedDialog;
import com.essam.microprocess.dressamdaher.Fragment.First_Fragment;
import com.essam.microprocess.dressamdaher.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.Main_fragment,new First_Fragment())
                .commit();

    }


}
