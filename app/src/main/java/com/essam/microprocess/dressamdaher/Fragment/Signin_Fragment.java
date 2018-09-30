package com.essam.microprocess.dressamdaher.Fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.essam.microprocess.dressamdaher.Contracts.MainActivityContract;
import com.essam.microprocess.dressamdaher.Contracts.SigninContract;
import com.essam.microprocess.dressamdaher.Dialog.AnimatedDialog;
import com.essam.microprocess.dressamdaher.MainPresnter.SigninPresenter;
import com.essam.microprocess.dressamdaher.R;
import com.essam.microprocess.dressamdaher.Views.ControlPanel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class Signin_Fragment extends Fragment implements SigninContract.view {

    SigninContract.presenter presenter ;
    AnimatedDialog dialog ;

    @BindView(R.id.email)
    EditText et_email;

    @BindView(R.id.password)
    EditText et_password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_signin, container, false);

        // initialize butterknife library .
        ButterKnife.bind(this , v );

        //initialize dialog with context .
        dialog =  new AnimatedDialog(getActivity());

        //initialize presenter
        presenter = new SigninPresenter(this);

        // solve problem of toggle password button and font with password
        et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        et_password.setTypeface(Typeface.DEFAULT);

        return  v ;
    }



    // goto Register fragment
    @OnClick(R.id.txt_register)
    void Goto_Register_fragment (View view){
        //add fragment
        MainActivityContract.View view1 = (MainActivityContract.View) getActivity();
        if (view1!=null){

            view1.showFragmentRegister();
        }
    }


    @OnClick(R.id.email_sign_in_button)
    void Singin (View view){

        //check first if some thing empty .
        presenter.passtocheck(et_email,et_password);


    }



    @Override
    public void checkResult(String Result) {

       if(Result.equals("Successful")){

           dialog.ShowDialog();
           presenter.passlogIn(et_email.getText().toString().trim() , et_password.getText().toString());


       }

    }

    @Override
    public void logInResult(String Result) {

        if (Result.equals("Successful")){
            //Successful Login .
            dialog.Close_Dialog();
            //Toast.makeText(getActivity(), Result, Toast.LENGTH_SHORT).show();

            getActivity().finish();

            //Start Activity ControlPanel
            Intent intent = new Intent(getActivity(),ControlPanel.class);
            startActivity(intent);


        }
        else {
            //Failure Login .
            dialog.Close_Dialog();
            et_password.setError(getString(R.string.PleaseMakesure));
        }

    }
}
