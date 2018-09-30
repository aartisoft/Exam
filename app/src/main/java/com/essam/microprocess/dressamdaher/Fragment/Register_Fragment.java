package com.essam.microprocess.dressamdaher.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.essam.microprocess.dressamdaher.Contracts.MainActivityContract;
import com.essam.microprocess.dressamdaher.R;
import com.essam.microprocess.dressamdaher.Utils.ViewsEmpty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Register_Fragment extends Fragment implements View.OnClickListener{

    EditText NameStudent,Email,Password,makeSureFromPass,phoneme;
    TextView gotoLogin;
    Spinner spinnerCountry;
    Button registeringToData;
    ProgressBar loadtoregister;
    String [] country;
    String selectedCountry = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        country  = getResources().getStringArray(R.array.country);  // country array in spinner //
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v            = inflater.inflate(R.layout.register_layout,container,false);
        NameStudent       = v.findViewById(R.id.NameStudent);
        Email             = v.findViewById(R.id.Email);
        Password          = v.findViewById(R.id.Password);
        makeSureFromPass  = v.findViewById(R.id.makeSureFromPass);
        phoneme           = v.findViewById(R.id.phoneme);
        spinnerCountry    = v.findViewById(R.id.spinnerCountry);
        loadtoregister    = v.findViewById(R.id.loadtoregister);
        registeringToData = v.findViewById(R.id.registeringToData);
        gotoLogin         = v.findViewById(R.id.gotoLogin);

        Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        Password.setTypeface(Typeface.DEFAULT);
        makeSureFromPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.spinner_style,country);
        spinnerCountry.setAdapter(adapter);
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > 0){
                    selectedCountry = country[i];
                }else {
                    selectedCountry = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                // nothing to dooo //
            }
        });
        makeSureFromPass.setTypeface(Typeface.DEFAULT);
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


                ViewsEmpty.isEmpty(NameStudent,getResources().getString(R.string.empty_field));

                if (Email.getText().toString().isEmpty()){
                    Email.setError(getResources().getString(R.string.empty_field));
                }
                if (!Email.getText().toString().isEmpty() &&!Email.getText().toString().endsWith("@yahoo.com")){
                    Email.setError(getResources().getString(R.string.notLookedLikeYahoo));
                }
                if (Password.getText().toString().isEmpty()){
                    Password.setError(getResources().getString(R.string.enterPass),null);
                }
                if (!Password.getText().toString().isEmpty() && Password.getText().toString().length()<12){
                    Password.setError(getResources().getString(R.string.less12char),null);
                }
                if (makeSureFromPass.getText().toString().isEmpty()){
                    makeSureFromPass.setError(getResources().getString(R.string.should_like_pass),null);
                }
                if (!Password.getText().toString().isEmpty()
                        && !makeSureFromPass.getText().toString().isEmpty()
                        && !makeSureFromPass.getText().toString().equals(Password.getText().toString())){

                    makeSureFromPass.setError( getResources().getString(R.string.edit1_notLoke_edit2),null);
                }
                ViewsEmpty.isEmpty(phoneme,getResources().getString(R.string.enter_phone));
                if (selectedCountry.equals("")){

                    Toast.makeText(getActivity(), getResources().getString(R.string.select_country), Toast.LENGTH_SHORT).show();

                }

                // if all things are true //
            if (!NameStudent.getText().toString().isEmpty()&& !Email.getText().toString().isEmpty()
                    && !Password.getText().toString().isEmpty()&&Password.getText().toString().length()>=12
                    && !makeSureFromPass.getText().toString().isEmpty() && makeSureFromPass.getText().toString().equals(Password.getText().toString())
                    && !phoneme.getText().toString().isEmpty() && !selectedCountry.equals("")){


                Toast.makeText(getActivity(), "كل شي تماااااااااااااااااااام ", Toast.LENGTH_SHORT).show();

            }

        }
    }
}
