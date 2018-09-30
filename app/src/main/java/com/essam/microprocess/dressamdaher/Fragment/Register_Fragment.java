package com.essam.microprocess.dressamdaher.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.essam.microprocess.dressamdaher.Contracts.MainActivityContract;
import com.essam.microprocess.dressamdaher.R;

public class Register_Fragment extends Fragment implements View.OnClickListener{

    EditText NameStudent;
    TextView gotoLogin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.register_layout,container,false);
        NameStudent = v.findViewById(R.id.NameStudent);
        gotoLogin   = v.findViewById(R.id.gotoLogin);
        gotoLogin.setOnClickListener(this);
        NameStudent.requestFocus();
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
    }
}
