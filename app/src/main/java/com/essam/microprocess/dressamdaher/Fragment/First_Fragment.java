package com.essam.microprocess.dressamdaher.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.essam.microprocess.dressamdaher.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class First_Fragment extends Fragment {

@BindView(R.id.Goto_signin)
    Button Sign_in;

@BindView(R.id.Goto_signup)
Button Sign_up;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v =inflater.inflate(R.layout.fragment_first_, container, false);

         ButterKnife.bind(this,v); // intialize butterknife .



         Sign_in.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 // add fragment (sign in )
                getFragmentManager().beginTransaction().
                        replace(R.id.Main_fragment,new Signin_Fragment())
                        .addToBackStack(null).commit();

             }
         });

         Sign_up.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 // add fragment (sign up )

             }
         });





        return v;
    }

}
