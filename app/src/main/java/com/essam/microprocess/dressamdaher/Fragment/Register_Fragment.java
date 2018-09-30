package com.essam.microprocess.dressamdaher.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.essam.microprocess.dressamdaher.Contracts.MainActivityContract;
import com.essam.microprocess.dressamdaher.R;
import com.essam.microprocess.dressamdaher.Utils.ViewsEmpty;

public class Register_Fragment extends Fragment implements View.OnClickListener{

    EditText NameStudent,Email,Password,makeSureFromPass,phoneme;
    TextView gotoLogin;
    Spinner spinnerCountry;
    Button registeringToData;
    ProgressBar loadtoregister;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.register_layout,container,false);
        NameStudent       = v.findViewById(R.id.NameStudent);
        Email             = v.findViewById(R.id.Email);
        Password          = v.findViewById(R.id.Password);
        makeSureFromPass  = v.findViewById(R.id.makeSureFromPass);
        phoneme           = v.findViewById(R.id.phoneme);
        spinnerCountry    = v.findViewById(R.id.spinnerCountry);
        loadtoregister    = v.findViewById(R.id.loadtoregister);
        registeringToData = v.findViewById(R.id.registeringToData);
        gotoLogin         = v.findViewById(R.id.gotoLogin);
        registeringToData.setOnClickListener(this);
        gotoLogin.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        if (view==gotoLogin){
            
            // go to loginFrag ,....  //
            MainActivityContract.View view1 = (MainActivityContract.View) getActivity();
            if (view1!=null){
                view1.showLoginFragment();
            }

        }

        if (view==registeringToData){

                ViewsEmpty.isEmpty(NameStudent,"قم بملئ الحقل من فضلك");

        }
    }
}
